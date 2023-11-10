package org.example.persistencia;

import org.example.logica.Empleado;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class EmpleadoJpaController {
    private EntityManagerFactory emf = null;

    //Constructor
    public EmpleadoJpaController() {
        //Obtener la unidad de persistencia
        this.emf = createEntityManagerFactory("empleadosPU");
    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }


    //Metodos para interactuar con la BD
    //Crear
    public void create(Empleado empleado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(empleado);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


    //Eliminar
    public void destroy(Long id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Empleado empleado = em.find(Empleado.class, id);
            if (empleado != null) {
                em.remove(empleado);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    //Editar
    public void edit(Empleado empleado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(empleado);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    //Listar
    public List<Empleado> findEmpleadoEntities() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            CriteriaQuery<Empleado> cq = em.getCriteriaBuilder().createQuery(Empleado.class);
            cq.select(cq.from(Empleado.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    //Obtener empleado por ID
    public Empleado findEmpleado(Long id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            return em.find(Empleado.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    //Buscar por cargo
    public List<Empleado> searchByCharge(String cargo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e WHERE e.cargo = :cargo", Empleado.class);
            query.setParameter("cargo", cargo);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
