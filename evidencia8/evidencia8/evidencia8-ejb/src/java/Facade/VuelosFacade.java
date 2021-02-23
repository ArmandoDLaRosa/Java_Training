
package Facade;

import Entity.Vuelos;
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
public class VuelosFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "evidencia8-ejbPU")
    private EntityManager em;
    
    public List<Vuelos> findAllTyped(){
        TypedQuery <Vuelos> query;
        query = em.createQuery("SELECT v FROM Vuelos v", Vuelos.class);
        return query.getResultList(); 
    }
    
    public List<Vuelos> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findVuelos");
        return query.getResultList();
    }
    
    // FUNCIONES TYPED QUERY con parámetros
    public List<Vuelos> findAllByNumeroDeVuelo(String numeroDeVueloParameter) {
        TypedQuery<Vuelos> query;
        query =  em.createQuery("SELECT v FROM Vuelos v WHERE v.numeroDeVuelo =:numeroDeVuelo", Vuelos.class);
        query.setParameter("numeroDeVuelo", numeroDeVueloParameter);       
        /**
         * If there are more parameters, write each setParameter() here.\
         * The query would need AND after the WHERE for each new parameter.
         */
        return query.getResultList();   
    }
    
    public Vuelos find(Long id ) {
       return em.find(Vuelos.class, id);
    }    
    
    public void insert(Vuelos v){
        em.persist(v);
    }
    
    public void update(Vuelos v){
        em.merge(v);
    }   
    
    public void delete(Vuelos v){
        em.remove(em.merge(v));
    }
}
