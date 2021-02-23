
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Armando De La Rosa | Enero 2021
 */

@Entity
@NamedQueries({
   @NamedQuery( name = "findPaises", query = "SELECT p FROM Paises p"),
   @NamedQuery( name = "findAllByNombre", query =  "SELECT p FROM Paises p WHERE p.nombre =:nombre")
   // Write More...
})
@Table(name = "Paises") 
public class Paises implements Serializable {

    private static final long serialVersionUID = 1L; 
    @Id //@Column(name = "idPais")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, columnDefinition = "VARCHAR(35)")
    private String nombre; 
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "pais")
    private List<Estados>  listaEstados;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paises)) {
            return false;
        }
        Paises other = (Paises) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PAÍS   " + 
                        "       ID  " + id + 
                        "   |   Nombre: " + getNombre();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the listaEstados
     */
    public List<Estados> getListaEstados() {
        return listaEstados;
    }

    /**
     * @param listaEstados the listaEstados to set
     */
    public void setListaEstados(List<Estados> listaEstados) {
        this.listaEstados = listaEstados;
    }
    
}
