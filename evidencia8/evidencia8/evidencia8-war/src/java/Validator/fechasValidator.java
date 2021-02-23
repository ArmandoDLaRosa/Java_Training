
package Validator;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@FacesValidator(value = "fechasValidator")
public class fechasValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo fecha no debe estar vacío", ""));           
        } else {
            if (value instanceof Date) {
                Date dte = (Date) value;
                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                String str = formatter.format(dte);
                Pattern ptr = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}"); //acepta formato correcto de fecha dd/MM/yyyy
                if (!ptr.matcher(str).matches()) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El dato [" + value.toString() + "] no sigue el formato dd/MM/yyyy", ""));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de dato no es aceptable", ""));
            }
        }
    }
}
