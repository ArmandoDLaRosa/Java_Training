package interfacesajcr;

import java.util.Scanner;

/**
 * @author Armando De La Rosa
 */
public class InterfacesAJCR 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        // Variables
        int respuesta; 
        
        // Objetos Predefinidos para test de Interfaz.java con las clases:
        //    → Piloto
        //    → Desarrollador
        //    → Cocinero
        Piloto  instanciaPiloto =  new Piloto("Armando De La Rosa", "23233");
        Desarrollador instanciaDesarrollador = new Desarrollador("Eduardo Cruz", "32322");
        Cocinero instanciaCocinero = new Cocinero("Felipe Fuentes", "23232");
        
        // -----------
        // TEST
        // -----------
        do
        {
            // -----------
            // MENU 
            // -----------
            System.out.println("\n\n PRUEBA LOS MÉTODOS        ");
            System.out.println("\t Cerrar ......  escribe 0.   ");
            System.out.println("\t Piloto,        escribe 1.   ");
            System.out.println("\t Desarrollador, escribe 2.   ");
            System.out.println("\t Cocinero,      escribe 3.   ");
            respuesta = sc.nextInt(); 
            
            // CASOS
            switch(respuesta)
            {
                case 1:
                    // PILOTO
                    System.out.println("\n\nPILOTO ----------------");
                    System.out.println("\nPiloto, encenderObjeto() ");
                    instanciaPiloto.encenderObjeto();
                    System.out.println("\nPiloto, maniobrarObjeto()");
                    instanciaPiloto.maniobrarObjeto();
                    System.out.println("\nPiloto, apagarObjeto()   ");
                    instanciaPiloto.apagarObjeto();
                    break;

                case 2:
                    // DESARROLLADOR
                    System.out.println("\n\nDESARROLLADOR ----------------");
                    System.out.println("\nDesarrollador, encenderObjeto() ");
                    instanciaDesarrollador.encenderObjeto();
                    System.out.println("\nDesarrollador, maniobrarObjeto()");
                    instanciaDesarrollador.maniobrarObjeto();
                    System.out.println("\nDesarrollador, apagarObjeto()   ");
                    instanciaDesarrollador.apagarObjeto();                    
                    break;

                case 3:
                    // COCINERO
                    System.out.println("\n\nCOCINERO ----------------");
                    System.out.println("\nCocinero, encenderObjeto() ");
                    instanciaCocinero.encenderObjeto();
                    System.out.println("\nCocinero, maniobrarObjeto()");
                    instanciaCocinero.maniobrarObjeto();
                    System.out.println("\nCocinero, apagarObjeto()   ");
                    instanciaCocinero.apagarObjeto();
                    break;
                    
            }    
        } while (respuesta != 0 );
    }
}
