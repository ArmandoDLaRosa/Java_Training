
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
@FacesValidator(value = "codigoValidator")
public class codigoValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof String) {
            String str = (String) value;
            if (!str.matches("^[a-zA-Z0-9]+$")) {  //  se aceptan  letras y números
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El dato [" + value.toString() + "] tiene algo más que números y letras", ""));
            }
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de dato no es aceptable", ""));
        }
    }
}
