package evidencia_4;

import java.util.ArrayList; // Arrays sin límite de datos
import java.util.Scanner;  //  Lectura de la consola
/**
 * @author Armando De La Rosa
 */
public class Evidencia_4 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in); 
        // Variables Declaradas → 4 string, 4 enteros y 1 double.
        String nombre, cedula, estadoCivil, lenguaje;
        int respuesta, contador, edad, lineaPorHora;
        double salario;
        ArrayList<Empleado> empleados =  new ArrayList<>();
        
        // ------------------
        // Capturar Empleados
        // ------------------
        contador = 0;
        respuesta = 1;
        while (respuesta != 0)
        {
            System.out.println("\n\nIngresa información del Empleado #" + (contador+1));
            System.out.println("Respeta la mayúscula de las opciones en Estado Civil y Sexo");

            System.out.println("\n\nNombre: ");
            nombre = scanner.nextLine();
            System.out.println("Cedula: ");
            cedula = scanner.next();
            System.out.println("Edad (entre 18 y 45 años): ");   
            edad = scanner.nextInt();
            while (edad < 18 || edad > 45) // "rango" incluye 18 y 45. 
            {
                System.out.println("Edad (entre 18 y 45 años): ");   
                edad = scanner.nextInt();
            }
            System.out.println("Estado Civil: ");
            estadoCivil = scanner.next();
            System.out.println("Salario: ");
            salario = scanner.nextDouble();
                        
            System.out.println("\n\n¿Es un programador? ");
            System.out.println("\tSÍ, escribe 1");
            System.out.println("\tNO, escribe 0");
            respuesta = scanner.nextInt();       
            if (respuesta == 0)
            {
                // Crear empleados
                empleados.add(new Empleado(nombre, cedula, edad, estadoCivil, salario));
            } else                                                                                  
            {
                // Crear programadores
                System.out.println("\nLíneas Por Hora: ");   
                lineaPorHora = scanner.nextInt();
                
                System.out.println("Lenguaje Dominante: ");
                lenguaje = scanner.next();
                
                empleados.add(new Programador(nombre, cedula, edad, estadoCivil, salario, lineaPorHora, lenguaje));
            }
            contador++;
            
            System.out.println("\n\n¿Deseas agregar otra persona? ");
            System.out.println("\tSÍ, escribe 1");
            System.out.println("\tNO, escribe 0");
            respuesta = scanner.nextInt();            
            scanner.nextLine(); // Para evitar que se omita una línea en la consola.
        }

        // ------------------
        // Imprimir Empleados
        // ------------------
        empleados.forEach(item ->
        {
            // Obtener el tipo de empleado
            String tipoEmpleado = item.getClass().getName().substring(item.getClass().getName().lastIndexOf(".")+1);
            // Imprimir los datos del empleado
            System.out.println("\n\nDatos ----------------------------------------------- " + tipoEmpleado);
            item.Imprimir();
        });
    }
}
