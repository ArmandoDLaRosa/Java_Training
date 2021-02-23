
package Converter;

import Controller.CiudadesController;
import Entity.Ciudades;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Armando De La Rosa | Enero 2021
 */
@FacesConverter("CiudadRegister") 
// forClass=Ciudades.class, si quieres que aplique siempre con la clase,
// pero solo quiero que pase cuando inserto datos en Vuelos Alta. 
// Si se deja "forClass..." las TypedQuery mostrarían el ID y no el toString() de la clase
public class CiudadesConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        CiudadesController ciudadesController = context.getApplication().evaluateExpressionGet(context, "#{ciudadesController}", CiudadesController.class);
        return ciudadesController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return ""; // Never return null here!
        }        
        Ciudades ciudades  = (Ciudades) value;
        return ciudades.getId().toString();
    }
}
