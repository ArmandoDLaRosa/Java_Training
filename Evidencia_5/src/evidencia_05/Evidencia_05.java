package evidencia_05;

import java.util.ArrayList; // Arrays sin límite de datos
import java.util.Scanner;  //  Lectura de la consola

/**
 * @author Armando De La Rosa
 */
public class Evidencia_05 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in); 
        // Variables Declaradas → 4 string, 4 enteros, 1 bololean y 1 double.
        String nombre, cedula, estadoCivil, lenguaje;
        int respuesta, contador, edad, lineaPorHora;
        double salario;
        boolean error;
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
            
            // NOTA " VALIDACIÓN":------------------------------------
            // Las excepciones también se pueden trabajar en las clases
            // en este caso los constructores y set() de edad y salario.
            // se usa NumberFormatException para mostrar una subclase de
            // Exception ya incluida, es decir, no personalizada.
            error = true;
            edad = 0;
            do
            {
                try 
                {
                    edad = Integer.parseInt(scanner.next());
                    if (edad < 18 || edad > 45)
                    {
                        throw new FormatException(edad);   
                    }
                    error = false; 
                }
                // NO EN RANGO ó muy grande. 
                catch (FormatException notInRange) 
                {
                    System.out.println(notInRange);
                }
                // NO ES NÚMERO 
                catch (NumberFormatException notNumeric)
                {
                   System.out.println(notNumeric.toString().substring(notNumeric.toString().lastIndexOf(".")+1) + ", es decir, ingrese una edad númerica (18 a 45 años): "); 
                }
                // MÁS GRANDE QUE INTEGER
            } while (error);
            
            System.out.println("Estado Civil: ");
            estadoCivil = scanner.next();
            
            System.out.println("Salario: ");            
            error = true;
            salario = 0;
            do
            {
                try 
                {
                    salario = Integer.parseInt(scanner.next());
                    if (salario < 3800 || salario > 300000) // Se asume que $300 000 mensuales es un salario muy grande, se asume que $3800 es el salario mensual mínimo
                    {
                        throw new OverFlowException("El salario capturado no está en el rango de 3800 a 300000 pesos. Captura de nuevo el salario: ");   
                    }
                    error = false; 
                }
                // Muy GRANDE
                catch (OverFlowException tooBig) 
                {
                    System.out.println(tooBig.toString().substring(tooBig.toString().lastIndexOf("$")+1));
                }
                // NO ES NÚMERO
                catch (NumberFormatException notNumeric)
                {
                   System.out.println(notNumeric.toString().substring(notNumeric.toString().lastIndexOf(".")+1) + ", es decir, ingrese una esalariodad númerica (3800 a 300000 pesos): "); 
                }
            } while (error);
            
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

    // CLASES de EXCEPCIONES PERSONALIZDAS
    public static class FormatException extends Exception
    {
        private int wrongData;
        public FormatException(int wrongData)
        {
            this.wrongData = wrongData;
        }
        
        public String toString()
        {
          return "FormatException["+ wrongData + "]: La edad capturada no pertenece al rango permitido (18 a 45 años), favor de capturar nuevamente.";
        }
    }
    
    public static class OverFlowException extends Exception
    {
        public OverFlowException(String message)
        {
            super(message);
            // se pudo hacer override del toString();
            // se deja así para mostrar otra manera de hacer Exceptions personalizadas.
        }
    }
}
