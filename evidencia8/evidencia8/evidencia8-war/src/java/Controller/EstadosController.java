
package Controller;

import Entity.Estados;
import Facade.EstadosFacade;
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
@Named(value = "estadosController")
@SessionScoped
public class EstadosController implements Serializable {

    @EJB
    private EstadosFacade estadosFacade;
    private Estados estado = new Estados();
    private boolean confirm = false;
    
    public List<Estados> findAllTyped(){
        return estadosFacade.findAllTyped();
    }

    public List<Estados> findAllNamed(){
        return estadosFacade.findAllNamed();
    }

    public String insert() {
        FacesMessage msj;
        try {
            estadosFacade.insert(estado);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del estado: " + estado.getNombre() + ", fue agregado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("EstadosAlta", msj);
            clean();
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del estado: " + estado.getNombre() + ", no pudo ser agregado", "");
            FacesContext.getCurrentInstance().addMessage("EstadosAlta", msj);
        }
        return "";  // JSF PAGE A DONDE SE VA
    }
    
    public String prepareEdit(Estados e){
        estado = e;
        return "EstadosEdit";
    }
    
    public String update() {
        FacesMessage msj;
        try{
            estadosFacade.update(estado);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del estado: " + estado.getNombre() + ", fue editado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("EstadosEdit", msj);
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del estado: " + estado.getNombre() + ", no pudo ser editado", "");
            FacesContext.getCurrentInstance().addMessage("EstadosEdit", msj);                        
        }
        return "";  // JSF PAGE A DONDE SE VA
    }
        
    public Estados find(Long id){
        return estadosFacade.find(id);
    }
    
    public String  mainClean(String url){
        estado = new Estados();
        setConfirm(false);
        return url;
    }   
    
    public void clean(){
        estado = new Estados();
    } 

    public String prepareDelete(){
        setConfirm(true);
        return "EstadosList";
    }

    public void delete(Estados e) {
        FacesMessage msj;
        try {
            if (e.getListaCiudades().isEmpty()) {
                estado = e;
                estadosFacade.delete(estado);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del estado: " + e.getNombre() + ", fue eliminado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("EstadosList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del estado: " + e.getNombre() + ", no pudo ser eliminado porque tiene dependientes", "");
                FacesContext.getCurrentInstance().addMessage("EstadosList", msj);
            }
        } catch (Exception o) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del estado: " + e.getNombre() + ". no pudo ser eliminado", "");
            FacesContext.getCurrentInstance().addMessage("EstadosList", msj);
        }
        mainClean("EstadosList");
    }
    
    /**
     * @return the estado
     */
    public Estados getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estados estado) {
        this.estado = estado;
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
