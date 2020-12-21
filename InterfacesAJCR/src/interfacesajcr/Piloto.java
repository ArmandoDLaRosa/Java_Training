package interfacesajcr;

/**
 * @author Armando De La Rosa
 */
public class Piloto implements Interfaz 
{

    private String nombre;
    private String aniosVolando;

    /**
     *  Constructor por default Piloto
     */
    public Piloto()
    {
        this("", "");
    }
    
    /**
     *  Constructor por Parámetros Piloto
     */
    public Piloto(String nombre, String aniosVolando)
    {
        this.nombre = nombre;
        this.aniosVolando =  aniosVolando;
    }
        
    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @return the aniosVolando
     */
    public String getAniosVolando()
    {
        return aniosVolando;
    }

    /**
     * @param aniosVolando the aniosVolando to set
     */
    public void setAniosVolando(String aniosVolando) 
    {
        this.aniosVolando = aniosVolando;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    // ------------------------------------
    // MÉTODOS DE LA INTERFAZ IMPLEMENTADOS
    // ------------------------------------
    @Override
    public void encenderObjeto() {
        System.out.println("Soy el Piloto " + this.nombre + ", encendí mi nave.");
    }

    @Override
    public void maniobrarObjeto() {
        System.out.println("Soy el Piloto " + this.nombre + ", y llevo más de " + this.aniosVolando + " años volando.");
    }

    @Override
    public void apagarObjeto() {
        System.out.println("Soy el Piloto" + this.nombre + ", apagué mi nave.");
    }
}
