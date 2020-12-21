package interfacesajcr;

/**
 * @author Armando De La Rosa
 */
public class Desarrollador implements Interfaz
{
    
    private String nombre;
    private String lineasDeCodigo;

    /**
     *  Constructor por default Desarrollador
     */
    public Desarrollador()
    {
        this("", "");
    }
    
    /**
     *  Constructor por Parámetros Desarrollador
     */
    public Desarrollador(String nombre, String lineasDeCodigo)
    {
        this.nombre = nombre;
        this.lineasDeCodigo =  lineasDeCodigo;
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
     * @return the lineasDeCodigo
     */
    public String getLineasDeCodigo()
    {
        return lineasDeCodigo;
    }

    /**
     * @param lineasDeCodigo the lineasDeCodigo to set
     */
    public void setLineasDeCodigo(String lineasDeCodigo) 
    {
        this.lineasDeCodigo = lineasDeCodigo;
    }

    // ------------------------------------
    // MÉTODOS DE LA INTERFAZ IMPLEMENTADOS
    // ------------------------------------
    @Override
    public void encenderObjeto() {
        System.out.println("Soy el Desarrollador " + this.nombre + ", encendí mi computadora.");
    }

    @Override
    public void maniobrarObjeto() {
        System.out.println("Soy el Desarrollador " + this.nombre + ", y llevo más de " + this.lineasDeCodigo + " líneas codificadas.");
    }

    @Override
    public void apagarObjeto() {
        System.out.println("Soy el Desarrollador " + this.nombre + ", apagué mi computadora.");
    }
}
