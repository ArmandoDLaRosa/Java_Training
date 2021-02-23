
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
   @NamedQuery( name = "findAviones", query = "SELECT a FROM Aviones a"),
   @NamedQuery(name = "findAllByNumAvionANDCapPasajeros", query = "SELECT a FROM Aviones a WHERE a.numeroDeAvion =:numeroDeAvion AND a.capacidadDePasajeros =:capacidadDePasajeros")
   // Write More...
})
@Table(name = "Aviones") // schema = " " , pero no jala en h2
public class Aviones implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id //@Column(name = "idAvion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numeroDeAvion", nullable = false, columnDefinition = "VARCHAR(20)")
    private String numeroDeAvion;    
    
    @Column(name = "capacidadDePasajeros", nullable = false, length = 3)
    private int  capacidadDePasajeros; 
    
    @Column(name = "modelo", nullable = false, columnDefinition = "VARCHAR(25)")
    private String modelo;    
    
    @Column(name = "aerolinea", nullable = false, columnDefinition = "VARCHAR(35)")
    private String aerolinea;  
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "numeroDeAvion")
    private List<Vuelos>  listaVuelos;
    
    /**
     * @return the numeroDeAvion
     */
    public String getNumeroDeAvion() {
        return numeroDeAvion;
    }

    /**
     * @param numeroDeAvion the numeroDeAvion to set
     */
    public void setNumeroDeAvion(String numeroDeAvion) {
        this.numeroDeAvion = numeroDeAvion;
    }

    /**
     * @return the capacidadDePasajeros
     */
    public int getCapacidadDePasajeros() {
        return capacidadDePasajeros;
    }

    /**
     * @param capacidadDePasajeros the capacidadDePasajeros to set
     */
    public void setCapacidadDePasajeros(int capacidadDePasajeros) {
        this.capacidadDePasajeros = capacidadDePasajeros;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the aerolinea
     */
    public String getAerolinea() {
        return aerolinea;
    }

    /**
     * @param aerolinea the aerolinea to set
     */
    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }
    
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
        if (!(object instanceof Aviones)) {
            return false;
        }
        Aviones other = (Aviones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AVIÓN   " + 
                        "       ID  " + id + 
                        "   |   Número de Avión: " + getNumeroDeAvion() +
                        "   |   Capacidad de Pasajeros: " + getCapacidadDePasajeros();
    }

    /**
     * @return the listaVuelos
     */
    public List<Vuelos> getListaVuelos() {
        return listaVuelos;
    }

    /**
     * @param listaVuelos the listaVuelos to set
     */
    public void setListaVuelos(List<Vuelos> listaVuelos) {
        this.listaVuelos = listaVuelos;
    }
    
}
