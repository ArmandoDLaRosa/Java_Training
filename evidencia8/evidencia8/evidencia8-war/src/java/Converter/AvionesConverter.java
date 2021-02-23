
package Converter;

import Controller.AvionesController;
import Entity.Aviones;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Armando De La Rosa | Enero 2021
 */
@FacesConverter("AvionRegister") 
// forClass=Aviones.class, si quieres que aplique siempre con la clase,
// pero solo quiero que pase cuando inserto datos en Vuelos Alta. 
// Si se deja "forClass..." las TypedQuery mostrarían el ID y no el toString() de la clase
public class AvionesConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        AvionesController avionesController = context.getApplication().evaluateExpressionGet(context, "#{avionesController}", AvionesController.class);
        return avionesController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return ""; // Never return null here!
        }        
        Aviones aviones  = (Aviones) value;
        return aviones.getId().toString();
    }
}
