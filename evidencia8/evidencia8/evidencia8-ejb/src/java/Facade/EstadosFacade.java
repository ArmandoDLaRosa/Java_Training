
package Facade;


import Entity.Estados;
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
public class EstadosFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")    
    @PersistenceContext(unitName = "evidencia8-ejbPU")
    private EntityManager em;
    
    public List<Estados> findAllTyped(){
        TypedQuery <Estados> query;
        query = em.createQuery("SELECT e FROM Estados e", Estados.class);
        return query.getResultList(); 
    }
    
    public List<Estados> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findEstados");
        return query.getResultList();
    }

    public List<Estados> findAllByNombre(String nombreParameter) {
        TypedQuery<Estados> query;
        query =  em.createQuery("SELECT e FROM Estados e WHERE UPPER(e.nombre)=:nombre", Estados.class);
        query.setParameter("nombre", nombreParameter.toUpperCase());       
        /**
         * If there are more parameters, write each setParameter() here.\
         * The query would need AND after the WHERE for each new parameter.
         */
        return query.getResultList();   
    }    
    
    public Estados find(Long id ) {
       return em.find(Estados.class, id);
    }

    public void insert(Estados e){
        em.persist(e);
    }
    
    public void update(Estados e){
        em.merge(e);
    }  

    public void delete(Estados e){
        em.remove(em.merge(e));
    }    
}
