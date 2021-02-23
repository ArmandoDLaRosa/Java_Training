
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
@Named(value = "loginController")
@SessionScoped
public class loginController implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade = new UsuariosFacade();
    private Usuarios usuario = new Usuarios();
    private String correo = "";
    private String contra = "";
    private boolean authorized = false;
    ArrayList<Integer> perfilesList = new ArrayList<>(Arrays.asList(3));

    public String iniciarSesion(){
        
        List<Usuarios> user = usuariosFacade.iniciarSesion(this.correo, this.contra);
        if(user.isEmpty()){
                FacesMessage msj;
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Intente de nuevo, ha ingresado algún dato incorrecto.", "");
                FacesContext.getCurrentInstance().addMessage("index", msj);   
            return "index";
        } else {
            prepareMenu(user);            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombre", user.get(0).getNombre());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", user.get(0).getEmail());
            return "menu";                             
        }
    }
    public void prepareMenu(List<Usuarios> user) {
        usuario = user.get(0);
            if( perfilesList.contains(usuario.getPerfil()))  { 
                authorized = true;
            }    
   }

    public String  mainClean(String url){
        usuario = new Usuarios();
        setAuthorized(false);
        return url;
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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contra
     */
    public String getContra() {
        return contra;
    }

    /**
     * @param contra the contra to set
     */
    public void setContra(String contra) {
        this.contra = contra;
    }

    /**
     * @return the authorized
     */
    public boolean isAuthorized() {
        return authorized;
    }

    /**
     * @param authorized the authorized to set
     */
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
