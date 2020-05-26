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
import Entidades.Usuario;
import Entidades.Cliente;
import Entidades.Movimiento;
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
public class MovimientoJpaController implements Serializable {

    public MovimientoJpaController() {
      this.emf = Persistence.createEntityManagerFactory("InventarioSisInfPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimiento movimiento) {
        if (movimiento.getProductoMovimientoList() == null) {
            movimiento.setProductoMovimientoList(new ArrayList<ProductoMovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioTrans = movimiento.getUsuarioTrans();
            if (usuarioTrans != null) {
                usuarioTrans = em.getReference(usuarioTrans.getClass(), usuarioTrans.getId());
                movimiento.setUsuarioTrans(usuarioTrans);
            }
            Cliente idCliente = movimiento.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getId());
                movimiento.setIdCliente(idCliente);
            }
            List<ProductoMovimiento> attachedProductoMovimientoList = new ArrayList<ProductoMovimiento>();
            for (ProductoMovimiento productoMovimientoListProductoMovimientoToAttach : movimiento.getProductoMovimientoList()) {
                productoMovimientoListProductoMovimientoToAttach = em.getReference(productoMovimientoListProductoMovimientoToAttach.getClass(), productoMovimientoListProductoMovimientoToAttach.getId());
                attachedProductoMovimientoList.add(productoMovimientoListProductoMovimientoToAttach);
            }
            movimiento.setProductoMovimientoList(attachedProductoMovimientoList);
            em.persist(movimiento);
            if (usuarioTrans != null) {
                usuarioTrans.getMovimientoList().add(movimiento);
                usuarioTrans = em.merge(usuarioTrans);
            }
            if (idCliente != null) {
                idCliente.getMovimientoList().add(movimiento);
                idCliente = em.merge(idCliente);
            }
            for (ProductoMovimiento productoMovimientoListProductoMovimiento : movimiento.getProductoMovimientoList()) {
                Movimiento oldIdMovOfProductoMovimientoListProductoMovimiento = productoMovimientoListProductoMovimiento.getIdMov();
                productoMovimientoListProductoMovimiento.setIdMov(movimiento);
                productoMovimientoListProductoMovimiento = em.merge(productoMovimientoListProductoMovimiento);
                if (oldIdMovOfProductoMovimientoListProductoMovimiento != null) {
                    oldIdMovOfProductoMovimientoListProductoMovimiento.getProductoMovimientoList().remove(productoMovimientoListProductoMovimiento);
                    oldIdMovOfProductoMovimientoListProductoMovimiento = em.merge(oldIdMovOfProductoMovimientoListProductoMovimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimiento movimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimiento persistentMovimiento = em.find(Movimiento.class, movimiento.getId());
            Usuario usuarioTransOld = persistentMovimiento.getUsuarioTrans();
            Usuario usuarioTransNew = movimiento.getUsuarioTrans();
            Cliente idClienteOld = persistentMovimiento.getIdCliente();
            Cliente idClienteNew = movimiento.getIdCliente();
            List<ProductoMovimiento> productoMovimientoListOld = persistentMovimiento.getProductoMovimientoList();
            List<ProductoMovimiento> productoMovimientoListNew = movimiento.getProductoMovimientoList();
            if (usuarioTransNew != null) {
                usuarioTransNew = em.getReference(usuarioTransNew.getClass(), usuarioTransNew.getId());
                movimiento.setUsuarioTrans(usuarioTransNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getId());
                movimiento.setIdCliente(idClienteNew);
            }
            List<ProductoMovimiento> attachedProductoMovimientoListNew = new ArrayList<ProductoMovimiento>();
            for (ProductoMovimiento productoMovimientoListNewProductoMovimientoToAttach : productoMovimientoListNew) {
                productoMovimientoListNewProductoMovimientoToAttach = em.getReference(productoMovimientoListNewProductoMovimientoToAttach.getClass(), productoMovimientoListNewProductoMovimientoToAttach.getId());
                attachedProductoMovimientoListNew.add(productoMovimientoListNewProductoMovimientoToAttach);
            }
            productoMovimientoListNew = attachedProductoMovimientoListNew;
            movimiento.setProductoMovimientoList(productoMovimientoListNew);
            movimiento = em.merge(movimiento);
            if (usuarioTransOld != null && !usuarioTransOld.equals(usuarioTransNew)) {
                usuarioTransOld.getMovimientoList().remove(movimiento);
                usuarioTransOld = em.merge(usuarioTransOld);
            }
            if (usuarioTransNew != null && !usuarioTransNew.equals(usuarioTransOld)) {
                usuarioTransNew.getMovimientoList().add(movimiento);
                usuarioTransNew = em.merge(usuarioTransNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getMovimientoList().remove(movimiento);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getMovimientoList().add(movimiento);
                idClienteNew = em.merge(idClienteNew);
            }
            for (ProductoMovimiento productoMovimientoListOldProductoMovimiento : productoMovimientoListOld) {
                if (!productoMovimientoListNew.contains(productoMovimientoListOldProductoMovimiento)) {
                    productoMovimientoListOldProductoMovimiento.setIdMov(null);
                    productoMovimientoListOldProductoMovimiento = em.merge(productoMovimientoListOldProductoMovimiento);
                }
            }
            for (ProductoMovimiento productoMovimientoListNewProductoMovimiento : productoMovimientoListNew) {
                if (!productoMovimientoListOld.contains(productoMovimientoListNewProductoMovimiento)) {
                    Movimiento oldIdMovOfProductoMovimientoListNewProductoMovimiento = productoMovimientoListNewProductoMovimiento.getIdMov();
                    productoMovimientoListNewProductoMovimiento.setIdMov(movimiento);
                    productoMovimientoListNewProductoMovimiento = em.merge(productoMovimientoListNewProductoMovimiento);
                    if (oldIdMovOfProductoMovimientoListNewProductoMovimiento != null && !oldIdMovOfProductoMovimientoListNewProductoMovimiento.equals(movimiento)) {
                        oldIdMovOfProductoMovimientoListNewProductoMovimiento.getProductoMovimientoList().remove(productoMovimientoListNewProductoMovimiento);
                        oldIdMovOfProductoMovimientoListNewProductoMovimiento = em.merge(oldIdMovOfProductoMovimientoListNewProductoMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movimiento.getId();
                if (findMovimiento(id) == null) {
                    throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.");
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
            Movimiento movimiento;
            try {
                movimiento = em.getReference(Movimiento.class, id);
                movimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioTrans = movimiento.getUsuarioTrans();
            if (usuarioTrans != null) {
                usuarioTrans.getMovimientoList().remove(movimiento);
                usuarioTrans = em.merge(usuarioTrans);
            }
            Cliente idCliente = movimiento.getIdCliente();
            if (idCliente != null) {
                idCliente.getMovimientoList().remove(movimiento);
                idCliente = em.merge(idCliente);
            }
            List<ProductoMovimiento> productoMovimientoList = movimiento.getProductoMovimientoList();
            for (ProductoMovimiento productoMovimientoListProductoMovimiento : productoMovimientoList) {
                productoMovimientoListProductoMovimiento.setIdMov(null);
                productoMovimientoListProductoMovimiento = em.merge(productoMovimientoListProductoMovimiento);
            }
            em.remove(movimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimiento> findMovimientoEntities() {
        return findMovimientoEntities(true, -1, -1);
    }

    public List<Movimiento> findMovimientoEntities(int maxResults, int firstResult) {
        return findMovimientoEntities(false, maxResults, firstResult);
    }

    private List<Movimiento> findMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimiento.class));
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

    public Movimiento findMovimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimiento> rt = cq.from(Movimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
