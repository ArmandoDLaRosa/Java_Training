package evidencia_1;

import java.util.Scanner;

/**
 * @author Armando De La Rosa
 */
public class Evidencia_1 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //Variables declaradas en orden → 1 string, 1 entero y 2 double. 
        String stringName;   
        int numInt;
        double numDouble, sum;
        Scanner scanner = new Scanner(System.in); //→ import java.util.Scanner;

        // Main 
        System.out.println("Ingresa tu nombre:");
        stringName = scanner.nextLine();
        System.out.println("Ingresa un numero entero:");
        numInt = scanner.nextInt();
        System.out.println("Ingresa un numero decimal:");
        numDouble = scanner.nextFloat();
        sum = numDouble + numInt;
        System.out.printf("Hola %s, el resultado de la suma entre %d y %.2f es %.2f.%n", stringName, numInt, numDouble, sum);
    }
}
