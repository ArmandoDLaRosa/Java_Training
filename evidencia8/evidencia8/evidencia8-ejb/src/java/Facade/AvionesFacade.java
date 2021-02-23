
package Facade;

import Entity.Aviones;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Armando De La Rosa | Enero 2021
 */

@Stateless
@LocalBean
public class AvionesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")    
    @PersistenceContext(unitName = "evidencia8-ejbPU")
    private EntityManager em;
    
    public List<Aviones> findAllTyped(){
        TypedQuery <Aviones> query;
        query = em.createQuery("SELECT a FROM Aviones a", Aviones.class);
        return query.getResultList(); 
    }
    
    public List<Aviones> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findAviones");
        return query.getResultList();
    }
    
    // FUNCIONES TYPED QUERY con parámetros
    public List<Aviones> findAllByNumeroDeAvion(String numeroDeAvionParameter) {
        TypedQuery<Aviones> query;
        query =  em.createQuery("SELECT a FROM Aviones a WHERE a.numeroDeAvion =:numeroDeAvion", Aviones.class);
        query.setParameter("numeroDeAvion", numeroDeAvionParameter);       
        /**
         * If there are more parameters, write each setParameter() here.\
         * The query would need AND after the WHERE for each new parameter.
         */
        return query.getResultList();   
    }
    
    // FUNCIONES NAMED QUERY con parámetros
    public List<Aviones> findAllByNumAvionANDCapPasajeros(String numeroDeAvionParameter, int  capacidadDePasajerosParameter){
        Query query;
        query = em.createNamedQuery("findAllByNumAvionANDCapPasajeros");
        query.setParameter("numeroDeAvion", numeroDeAvionParameter);
        query.setParameter("capacidadDePasajeros", capacidadDePasajerosParameter);
        return query.getResultList();
    }

    public Aviones find(Long id ) {
       return em.find(Aviones.class, id);
    }
    
    public void insert(Aviones a){
        em.persist(a);
    }
    
    public void update(Aviones a){
        em.merge(a);
    }   
    
    public void delete(Aviones a){
        em.remove(em.merge(a));
    }    
}
