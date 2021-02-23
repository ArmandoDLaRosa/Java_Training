
package Converter;

import Controller.PaisesController;
import Entity.Paises;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Armando De La Rosa | Enero 2021
 */
@FacesConverter("PaisRegister") 
// forClass=Paises.class, si quieres que aplique siempre con la clase,
// pero solo quiero que pase cuando inserto datos en Vuelos Alta. 
// Si se deja "forClass..." las TypedQuery mostrarían el ID y no el toString() de la clase
public class PaisesConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        PaisesController paisesController = context.getApplication().evaluateExpressionGet(context, "#{paisesController}", PaisesController.class);
        return paisesController.find(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return ""; // Never return null here!
        }        
        Paises paises  = (Paises) value;
        return paises.getId().toString();
    }
}
