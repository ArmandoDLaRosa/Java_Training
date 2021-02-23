
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Armando De La Rosa | Enero 2021
 */
@Entity
@NamedQueries(
   @NamedQuery( name = "findEstados", query = "SELECT e FROM Estados e")
   // Write More...
)
@Table(name = "Estados") 
public class Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id //@Column(name = "idEstado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false, columnDefinition = "VARCHAR(35)")
    private String nombre; 
    
    @JoinColumn(name = "pais", referencedColumnName = "id", nullable = false, columnDefinition = "VARCHAR(35)")
    @ManyToOne(optional = false) 
    private Paises pais; 
        
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "estado")
    private List<Ciudades>  listaCiudades;
    
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
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ESTADO   " + 
                        "       ID  " + id + 
                        "   |   Nombre: " + getNombre() + 
                        "   |   País: " + getPais().getNombre();
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
     * @return the pais
     */
    public Paises getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Paises pais) {
        this.pais = pais;
    }

    /**
     * @return the listaCiudades
     */
    public List<Ciudades> getListaCiudades() {
        return listaCiudades;
    }

    /**
     * @param listaCiudades the listaCiudades to set
     */
    public void setListaCiudades(List<Ciudades> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }
    
}
