
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
@NamedQueries({
   @NamedQuery( name = "findCiudades", query = "SELECT c FROM Ciudades c"),
   @NamedQuery( name = "findAllByPais", query = "SELECT c FROM Ciudades c WHERE c.estado.pais.nombre =:pais") 
   // Write More...
})
@Table(name = "Ciudades") 
public class Ciudades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id //@Column(name = "idCiudad") usarlo depende del diseño de la DB
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false, columnDefinition = "VARCHAR(35)")
    private String nombre; 
    
    @JoinColumn(name = "estado", referencedColumnName = "id", nullable = false, columnDefinition = "VARCHAR(35)")
    @ManyToOne(optional = false) 
    private Estados estado; 
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "origen")
    private List<Vuelos>  listaOrigenes;  
        
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "destino")
    private List<Vuelos>  listaDestinos;
    
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
        if (!(object instanceof Ciudades)) {
            return false;
        }
        Ciudades other = (Ciudades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CIUDAD   " + 
                        "       ID  " + id + 
                        "   |   Nombre: " + getNombre() + 
                        "   |   Estado: " + getEstado().getNombre();
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
     * @return the estado
     */
    public Estados getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    /**
     * @return the listaOrigenes
     */
    public List<Vuelos> getListaOrigenes() {
        return listaOrigenes;
    }

    /**
     * @param listaOrigenes the listaOrigenes to set
     */
    public void setListaOrigenes(List<Vuelos> listaOrigenes) {
        this.listaOrigenes = listaOrigenes;
    }

    /**
     * @return the listaDestinos
     */
    public List<Vuelos> getListaDestinos() {
        return listaDestinos;
    }

    /**
     * @param listaDestinos the listaDestinos to set
     */
    public void setListaDestinos(List<Vuelos> listaDestinos) {
        this.listaDestinos = listaDestinos;
    }
    
}
