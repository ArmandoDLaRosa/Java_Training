
package Validator;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Armando De La Rosa | Febrero 2021
 */

@FacesValidator(value = "palabrasValidator")
public class palabrasValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof String) {
            String str = (String) value;
            Pattern ptr = Pattern.compile("^[a-zA-Z\\p{L} ]+([a-zA-Z\\p{L} ]+)*$"); //  acepta palabras y espacios en blanco
            if (!ptr.matcher(str).matches()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El dato [" + value.toString() + "] tiene algo más que espacios en blanco y palabras", ""));
            }
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de dato no es aceptable", ""));
        }
    }
}
