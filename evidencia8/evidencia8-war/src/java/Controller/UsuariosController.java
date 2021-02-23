
package Controller;

import Entity.Usuarios;
import Facade.UsuariosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Armando De La Rosa | Febrero 2021
 */
@Named(value = "usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;
    private Usuarios usuario = new Usuarios();
    private boolean confirm = false;
    ArrayList<Integer> perfilesList = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

    public List<Usuarios> findAllTyped(){
        return usuariosFacade.findAllTyped();
    }
    
    public String insert(){
        FacesMessage msj;
        try{
            // Protege contra perfiles injectados que no existen
            if(perfilesList.contains(usuario.getPerfil())){
                usuariosFacade.insert(usuario);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del usuario: " + usuario.getNombre() + ", fue agregado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("UsuariosAlta", msj);
                clean();                
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario no pudo ser agregado, porque el perfil " + usuario.getPerfil() + " no existe.", "");
                FacesContext.getCurrentInstance().addMessage("UsuariosAlta", msj);                    
            }
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario: " + usuario.getNombre() + ", no pudo ser agregado", "");
            FacesContext.getCurrentInstance().addMessage("UsuariosAlta", msj);            
        }
        return "";  // JSF PAGE A DONDE SE VA
    }     
    
    public String prepareEdit(Usuarios u){
        usuario = u;
        return "UsuariosEdit";
    }  
    
    public String update() {
        FacesMessage msj;
        try{
            if(perfilesList.contains(usuario.getPerfil())){            
                usuariosFacade.update(usuario);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del usuario: " + usuario.getNombre() + ", fue editado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("UsuariosEdit", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario no pudo ser editado, porque el perfil " + usuario.getPerfil() + " no existe.", "");
                FacesContext.getCurrentInstance().addMessage("UsuariosEdit", msj);                      
            }        
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario: " + usuario.getNombre() + ", no pudo ser editado", "");
            FacesContext.getCurrentInstance().addMessage("UsuariosEdit", msj);            
        }
        return "";  // JSF PAGE A DONDE SE VA
    }

    public Usuarios find(Long id){
        return usuariosFacade.find(id);
    }
    
    public String  mainClean(String url){
        usuario = new Usuarios();
        setConfirm(false);
        return url;
    }   
     
    public void clean(){
        usuario = new Usuarios();
    }

    public String prepareDelete(){
        setConfirm(true);
        return "UsuariosList";
    }

    public void delete(Usuarios u){
        FacesMessage msj;
        try{
            usuario = u;
            usuariosFacade.delete(usuario);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del usuario: " + u.getNombre() + ", fue eliminado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("UsuariosList", msj);
        } catch( Exception e){
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del usuario: " + u.getNombre() + ". no pudo ser eliminado", "");
            FacesContext.getCurrentInstance().addMessage("UsuariosList", msj);
        }
        mainClean("UsuariosList");
    }    
    /**
     * @return the usuario
     */
    public Usuarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the confirm
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
    
}
