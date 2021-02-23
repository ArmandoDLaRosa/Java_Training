
package Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Armando De La Rosa | Febrero 2021
 */
@FacesValidator(value = "nombresValidator")
public class nombresValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof String) {
            String str = (String) value;
            if (!str.matches("^[a-zA-Z\\p{L} \\-\\.\\']+$")) { //acepta solamente letras, enseguida un espacio en blanco opcional y luego mas letras y así sucesivamente
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El dato [" + value.toString() + "] tiene algo más grupos de letras separados por espacios", ""));
            }
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de dato no es aceptable", ""));
        }
    }
}
