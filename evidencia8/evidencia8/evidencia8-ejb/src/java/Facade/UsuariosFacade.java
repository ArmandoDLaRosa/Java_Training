
package Facade;

import Entity.Ciudades;
import Entity.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Armando De La Rosa | Enero 2021
 */
@Stateless
@LocalBean
public class UsuariosFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "evidencia8-ejbPU")
    private EntityManager em;
               
    public List<Usuarios> findAllTyped(){
        TypedQuery <Usuarios> query;
        query = em.createQuery("SELECT u FROM Usuarios u", Usuarios.class);
        return query.getResultList(); 
    }    
    
    public List<Usuarios> iniciarSesion(String email, String contraseña){
        TypedQuery query = em.createQuery("SELECT u FROM Usuarios u WHERE u.email=:email AND u.contraseña=:contraseña", Usuarios.class);
        query.setParameter("email", email);
        query.setParameter("contraseña", contraseña);
        return query.getResultList();
    } 
        
    public Usuarios find(Long id ) {
       return em.find(Usuarios.class, id);
    }    
    
    public void insert(Usuarios u){
        em.persist(u);
    }
    
    public void update(Usuarios u){
        em.merge(u);
    }   
    
    public void delete(Usuarios u){
        em.remove(em.merge(u));
    } 
}
