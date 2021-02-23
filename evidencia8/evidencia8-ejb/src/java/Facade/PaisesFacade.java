
package Facade;

import Entity.Paises;
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
public class PaisesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method") 
    @PersistenceContext(unitName = "evidencia8-ejbPU")
    private EntityManager em;
    
    public List<Paises> findAllTyped(){
        TypedQuery <Paises> query;
        query = em.createQuery("SELECT p FROM Paises p", Paises.class);
        return query.getResultList(); 
    }
    
    public List<Paises> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findPaises");
        return query.getResultList();
    }
    
    // FUNCIONES NAMED QUERY con parámetros
    public List<Paises> findAllByNombre(String nombreParameter){
        Query query;
        query = em.createNamedQuery("findAllByNombre");
        query.setParameter("nombre", nombreParameter);
        return query.getResultList();
    } 
    
    public Paises find(Long id ) {
       return em.find(Paises.class, id);
    }    
    
    public void insert(Paises p){
        em.persist(p);
    }
    
    public void update(Paises p){
        em.merge(p);
    }    
    
    public void delete(Paises p){
        em.remove(em.merge(p));
    }    
}
