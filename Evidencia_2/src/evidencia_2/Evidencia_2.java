package evidencia_2;

import java.util.Scanner;

/**
 * @author Armando De La Rosa
 */
public class Evidencia_2
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Variables declaradas → 1 entero
        int radio;
        Scanner scanner = new Scanner(System.in); //→ import java.util.Scanner;
        
        // MAIN
        System.out.println("Ingresa el valor del radio: ");
        radio = scanner.nextInt();
        
        System.out.printf("Circunferencia: %.2f%n", calcularCircunferencia(radio));
        System.out.printf("Area: %.2f%n", calcularArea(radio));
    }
    
    // MÉTODOS
    
    // Cálcula la circunferencia, regresa un DOUBLE por los decimales de PI
    static double calcularCircunferencia(int radio)
    {
        return (radio*2*Math.PI);       
    }

    // Cálcula el área, regresa un DOUBLE por los decimales de PI
    static double calcularArea(int radio)
    {
        return (radio*radio*Math.PI);       
    }
}

