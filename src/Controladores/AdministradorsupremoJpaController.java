/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Administradorsupremo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jramirez
 */
public class AdministradorsupremoJpaController implements Serializable {

    public AdministradorsupremoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InventarioSisInfPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administradorsupremo administradorsupremo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(administradorsupremo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administradorsupremo administradorsupremo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            administradorsupremo = em.merge(administradorsupremo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administradorsupremo.getId();
                if (findAdministradorsupremo(id) == null) {
                    throw new NonexistentEntityException("The administradorsupremo with id " + id + " no longer exists.");
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
            Administradorsupremo administradorsupremo;
            try {
                administradorsupremo = em.getReference(Administradorsupremo.class, id);
                administradorsupremo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administradorsupremo with id " + id + " no longer exists.", enfe);
            }
            em.remove(administradorsupremo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administradorsupremo> findAdministradorsupremoEntities() {
        return findAdministradorsupremoEntities(true, -1, -1);
    }

    public List<Administradorsupremo> findAdministradorsupremoEntities(int maxResults, int firstResult) {
        return findAdministradorsupremoEntities(false, maxResults, firstResult);
    }

    private List<Administradorsupremo> findAdministradorsupremoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administradorsupremo.class));
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

    public Administradorsupremo findAdministradorsupremo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administradorsupremo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorsupremoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administradorsupremo> rt = cq.from(Administradorsupremo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
