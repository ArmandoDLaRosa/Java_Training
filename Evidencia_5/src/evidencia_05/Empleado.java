package evidencia_05;
/**
 * @author Armando De La Rosa
 */
public class Empleado 
{
    protected String nombre;
    protected String cedula;
    protected int edad;
    protected String estadoCivil;
    protected double salario;
    
    // Constructor por Default
    Empleado()
    {
        this("", "", 0, "", 0);
    }
    
    // Constructor por Parámetros
    Empleado(String nombre, String cedula, int edad, String estadoCivil, double salario)
    {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.salario = salario;
    }

    /**
     * @return la clasificación
     */
    protected String asignarClasificacion()
    {
        if (edad <= 21) 
        {
            return "Principiante";
        }
        if(edad >= 22 && edad <= 35)
        {
            return "Intermedio";
        }
        else 
            return "Senior";
    }
    
    /**
     *  Imprimir los datos del empleado
     */
    public void Imprimir()
    {
        System.out.println("Nombre:        " + nombre + 
                           "\nCédula:        " + cedula + 
                           "\nEdad:          " + edad + 
                           "\nEstado Civil:  " + estadoCivil +
                           "\nSalario:       " + salario +
                           "\nClasificación: " + asignarClasificacion());
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
     * @return the cedula
     */
    public String getCedula() 
    {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula)
    {
        this.cedula = cedula;
    }

    /**
     * @return the edad
     */
    public int getEdad() 
    {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) 
    {
        this.edad = edad;
    }

    /**
     * @return the estadoCivil
     */
    public String getEstadoCivil() 
    {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(String estadoCivil) 
    {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return the salario
     */
    public double getSalario() 
    {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) 
    {
        this.salario = salario;
    }
}
