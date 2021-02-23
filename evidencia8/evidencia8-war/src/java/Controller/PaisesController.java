
package Controller;

import Entity.Paises;
import Entity.Vuelos;
import Facade.PaisesFacade;
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
@Named(value = "paisesController")
@SessionScoped
public class PaisesController implements Serializable {

    @EJB
    private PaisesFacade paisesFacade;
    private Paises pais = new Paises();
    private boolean confirm = false;
    
    public List<Paises> findAllTyped(){
        return paisesFacade.findAllTyped();
    }

    public List<Paises> findAllNamed(){
        return paisesFacade.findAllNamed();
    }

    public List<Paises> findAllByNombre(){
        return paisesFacade.findAllByNombre("United States");
    }

    public String insert(){
        FacesMessage msj;
        try{
            paisesFacade.insert(pais);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del país: " + pais.getNombre() + ", fue agregado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("PaísesAlta", msj);
            clean();
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo: " + pais.getNombre() + ", no pudo ser agregado", "");
            FacesContext.getCurrentInstance().addMessage("PaísesAlta", msj);                
        }
        return "";  // JSF PAGE A DONDE SE VA
    }    
    
    public String prepareEdit(Paises p){
        pais = p;
        return "PaisesEdit";
    }
    
    public String update() {
        FacesMessage msj;
        try{
            paisesFacade.update(pais);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del país: " + pais.getNombre() + ", fue editado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("PaísesEdit", msj);
        } catch(Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo: " + pais.getNombre() + ", no pudo ser editado", "");
            FacesContext.getCurrentInstance().addMessage("PaísesEdit", msj);                
        }
        return "";  // JSF PAGE A DONDE SE VA
    }
        
    public Paises find(Long id){
        return paisesFacade.find(id);
    }

    public String  mainClean(String url){
        pais = new Paises();
        setConfirm(false);
        return url;
    }    
    
    public void clean(){
        pais = new Paises();
    }

    public String prepareDelete(){
        setConfirm(true);
        return "PaisesList";
    }
 
    public void delete(Paises p) {
        FacesMessage msj;
        try {
            if (p.getListaEstados().isEmpty()) {
                pais = p;
                paisesFacade.delete(pais);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del país: " + p.getNombre() + ", fue eliminado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del país: " + p.getNombre() + ", no pudo ser eliminado porque tiene dependientes", "");
                FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del país: " + p.getNombre() + ". no pudo ser eliminado", "");
            FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
        }
        mainClean("PaisesList");
    }

    /**
     * @return the pais
     */
    public Paises getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Paises pais) {
        this.pais = pais;
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
