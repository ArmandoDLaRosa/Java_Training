
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

@FacesValidator(value = "numerosValidator")
public class numerosValidator implements Validator {

    // Este VALIDATOR solo funciona para atributos que son STRING;
    // Los atributos que son tipo integer arrojan primero un error de conversión si
    // tienen algo más que números, si se puso esto en un validotr para un atributo INTEGER
    // estará acompañado de un converterMessage. 
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value != null) {
            if (!(value instanceof Number)) {
                String str = (String) value;
                if (!str.matches("[0-9]+")) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El dato [" + value.toString() + "] tiene algo más que números", ""));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de dato no es aceptable", ""));
            }
        } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "No has ingresado un dato", ""));
        }
    }
}
