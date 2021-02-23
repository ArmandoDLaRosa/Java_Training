
package Controller;

import Entity.Ciudades;
import Entity.Vuelos;
import Facade.CiudadesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Armando De La Rosa | Enero 2021
 */
@Named(value = "ciudadesController")
@SessionScoped
public class CiudadesController implements Serializable {

    @EJB
    private CiudadesFacade ciudadesFacade;
    private Ciudades ciudad = new Ciudades();
    private boolean confirm = false;
    
    public List<Ciudades> findAllTyped(){
        return ciudadesFacade.findAllTyped();
    }

    public List<Ciudades> findAllNamed(){
        return ciudadesFacade.findAllNamed();
    }

    public List<Ciudades> findByEstado(){
        return ciudadesFacade.findAllByEstado("Nuevo León");
    }    
    
    public List<Ciudades> findAllByPais(){
        return ciudadesFacade.findAllByPais("United States");
    }        
        
    public String insert(){
        FacesMessage msj;
        try{
            // Esta Validación protege contra que existan dos o más ciudades con el mismo Nombre en el mismo Estado
            if(ciudadesFacade.findAllByEstadoANDName(ciudad.getNombre(), ciudad.getEstado().getNombre()).isEmpty()){
                ciudadesFacade.insert(ciudad);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de la ciudad: " + ciudad.getNombre() + ", fue agregado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("CiudadesAlta", msj);
                clean();                
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad no pudo ser agregado, porque la ciudad " + ciudad.getNombre() + " ya existe en el estado.", "");
                FacesContext.getCurrentInstance().addMessage("CiudadesAlta", msj);                   
            }
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad: " + ciudad.getNombre() + ", no pudo ser agregado", "");
            FacesContext.getCurrentInstance().addMessage("CiudadesAlta", msj);                        
        }
        return "";  // JSF PAGE A DONDE SE VA
    }    
    
    public String prepareEdit(Ciudades c){
        ciudad = c;
        return "CiudadesEdit";
    }
    
    public String update() {
        FacesMessage msj;
        try{
            // Esta Validación protege contra que existan dos o más ciudades con el mismo Nombre en el mismo Estado
            if(ciudadesFacade.findAllByEstadoANDName(ciudad.getNombre(), ciudad.getEstado().getNombre()).isEmpty()){
                ciudadesFacade.update(ciudad);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de la ciudad: " + ciudad.getNombre() + ", fue editado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("CiudadesEdit", msj);                
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad no pudo ser editado, porque la ciudad " + ciudad.getNombre() + " ya existe en el estado.", "");
                FacesContext.getCurrentInstance().addMessage("CiudadesEdit", msj);                   
            }       
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad: " + ciudad.getNombre() + ", no pudo ser editado", "");
            FacesContext.getCurrentInstance().addMessage("CiudadesEdit", msj);                        
        }
        return "";  // JSF PAGE A DONDE SE VA
    }
        
    public Ciudades find(Long id){
        return ciudadesFacade.find(id);
    }
    
    public String  mainClean(String url){
        ciudad = new Ciudades();
        setConfirm(false);
        return url;
    } 
    
    public void clean(){
        ciudad = new Ciudades();
    }

    public String prepareDelete(){
        setConfirm(true);
        return "CiudadesList";
    }

    public void delete(Ciudades c) {
        FacesMessage msj;
        try {
            if (c.getListaDestinos().isEmpty() || c.getListaOrigenes().isEmpty()) {
                ciudad = c;
                ciudadesFacade.delete(ciudad);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de la ciudad: " + c.getNombre() + ", fue eliminado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("CiudadesList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad: " + c.getNombre() + ", no pudo ser eliminado porque tiene dependientes", "");
                FacesContext.getCurrentInstance().addMessage("CiudadesList", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de la ciudad: " + c.getNombre() + ". no pudo ser eliminado", "");
            FacesContext.getCurrentInstance().addMessage("CiudadesList", msj);
        }
        mainClean("CiudadesList");
    }
    
    /**
     * @return the ciudad
     */
    public Ciudades getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
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
