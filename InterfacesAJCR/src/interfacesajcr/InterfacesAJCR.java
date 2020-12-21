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
        Piloto  instanciaPiloto =  new Piloto("Armando De La Rosa", "13");
        Desarrollador instanciaDesarrollador = new Desarrollador("Eduardo Cruz", "32322");
        Cocinero instanciaCocinero = new Cocinero("Felipe Fuentes", "15000");
        
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
                    encenderI(instanciaPiloto);
                    System.out.println("\nPiloto, maniobrarObjeto()");
                    maniobrarI(instanciaPiloto);
                    System.out.println("\nPiloto, apagarObjeto()   ");
                    apagarI(instanciaPiloto);
                    break;

                case 2:
                    // DESARROLLADOR
                    System.out.println("\n\nDESARROLLADOR ----------------");
                    System.out.println("\nDesarrollador, encenderObjeto() ");
                    encenderI(instanciaDesarrollador);
                    System.out.println("\nDesarrollador, maniobrarObjeto()");
                    maniobrarI(instanciaDesarrollador);
                    System.out.println("\nDesarrollador, apagarObjeto()   ");
                    apagarI(instanciaDesarrollador);
                    break;

                case 3:
                    // COCINERO
                    System.out.println("\n\nCOCINERO ----------------");
                    System.out.println("\nCocinero, encenderObjeto() ");
                    encenderI(instanciaCocinero);
                    System.out.println("\nCocinero, maniobrarObjeto()");
                    maniobrarI(instanciaCocinero);
                    System.out.println("\nCocinero, apagarObjeto()   ");
                    apagarI(instanciaCocinero);
                    break;
                    
            }    
        } while (respuesta != 0 );
            
    }
    
    // -----------------------------
    // VALIDAR QUE INTERFAZ FUNCIONE
    // -----------------------------
    public static void encenderI(Interfaz instancia)
    {
        instancia.encenderObjeto();
    }
    
    public static void maniobrarI(Interfaz instancia)
    {
        instancia.maniobrarObjeto();
    }
    
    public static void apagarI(Interfaz instancia)
    {
        instancia.apagarObjeto();
    }   
}
