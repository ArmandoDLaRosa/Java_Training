
package Controller;

import Entity.Vuelos;
import Facade.VuelosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 * @author Armando De La Rosa | Enero 2021
 */
@Named(value = "vuelosController")
@SessionScoped
public class VuelosController implements Serializable {

    @EJB
    private VuelosFacade vuelosFacade;
    private Vuelos vuelo = new Vuelos();
    private boolean confirm = false;
    
    public List<Vuelos> findAllTyped(){
        return vuelosFacade.findAllTyped();
    }

    public List<Vuelos> findAllNamed(){
        return vuelosFacade.findAllNamed();
    }
    
    public List<Vuelos> findByNumeroDeVuelo(){
        return vuelosFacade.findAllByNumeroDeVuelo("2");
    }
    
    public String insert() {
        FacesMessage msj;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hour = new SimpleDateFormat("HH:mm");

            // Fecha FIN DE VUELO PREPARATION FOR COMPARISION → could become a class
            String fechaFin = sdf.format(vuelo.getFechaFinDeVuelo());
            String Fin[] = fechaFin.split("/");
            LocalDate finVueloFecha = LocalDate.of(Integer.parseInt(Fin[2]), Integer.parseInt(Fin[1]), Integer.parseInt(Fin[0]));

            // hora FIN DE VUELO PREPARATION FOR COMPARISION → could become a class
            String horaFin = hour.format(vuelo.getHoraFinDeVuelo());
            String hFin[] = horaFin.split(":");
            LocalTime horaFinVuelo = LocalTime.of(Integer.parseInt(hFin[0]), Integer.parseInt(hFin[1]));

            // Fecha INICIO DE VUELO PREPARATION FOR COMPARISION → could become a class            
            String fechaInicio = sdf.format(vuelo.getFechaInicioDeVuelo());
            String Inicio[] = fechaInicio.split("/");
            LocalDate inicioVueloFecha = LocalDate.of(Integer.parseInt(Inicio[2]), Integer.parseInt(Inicio[1]), Integer.parseInt(Inicio[0]));

            // hora INICIO DE VUELO PREPARATION FOR COMPARISION → could become a class            
            String horaInicio = hour.format(vuelo.getHoraInicioDeVuelo());
            String hInicio[] = horaInicio.split(":");
            LocalTime horaInicioVuelo = LocalTime.of(Integer.parseInt(hInicio[0]), Integer.parseInt(hInicio[1]));

            LocalTime horaFinVueloMenosUNO = horaFinVuelo.minusHours(1);
            boolean dateIsBefore = inicioVueloFecha.isBefore(finVueloFecha);
            boolean datesAreEqual = inicioVueloFecha.isEqual(finVueloFecha);
            if (dateIsBefore == (true) || datesAreEqual == (true)) {
                if (!vuelo.getOrigen().getNombre().equals(vuelo.getDestino().getNombre())) {        // Protege contra el Origen y el Destino siendo iguales
                    if (vuelosFacade.findAllByNumeroDeVuelo(vuelo.getNumeroDeVuelo()).isEmpty()) {  // Protege contra 2 Numeros de Vuelos Iguales
                        if (datesAreEqual == false) {  // Si las fechas son diferentes no se validan horas
                            vuelosFacade.insert(vuelo);
                            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo: " + vuelo.getNumeroDeVuelo() + ", fue agregado exitosamente.", "");
                            FacesContext.getCurrentInstance().addMessage("VuelosAlta", msj);
                            clean();
                        } else { // Si las fechas son iguales se validan horas
                            if (horaInicioVuelo.isBefore(horaFinVuelo)) { // Hace la primera evaluación, así el usuario sabe si el error fue por la hora que debe haber de diferencia o por que la hora fin es mayor
                                // Este if protege contra la diferencia de horas siendo menor que 1. 
                                // El AND está porque restar 1 hora al 00:00 nos lleva al 23:00 dando casos donde se guardaría, así que conesto lo evitamoss. 
                                if ((horaInicioVuelo.isBefore(horaFinVueloMenosUNO) || horaInicioVuelo.equals(horaFinVueloMenosUNO)) && horaFinVueloMenosUNO.getHour() != 23) { // Prote
                                    vuelosFacade.insert(vuelo);
                                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo: " + vuelo.getNumeroDeVuelo() + ", fue agregado exitosamente.", "");
                                    FacesContext.getCurrentInstance().addMessage("VuelosAlta", msj);
                                    clean();
                                } else {
                                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser agregado, porque la HORA INICIO no es 1 hora antes de la HORA FIN", "");
                                    FacesContext.getCurrentInstance().addMessage("VuelosAlta", msj);
                                }
                            } else {
                                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser agregado, porque la HORA INICIO no es antes de la HORA FIN", "");
                                FacesContext.getCurrentInstance().addMessage("VuelosAlta", msj);
                            }
                        }
                    } else {
                        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser agregado, porque el vuelo " + vuelo.getNumeroDeVuelo() + " ya existe.", "");
                        FacesContext.getCurrentInstance().addMessage("VuelosAlta", msj);
                    }
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser agregado, porque el origen " + vuelo.getOrigen().getNombre() + " y el destino " + vuelo.getOrigen().getNombre() + " son iguales.", "");
                    FacesContext.getCurrentInstance().addMessage("VuelosAlta", msj);
                }
            } else {

                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser agregado, porque la FECHA INICIO es después que la FECHA FIN del vuelo.", "");
                FacesContext.getCurrentInstance().addMessage("VuelosAlta", msj);
            }
        } catch (NumberFormatException e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo: " + vuelo.getNumeroDeVuelo() + ", no pudo ser agregado.", "");
            FacesContext.getCurrentInstance().addMessage("VuelosAlta", msj);
        }
        return "";  // JSF PAGE A DONDE SE VA
    }
    
    public String prepareEdit(Vuelos v){
        vuelo = v;
        return "VuelosEdit";
    }
    
    public String update() {
        FacesMessage msj;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hour = new SimpleDateFormat("HH:mm");

            // Fecha FIN DE VUELO PREPARATION FOR COMPARISION → could become a class
            String fechaFin = sdf.format(vuelo.getFechaFinDeVuelo());
            String Fin[] = fechaFin.split("/");
            LocalDate finVueloFecha = LocalDate.of(Integer.parseInt(Fin[2]), Integer.parseInt(Fin[1]), Integer.parseInt(Fin[0]));

            // hora FIN DE VUELO PREPARATION FOR COMPARISION → could become a class
            String horaFin = hour.format(vuelo.getHoraFinDeVuelo());
            String hFin[] = horaFin.split(":");
            LocalTime horaFinVuelo = LocalTime.of(Integer.parseInt(hFin[0]), Integer.parseInt(hFin[1]));

            // Fecha INICIO DE VUELO PREPARATION FOR COMPARISION → could become a class            
            String fechaInicio = sdf.format(vuelo.getFechaInicioDeVuelo());
            String Inicio[] = fechaInicio.split("/");
            LocalDate inicioVueloFecha = LocalDate.of(Integer.parseInt(Inicio[2]), Integer.parseInt(Inicio[1]), Integer.parseInt(Inicio[0]));

            // hora INICIO DE VUELO PREPARATION FOR COMPARISION → could become a class            
            String horaInicio = hour.format(vuelo.getHoraInicioDeVuelo());
            String hInicio[] = horaInicio.split(":");
            LocalTime horaInicioVuelo = LocalTime.of(Integer.parseInt(hInicio[0]), Integer.parseInt(hInicio[1]));

            LocalTime horaFinVueloMenosUNO = horaFinVuelo.minusHours(1);
            boolean dateIsBefore = inicioVueloFecha.isBefore(finVueloFecha);
            boolean datesAreEqual = inicioVueloFecha.isEqual(finVueloFecha);
            if (dateIsBefore == (true) || datesAreEqual == (true)) {
                if (!vuelo.getOrigen().getNombre().equals(vuelo.getDestino().getNombre())) {         // Protege contra el Origen y el Destino siendo iguales
                    if (vuelosFacade.findAllByNumeroDeVuelo(vuelo.getNumeroDeVuelo()).isEmpty()) {   // Protege contra 2 Numeros de Vuelos Iguales
                        if (datesAreEqual == false) {  // Si las fechas son diferentes no se validan horas
                            vuelosFacade.update(vuelo);
                            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo: " + vuelo.getNumeroDeVuelo() + ", fue editado exitosamente.", "");
                            FacesContext.getCurrentInstance().addMessage("VuelosEdit", msj);
                        } else { // Si las fechas son iguales se validan horas
                            if (horaInicioVuelo.isBefore(horaFinVuelo)) { // Hace la primera evaluación, así el usuario sabe si el error fue por la hora que debe haber de diferencia o por que la hora fin es mayor
                                // Este if protege contra la diferencia de horas siendo menor que 1. 
                                // El AND está porque restar 1 hora al 00:00 nos lleva al 23:00 dando casos donde se guardaría, así que conesto lo evitamoss. 
                                if ((horaInicioVuelo.isBefore(horaFinVueloMenosUNO) || horaInicioVuelo.equals(horaFinVueloMenosUNO)) && horaFinVueloMenosUNO.getHour() != 23) { // Prote
                                    vuelosFacade.update(vuelo);
                                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo: " + vuelo.getNumeroDeVuelo() + ", fue editado exitosamente.", "");
                                    FacesContext.getCurrentInstance().addMessage("VuelosEdit", msj);
                                } else {
                                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser editado, porque la HORA INICIO no es 1 hora antes de la HORA FIN", "");
                                    FacesContext.getCurrentInstance().addMessage("VuelosEdit", msj);
                                }
                            } else {
                                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser editado, porque la HORA INICIO no es antes de la HORA FIN", "");
                                FacesContext.getCurrentInstance().addMessage("VuelosEdit", msj);
                            }
                        }
                    } else {
                        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser editado, porque el vuelo " + vuelo.getNumeroDeVuelo() + " ya existe.", "");
                        FacesContext.getCurrentInstance().addMessage("VuelosEdit", msj);
                    }
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser editado, porque el origen " + vuelo.getOrigen().getNombre() + " y el destino " + vuelo.getOrigen().getNombre() + " son iguales.", "");
                    FacesContext.getCurrentInstance().addMessage("VuelosEdit", msj);
                }
            } else {

                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo no pudo ser editado, porque la FECHA INICIO es después que la FECHA FIN del vuelo.", "");
                FacesContext.getCurrentInstance().addMessage("VuelosEdit", msj);
            }
        } catch (NumberFormatException e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo: " + vuelo.getNumeroDeVuelo() + ", no pudo ser editado.", "");
            FacesContext.getCurrentInstance().addMessage("VuelosEdit", msj);
        }
        return "";  // JSF PAGE A DONDE SE VA
    }

    public Vuelos find(Long id){
        return vuelosFacade.find(id);
    }
    
    public String  mainClean(String url){
        vuelo = new Vuelos();
        setConfirm(false);
        return url;
    }   
     
    public void clean(){
        vuelo = new Vuelos();
    }

    public String prepareDelete(){
        setConfirm(true);
        return "VuelosList";
    }

    public void delete(Vuelos v){
        FacesMessage msj;
        try{
            vuelo = v;
            vuelosFacade.delete(vuelo);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro del vuelo: " + v.getNumeroDeVuelo() + ", fue eliminado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage("VuelosList", msj);
        } catch( Exception e){
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro del vuelo: " + v.getNumeroDeVuelo() + ". no pudo ser eliminado", "");
            FacesContext.getCurrentInstance().addMessage("VuelosList", msj);
        }
        mainClean("VuelosList");
    }
    
    /**
     * @return the vuelo
     */
    public Vuelos getVuelo() {
        return vuelo;
    }

    /**
     * @param vuelo the vuelo to set
     */
    public void setVuelo(Vuelos vuelo) {
        this.vuelo = vuelo;
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
