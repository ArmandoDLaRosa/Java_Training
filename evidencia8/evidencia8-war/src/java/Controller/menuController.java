
package Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 * @author Armando De La Rosa | Febrero 2021
 */
@Named(value = "menuController")
@SessionScoped
public class menuController implements Serializable {

    public String darBienvenida() {
        String nombreUsr = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
        String email =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("email");
        return "Bienvenido al menú 🙂" + nombreUsr + " - (" + email + ") ";
    }
}
