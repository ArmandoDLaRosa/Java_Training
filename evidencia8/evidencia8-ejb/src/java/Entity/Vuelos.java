
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 * @author Armando De La Rosa | Enero 2021
 */

@Entity
@NamedQueries({
   @NamedQuery( name = "findVuelos", query = "SELECT v FROM Vuelos v"),
   // Write More...
})
@Table(name = "Vuelos") // schema= "AJCDLR2021" → Se pondría, pero luego no se ve el schema en el h2
public class Vuelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id //@Column(name = "idVuelo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numeroDeVuelo", nullable = false, columnDefinition = "VARCHAR(20)")
    private String numeroDeVuelo; 
     
    @JoinColumn(name = "numeroDeAvion", referencedColumnName = "id", nullable = false,  columnDefinition = "VARCHAR(20)")
    @ManyToOne(optional = false)
    private Aviones numeroDeAvion; 
    
    @JoinColumn(name = "origen",  referencedColumnName = "id", nullable = false,  columnDefinition = "VARCHAR(35)") 
    @ManyToOne(optional = false)
    private Ciudades origen; 
    
    @JoinColumn(name = "destino",  referencedColumnName = "id", nullable = false,  columnDefinition = "VARCHAR(35)")
    @ManyToOne(optional = false)
    private Ciudades destino; 
    
    @Column(name = "numeroDePasajeros", length = 3)
    private int  numeroDePasajeros; 
    
    @Column(name = "fechaInicioDeVuelo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicioDeVuelo; 
    
    @Column(name = "fechaFinDeVuelo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinDeVuelo; 
    
    @Column(name = "horaInicioDeVuelo")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaInicioDeVuelo; 
    
    @Column(name = "horaFinDeVuelo")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaFinDeVuelo;       
        
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
        if (!(object instanceof Vuelos)) {
            return false;
        }
        Vuelos other = (Vuelos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

     // El output es 1 línea sin saltos, se acomodó así para simplificar la lectura
    @Override
    public String toString() {
        return "VUELO   " + 
                        "       ID  " + id + 
                        "   |   Número Vuelo: " + getNumeroDeVuelo() + 
                        "   |   Número de Avión: " + getNumeroDeAvion().getNumeroDeAvion() +
                        "   |   Aerolínea: " + getNumeroDeAvion().getAerolinea() + 
                        "   |   Origen: " + getOrigen().getNombre()+ 
                        "   |   Destino: " + getDestino().getNombre();
    }

    /**
     * @return the numeroDeVuelo
     */
    public String getNumeroDeVuelo() {
        return numeroDeVuelo;
    }

    /**
     * @param numeroDeVuelo the numeroDeVuelo to set
     */
    public void setNumeroDeVuelo(String numeroDeVuelo) {
        this.numeroDeVuelo = numeroDeVuelo;
    }

    /**
     * @return the origen
     */
    public Ciudades getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Ciudades origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Ciudades getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Ciudades destino) {
        this.destino = destino;
    }

    /**
     * @return the numeroDePasajeros
     */
    public int getNumeroDePasajeros() {
        return numeroDePasajeros;
    }

    /**
     * @param numeroDePasajeros the numeroDePasajeros to set
     */
    public void setNumeroDePasajeros(int numeroDePasajeros) {
        this.numeroDePasajeros = numeroDePasajeros;
    }

    /**
     * @return the fechaInicioDeVuelo
     */
    public Date getFechaInicioDeVuelo() {
        return fechaInicioDeVuelo;
    }

    /**
     * @param fechaInicioDeVuelo the fechaInicioDeVuelo to set
     */
    public void setFechaInicioDeVuelo(Date fechaInicioDeVuelo) {
        this.fechaInicioDeVuelo = fechaInicioDeVuelo;
    }

    /**
     * @return the fechaFinDeVuelo
     */
    public Date getFechaFinDeVuelo() {
        return fechaFinDeVuelo;
    }

    /**
     * @param fechaFinDeVuelo the fechaFinDeVuelo to set
     */
    public void setFechaFinDeVuelo(Date fechaFinDeVuelo) {
        this.fechaFinDeVuelo = fechaFinDeVuelo;
    }

    /**
     * @return the horaInicioDeVuelo
     */
    public Date getHoraInicioDeVuelo() {
        return horaInicioDeVuelo;
    }

    /**
     * @param horaInicioDeVuelo the horaInicioDeVuelo to set
     */
    public void setHoraInicioDeVuelo(Date horaInicioDeVuelo) {
        this.horaInicioDeVuelo = horaInicioDeVuelo;
    }

    /**
     * @return the horaFinDeVuelo
     */
    public Date getHoraFinDeVuelo() {
        return horaFinDeVuelo;
    }

    /**
     * @param horaFinDeVuelo the horaFinDeVuelo to set
     */
    public void setHoraFinDeVuelo(Date horaFinDeVuelo) {
        this.horaFinDeVuelo = horaFinDeVuelo;
    }

    /**
     * @return the numeroDeAvion
     */
    public Aviones getNumeroDeAvion() {
        return numeroDeAvion;
    }

    /**
     * @param numeroDeAvion the numeroDeAvion to set
     */
    public void setNumeroDeAvion(Aviones numeroDeAvion) {
        this.numeroDeAvion = numeroDeAvion;
    }
    
}
