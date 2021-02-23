
package Facade;

import Entity.Ciudades;
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
public class CiudadesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method") 
    @PersistenceContext(unitName = "evidencia8-ejbPU")
    private EntityManager em;
    
    public List<Ciudades> findAllTyped(){
        TypedQuery <Ciudades> query;
        query = em.createQuery("SELECT c FROM Ciudades c", Ciudades.class);
        return query.getResultList(); 
    }
    
    public List<Ciudades> findAllNamed(){
        Query query;
        query = em.createNamedQuery("findCiudades");
        return query.getResultList();
    }
    
    // FUNCIONES TYPED QUERY con parámetros
    public List<Ciudades> findAllByEstado(String estadoParameter) {
        TypedQuery<Ciudades> query;
        query =  em.createQuery("SELECT c FROM Ciudades c WHERE UPPER(c.estado.nombre)=:estado", Ciudades.class);
        query.setParameter("estado", estadoParameter.toUpperCase());       
        /**
         * If there are more parameters, write each setParameter() here.\
         * The query would need AND after the WHERE for each new parameter.
         */
        return query.getResultList();   
    }

    public List<Ciudades> findAllByEstadoANDName(String ciudadParameter, String estadoParameter) {
        TypedQuery<Ciudades> query;
        query =  em.createQuery("SELECT c FROM Ciudades c WHERE UPPER(c.estado.nombre)=:estado AND UPPER(c.nombre)=:nombre", Ciudades.class);
        query.setParameter("estado", estadoParameter.toUpperCase());
        query.setParameter("nombre", ciudadParameter.toUpperCase());       
        /**
         * If there are more parameters, write each setParameter() here.\
         * The query would need AND after the WHERE for each new parameter.
         */
        return query.getResultList();   
    }  
    
// FUNCIONES NAMED QUERY con parámetros
    public List<Ciudades> findAllByPais(String paisParameter){
        Query query;
        query = em.createNamedQuery("findAllByPais");
        query.setParameter("pais", paisParameter);       
        return query.getResultList();
    }
    
    public Ciudades find(Long id ) {
       return em.find(Ciudades.class, id);
    }
    
    public void insert(Ciudades c){
        em.persist(c);
    }
    
    public void update(Ciudades c){
        em.merge(c);
    }   
    
    public void delete(Ciudades c){
        em.remove(em.merge(c));
    }
}
