package evidencia_3;

import java.util.Scanner;

/**
 * @author Armando De La Rosa
 */
public class Evidencia_3
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // NOTA: ----------------------------------
        // Se asume que no se puede usar ArrayLists.
        // Por lo tanto al inicio se le pide al usuario
        // la cantidad inicial de personas. 
        
        Scanner scanner = new Scanner(System.in); //→ import java.util.Scanner;
        // Variables declaradas → 8 entero.
        int respuesta, cantidad, contador, edadPromedio = 0;
        int hombresCant = 0, mujeresCant = 0, solterosCant = 0, casadosCant = 0;
            
        // MAIN
        System.out.println("\n\nSi no sabes la cantidad de personas a capturar,");
        System.out.println("ingresa un número grande. Si sí sabes, el exacto.");
        System.out.println("Cantidad posible de personas a agregar: ");
        cantidad = scanner.nextInt();
        String[][] registros = new String[4][cantidad];
        
        // -----------------
        // Capturar Personas
        // -----------------
        contador = 0;
        respuesta = 1;
        while (contador != cantidad && respuesta != 0)
        {
            System.out.println("\n\nIngresa información de la persona #" + (contador+1));
            System.out.println("Respeta la mayúscula de las opciones en Estado Civil y Sexo");
            scanner.nextLine();
            System.out.println("Nombre: ");
            registros[0][contador] = scanner.nextLine();
            System.out.println("Edad: ");
            registros[1][contador] = scanner.next();
            //Respetar la mayúscula de Solter@ o Casad@
            System.out.println("Estado Civil (Casado/Soltero o Casada/Soltera): ");     
            registros[2][contador] = scanner.next();
            //Respetar la mayúscula de "Hombre" y "Mujer".
            System.out.println("Sexo (Hombre/Mujer): ");
            registros[3][contador] = scanner.next();
            
            contador++;
            
            System.out.println("\n\n¿Deseas agregar otra persona? ");
            System.out.println("\tSÍ, escribe 1");
            System.out.println("\tNO, escribe 0");
            respuesta = scanner.nextInt();
            
            if (contador == cantidad && respuesta == 1)
            {
                System.out.println("\n\n------------------------------------------------");
                System.out.println("Ya ingresaste la cantidad máxima de personas: " + cantidad);
                System.out.println("Ingresa más personas posibles al inicio del programa");
                System.out.println("----------------------------------------------------");
            }
        }
      
        // ------------
        // Análisis
        // ------------
        for ( int i = 0; i < contador; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if (j == 1)
                   edadPromedio += Integer.parseInt(registros[1][i]);
                
                if (j == 2)
                {
                    if(registros[2][i].equals("Soltero") || registros[2][i].equals("Soltera") )
                       solterosCant++;
                    if(registros[2][i].equals("Casado") || registros[2][i].equals("Casada"))
                       casadosCant++;
                }
               
                if (j == 3)
                {
                    if (registros[3][i].equals("Hombre"))
                         hombresCant++;
                    if (registros[3][i].equals("Mujer"))
                         mujeresCant++;
                }
                // To print the data
                // System.out.println(registros[j][i]); 
            }
        }
        
        // ------------------
        // Mostrar Resultados
        // -----------------
        System.out.println("\n\n\tTotal de personas capturadas: " + contador);
        System.out.println("\tPromedio de Edad de las personas capturadas: " + ((double)edadPromedio/contador));
        System.out.println("\tPorcentaje de Hombres                      : " + (((double)hombresCant/contador)*100) + "\t%");
        System.out.println("\tPorcentaje de Mujeres                      : " + (((double)mujeresCant/contador)*100) + "\t%");
        System.out.println("\tPorcentaje de Hombres y Mujeres solteros   : " + (((double)solterosCant/contador)*100) + "\t%");
        System.out.println("\tPorcentaje de Hombres y Mujeres casados    : " + (((double)casadosCant/contador)*100) + "\t%");
    }
}

