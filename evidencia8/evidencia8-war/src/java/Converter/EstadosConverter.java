
package Converter;

import Controller.EstadosController;
import Entity.Estados;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Armando De La Rosa | Enero 2021
 */
@FacesConverter("EstadoRegister") 
// forClass=Estados.class, si quieres que aplique siempre con la clase,
// pero solo quiero que pase cuando inserto datos en Vuelos Alta. 
// Si se deja "forClass..." las TypedQuery mostrarían el ID y no el toString() de la clase
public class EstadosConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        EstadosController estadosController = context.getApplication().evaluateExpressionGet(context, "#{estadosController}", EstadosController.class);
        return estadosController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return ""; // Never return null here!
        }        
        Estados estados  = (Estados) value;
        return estados.getId().toString();
    }
}
