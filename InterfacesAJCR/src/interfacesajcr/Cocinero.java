package interfacesajcr;

/**
 * @author Armando De La Rosa
 */
public class Cocinero implements Interfaz
{
    
    private String nombre;
    private String horasEnEstufa;

    /**
     *  Constructor por default Cocinero
     */
    public Cocinero()
    {
        this("", "");
    }
    
    /**
     *  Constructor por Parámetros Cocinero
     */
    public Cocinero(String nombre, String horasEnEstufa)
    {
        this.nombre = nombre;
        this.horasEnEstufa =  horasEnEstufa;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    /**
     * @return the horasEnEstufa
     */
    public String getHorasEnEstufa() 
    {
        return horasEnEstufa;
    }

    /**
     * @param horasEnEstufa the horasEnEstufa to set
     */
    public void setHorasEnEstufa(String horasEnEstufa) 
    {
        this.horasEnEstufa = horasEnEstufa;
    }
    
    // ------------------------------------
    // MÉTODOS DE LA INTERFAZ IMPLEMENTADOS
    // ------------------------------------
    @Override
    public void encenderObjeto() {
        System.out.println("Soy el Cocinero " + this.nombre + ", encendí mi estufa.");
    }

    @Override
    public void maniobrarObjeto() {
        System.out.println("Soy el Cocinero " + this.nombre + ", y llevo más de " + this.horasEnEstufa + " horas en la estufa.");
    }

    @Override
    public void apagarObjeto() {
        System.out.println("Soy el Cocinero " + this.nombre + ", apagué mi estufa.");
    }
}
