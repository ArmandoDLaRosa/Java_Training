
package Controller;

import Entity.Aviones;
import Entity.Vuelos;
import Facade.AvionesFacade;
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
@Named(value = "avionesController")
@SessionScoped
public class AvionesController implements Serializable {

    @EJB
    private AvionesFacade avionesFacade;
    private Aviones avion = new Aviones();
    private boolean confirm = false;
            
    public List<Aviones> findAllTyped(){
        return avionesFacade.findAllTyped();
    }
    
    public List<Aviones> findAllNamed(){
        return avionesFacade.findAllNamed();
    }

    public List<Aviones> findByNumeroDeAvion(){
        return avionesFacade.findAllByNumeroDeAvion("2588");
    }
    
    public List<Aviones> findByNumAvionANDCapPasajeros(){
        return avionesFacade.findAllByNumAvionANDCapPasajeros("7456", 121);
    }
    
    public String insert(){
        FacesMessage msj;
        try{
            // Proteje contra que existan dos o más aviones con el mismo Número de avión.
            if(avionesFacade.findAllByNumeroDeAvion(avion.getNumeroDeAvion()).isEmpty()){
                avionesFacade.insert(avion);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del avión: " + avion.getNumeroDeAvion() + ", fue agregado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("AvionesAlta", msj);
                clean();                
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avión no pudo ser agregado, porque " + avion.getNumeroDeAvion() + " ya existe.", "");
                FacesContext.getCurrentInstance().addMessage("AvionesAlta", msj);                   
            }
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avión: " + avion.getNumeroDeAvion() + ", no pudo ser agregado", "");
            FacesContext.getCurrentInstance().addMessage("AvionesAlta", msj);                        
        }
        return "";  // JSF PAGE A DONDE SE VA
    }    
    
    public String prepareEdit(Aviones a){
        avion = a;
        return "AvionesEdit";
    }
    
    public String update() {
        FacesMessage msj;
        try{
            // Proteje contra que existan dos o más aviones con el mismo Número de avión.
            if(avionesFacade.findAllByNumeroDeAvion(avion.getNumeroDeAvion()).isEmpty()){
                avionesFacade.update(avion);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del avión: " + avion.getNumeroDeAvion() + ", fue editado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("AvionesEdit", msj);
            }  else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avión no pudo ser editado, porque " + avion.getNumeroDeAvion() + " ya existe.", "");
                FacesContext.getCurrentInstance().addMessage("AvionesEdit", msj);                    
            }
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avión: " + avion.getNumeroDeAvion() + ", no pudo ser editado", "");
            FacesContext.getCurrentInstance().addMessage("AvionesEdit", msj);                        
        }
        return "";  // JSF PAGE A DONDE SE VA
    }
    
    public String prepareDelete(){
        setConfirm(true);
        return "AvionesList";
    }
    
    public void delete(Aviones a) {
        FacesMessage msj;
        try {
            if (a.getListaVuelos().isEmpty()) {
                avion = a;
                avionesFacade.delete(avion);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del avión: " + a.getNumeroDeAvion() + ", fue eliminado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("AvionesList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avión: " + a.getNumeroDeAvion() + ", no pudo ser eliminado porque tiene dependientes", "");
                FacesContext.getCurrentInstance().addMessage("AvionesList", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del avión: " + a.getNumeroDeAvion() + ", no pudo ser eliminado", "");
            FacesContext.getCurrentInstance().addMessage("AvionesList", msj);
        }
        mainClean("AvionesList");
    }
        
    public Aviones find(Long id){
        return avionesFacade.find(id);
    }
    
    public String  mainClean(String url){
        avion = new Aviones();
        setConfirm(false);
        return url;
    }
    
    public void clean(){
        avion = new Aviones();
    }
        
    /**
     * @return the avion
     */
    public Aviones getAvion() {
        return avion;
    }

    /**
     * @param avion the avion to set
     */
    public void setAvion(Aviones avion) {
        this.avion = avion;
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
