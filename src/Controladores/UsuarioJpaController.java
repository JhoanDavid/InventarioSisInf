/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Movimiento;
import Entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jramirez
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InventarioSisInfPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getMovimientoList() == null) {
            usuario.setMovimientoList(new ArrayList<Movimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Movimiento> attachedMovimientoList = new ArrayList<Movimiento>();
            for (Movimiento movimientoListMovimientoToAttach : usuario.getMovimientoList()) {
                movimientoListMovimientoToAttach = em.getReference(movimientoListMovimientoToAttach.getClass(), movimientoListMovimientoToAttach.getId());
                attachedMovimientoList.add(movimientoListMovimientoToAttach);
            }
            usuario.setMovimientoList(attachedMovimientoList);
            em.persist(usuario);
            for (Movimiento movimientoListMovimiento : usuario.getMovimientoList()) {
                Usuario oldUsuarioTransOfMovimientoListMovimiento = movimientoListMovimiento.getUsuarioTrans();
                movimientoListMovimiento.setUsuarioTrans(usuario);
                movimientoListMovimiento = em.merge(movimientoListMovimiento);
                if (oldUsuarioTransOfMovimientoListMovimiento != null) {
                    oldUsuarioTransOfMovimientoListMovimiento.getMovimientoList().remove(movimientoListMovimiento);
                    oldUsuarioTransOfMovimientoListMovimiento = em.merge(oldUsuarioTransOfMovimientoListMovimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getId()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Movimiento> movimientoListOld = persistentUsuario.getMovimientoList();
            List<Movimiento> movimientoListNew = usuario.getMovimientoList();
            List<Movimiento> attachedMovimientoListNew = new ArrayList<Movimiento>();
            for (Movimiento movimientoListNewMovimientoToAttach : movimientoListNew) {
                movimientoListNewMovimientoToAttach = em.getReference(movimientoListNewMovimientoToAttach.getClass(), movimientoListNewMovimientoToAttach.getId());
                attachedMovimientoListNew.add(movimientoListNewMovimientoToAttach);
            }
            movimientoListNew = attachedMovimientoListNew;
            usuario.setMovimientoList(movimientoListNew);
            usuario = em.merge(usuario);
            for (Movimiento movimientoListOldMovimiento : movimientoListOld) {
                if (!movimientoListNew.contains(movimientoListOldMovimiento)) {
                    movimientoListOldMovimiento.setUsuarioTrans(null);
                    movimientoListOldMovimiento = em.merge(movimientoListOldMovimiento);
                }
            }
            for (Movimiento movimientoListNewMovimiento : movimientoListNew) {
                if (!movimientoListOld.contains(movimientoListNewMovimiento)) {
                    Usuario oldUsuarioTransOfMovimientoListNewMovimiento = movimientoListNewMovimiento.getUsuarioTrans();
                    movimientoListNewMovimiento.setUsuarioTrans(usuario);
                    movimientoListNewMovimiento = em.merge(movimientoListNewMovimiento);
                    if (oldUsuarioTransOfMovimientoListNewMovimiento != null && !oldUsuarioTransOfMovimientoListNewMovimiento.equals(usuario)) {
                        oldUsuarioTransOfMovimientoListNewMovimiento.getMovimientoList().remove(movimientoListNewMovimiento);
                        oldUsuarioTransOfMovimientoListNewMovimiento = em.merge(oldUsuarioTransOfMovimientoListNewMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Movimiento> movimientoList = usuario.getMovimientoList();
            for (Movimiento movimientoListMovimiento : movimientoList) {
                movimientoListMovimiento.setUsuarioTrans(null);
                movimientoListMovimiento = em.merge(movimientoListMovimiento);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario traerUsuario(Long cedula) {
        Usuario u = null;
        try {
            EntityManager em = getEntityManager();
            Query q = em.createNamedQuery("Usuario.cambio", Usuario.class);
            q.setParameter("id", cedula);
            u = (Usuario) q.getSingleResult();
            return u;
        } catch (Exception e) {
            System.out.println(" Error al traer el usuario " + e.getMessage());
        }
        return u;
    }

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Usuario Login(String user, String pass) {

        Usuario u = null;
        try {
            EntityManager em = getEntityManager();
            Query q = em.createNamedQuery("Usuario.login", Usuario.class);
            q.setParameter("user", user);
            q.setParameter("password", pass);
            u = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("error al encontrar el usuario: " + e);
        }
        return u;
    }

}
