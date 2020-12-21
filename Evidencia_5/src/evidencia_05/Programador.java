package evidencia_05;



import evidencia_05.Empleado;

/**
 * @author Armando De La Rosa
 */
public class Programador extends Empleado
{
    private int lineasDeCodigoPorHora;
    private String lenguajeDominante;
    
    // Constructor por Default
    Programador()
    {
        this("", "", 0, "", 0, 0, "");
    }
    
    // Constructor por Parámetros
    Programador(String nombre, String cedula, int edad, String estadoCivil, double salario, int lineasDeCodigoPorHora, String lenguajeDominante)
    {
        super(nombre, cedula, edad, estadoCivil, salario);
        this.lineasDeCodigoPorHora = lineasDeCodigoPorHora;
        this.lenguajeDominante = lenguajeDominante;
    }
    
    /**
     *  Imprimir los datos del programador
     *  Sobrecarga imprimir() de la clase Empleado.
     */
    @Override
    public void Imprimir()
    {
        System.out.println("Nombre:        " + this.nombre + 
                           "\nCédula:        " + this.cedula + 
                           "\nEdad:          " + this.edad + 
                           "\nEstado Civil:  " + this.estadoCivil +
                           "\nSalario:       " + this.salario +
                           "\nClasificación: " + asignarClasificacion()+
                           "\nLineas/Hora:   " + lineasDeCodigoPorHora +
                           "\nLeng. Domin:   " + lenguajeDominante);
    }
    
    /**
     * @return the lineasDeCodigoPorHora
     */
    public int getLineasDeCodigoPorHora()
    {
        return lineasDeCodigoPorHora;
    }

    /**
     * @param lineasDeCodigoPorHora the lineasDeCodigoPorHora to set
     */
    public void setLineasDeCodigoPorHora(int lineasDeCodigoPorHora)
    {
        this.lineasDeCodigoPorHora = lineasDeCodigoPorHora;
    }

    /**
     * @return the lenguajeDominante
     */
    public String getLenguajeDominante() 
    {
        return lenguajeDominante;
    }

    /**
     * @param lenguajeDominante the lenguajeDominante to set
     */
    public void setLenguajeDominante(String lenguajeDominante)
    {
        this.lenguajeDominante = lenguajeDominante;
    }
}
