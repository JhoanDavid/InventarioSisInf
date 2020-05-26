/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Producto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.ProductoMovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jramirez
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController() {
       this.emf = Persistence.createEntityManagerFactory("InventarioSisInfPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getProductoMovimientoList() == null) {
            producto.setProductoMovimientoList(new ArrayList<ProductoMovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProductoMovimiento> attachedProductoMovimientoList = new ArrayList<ProductoMovimiento>();
            for (ProductoMovimiento productoMovimientoListProductoMovimientoToAttach : producto.getProductoMovimientoList()) {
                productoMovimientoListProductoMovimientoToAttach = em.getReference(productoMovimientoListProductoMovimientoToAttach.getClass(), productoMovimientoListProductoMovimientoToAttach.getId());
                attachedProductoMovimientoList.add(productoMovimientoListProductoMovimientoToAttach);
            }
            producto.setProductoMovimientoList(attachedProductoMovimientoList);
            em.persist(producto);
            for (ProductoMovimiento productoMovimientoListProductoMovimiento : producto.getProductoMovimientoList()) {
                Producto oldIdProductoOfProductoMovimientoListProductoMovimiento = productoMovimientoListProductoMovimiento.getIdProducto();
                productoMovimientoListProductoMovimiento.setIdProducto(producto);
                productoMovimientoListProductoMovimiento = em.merge(productoMovimientoListProductoMovimiento);
                if (oldIdProductoOfProductoMovimientoListProductoMovimiento != null) {
                    oldIdProductoOfProductoMovimientoListProductoMovimiento.getProductoMovimientoList().remove(productoMovimientoListProductoMovimiento);
                    oldIdProductoOfProductoMovimientoListProductoMovimiento = em.merge(oldIdProductoOfProductoMovimientoListProductoMovimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            List<ProductoMovimiento> productoMovimientoListOld = persistentProducto.getProductoMovimientoList();
            List<ProductoMovimiento> productoMovimientoListNew = producto.getProductoMovimientoList();
            List<ProductoMovimiento> attachedProductoMovimientoListNew = new ArrayList<ProductoMovimiento>();
            for (ProductoMovimiento productoMovimientoListNewProductoMovimientoToAttach : productoMovimientoListNew) {
                productoMovimientoListNewProductoMovimientoToAttach = em.getReference(productoMovimientoListNewProductoMovimientoToAttach.getClass(), productoMovimientoListNewProductoMovimientoToAttach.getId());
                attachedProductoMovimientoListNew.add(productoMovimientoListNewProductoMovimientoToAttach);
            }
            productoMovimientoListNew = attachedProductoMovimientoListNew;
            producto.setProductoMovimientoList(productoMovimientoListNew);
            producto = em.merge(producto);
            for (ProductoMovimiento productoMovimientoListOldProductoMovimiento : productoMovimientoListOld) {
                if (!productoMovimientoListNew.contains(productoMovimientoListOldProductoMovimiento)) {
                    productoMovimientoListOldProductoMovimiento.setIdProducto(null);
                    productoMovimientoListOldProductoMovimiento = em.merge(productoMovimientoListOldProductoMovimiento);
                }
            }
            for (ProductoMovimiento productoMovimientoListNewProductoMovimiento : productoMovimientoListNew) {
                if (!productoMovimientoListOld.contains(productoMovimientoListNewProductoMovimiento)) {
                    Producto oldIdProductoOfProductoMovimientoListNewProductoMovimiento = productoMovimientoListNewProductoMovimiento.getIdProducto();
                    productoMovimientoListNewProductoMovimiento.setIdProducto(producto);
                    productoMovimientoListNewProductoMovimiento = em.merge(productoMovimientoListNewProductoMovimiento);
                    if (oldIdProductoOfProductoMovimientoListNewProductoMovimiento != null && !oldIdProductoOfProductoMovimientoListNewProductoMovimiento.equals(producto)) {
                        oldIdProductoOfProductoMovimientoListNewProductoMovimiento.getProductoMovimientoList().remove(productoMovimientoListNewProductoMovimiento);
                        oldIdProductoOfProductoMovimientoListNewProductoMovimiento = em.merge(oldIdProductoOfProductoMovimientoListNewProductoMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<ProductoMovimiento> productoMovimientoList = producto.getProductoMovimientoList();
            for (ProductoMovimiento productoMovimientoListProductoMovimiento : productoMovimientoList) {
                productoMovimientoListProductoMovimiento.setIdProducto(null);
                productoMovimientoListProductoMovimiento = em.merge(productoMovimientoListProductoMovimiento);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
