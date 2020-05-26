/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Producto;
import Entidades.Movimiento;
import Entidades.ProductoMovimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jramirez
 */
public class ProductoMovimientoJpaController implements Serializable {

    public ProductoMovimientoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InventarioSisInfPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoMovimiento productoMovimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = productoMovimiento.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getId());
                productoMovimiento.setIdProducto(idProducto);
            }
            Movimiento idMov = productoMovimiento.getIdMov();
            if (idMov != null) {
                idMov = em.getReference(idMov.getClass(), idMov.getId());
                productoMovimiento.setIdMov(idMov);
            }
            em.persist(productoMovimiento);
            if (idProducto != null) {
                idProducto.getProductoMovimientoList().add(productoMovimiento);
                idProducto = em.merge(idProducto);
            }
            if (idMov != null) {
                idMov.getProductoMovimientoList().add(productoMovimiento);
                idMov = em.merge(idMov);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoMovimiento productoMovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoMovimiento persistentProductoMovimiento = em.find(ProductoMovimiento.class, productoMovimiento.getId());
            Producto idProductoOld = persistentProductoMovimiento.getIdProducto();
            Producto idProductoNew = productoMovimiento.getIdProducto();
            Movimiento idMovOld = persistentProductoMovimiento.getIdMov();
            Movimiento idMovNew = productoMovimiento.getIdMov();
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getId());
                productoMovimiento.setIdProducto(idProductoNew);
            }
            if (idMovNew != null) {
                idMovNew = em.getReference(idMovNew.getClass(), idMovNew.getId());
                productoMovimiento.setIdMov(idMovNew);
            }
            productoMovimiento = em.merge(productoMovimiento);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getProductoMovimientoList().remove(productoMovimiento);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getProductoMovimientoList().add(productoMovimiento);
                idProductoNew = em.merge(idProductoNew);
            }
            if (idMovOld != null && !idMovOld.equals(idMovNew)) {
                idMovOld.getProductoMovimientoList().remove(productoMovimiento);
                idMovOld = em.merge(idMovOld);
            }
            if (idMovNew != null && !idMovNew.equals(idMovOld)) {
                idMovNew.getProductoMovimientoList().add(productoMovimiento);
                idMovNew = em.merge(idMovNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productoMovimiento.getId();
                if (findProductoMovimiento(id) == null) {
                    throw new NonexistentEntityException("The productoMovimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoMovimiento productoMovimiento;
            try {
                productoMovimiento = em.getReference(ProductoMovimiento.class, id);
                productoMovimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoMovimiento with id " + id + " no longer exists.", enfe);
            }
            Producto idProducto = productoMovimiento.getIdProducto();
            if (idProducto != null) {
                idProducto.getProductoMovimientoList().remove(productoMovimiento);
                idProducto = em.merge(idProducto);
            }
            Movimiento idMov = productoMovimiento.getIdMov();
            if (idMov != null) {
                idMov.getProductoMovimientoList().remove(productoMovimiento);
                idMov = em.merge(idMov);
            }
            em.remove(productoMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoMovimiento> findProductoMovimientoEntities() {
        return findProductoMovimientoEntities(true, -1, -1);
    }

    public List<ProductoMovimiento> findProductoMovimientoEntities(int maxResults, int firstResult) {
        return findProductoMovimientoEntities(false, maxResults, firstResult);
    }

    private List<ProductoMovimiento> findProductoMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoMovimiento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProductoMovimiento findProductoMovimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoMovimiento> rt = cq.from(ProductoMovimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
