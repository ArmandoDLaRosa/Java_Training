package archivosajcr;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Armando De La Rosa
 */
public class ArchivosAJCR 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // ----------------        
        // Variables
        // ----------------
        String nombre, holder, iniciales = "", fileName;
        Scanner scanner = new Scanner(System.in); 
        
        System.out.println("\n\nCreemos un archivo con tus iniciales");
        System.out.println("¿Cuál es tu nombre completo?"); // Ejemplo: "Armando Jesus Cerda Rosa"  
        nombre = scanner.nextLine();
        
        // Obtener las iniciales del nombre del usuario
        String[] nombreArray = nombre.split(" ");          // Ejemplo pasa a ser "AJCR "
        for (int i = 0; i < nombreArray.length; i++) 
        {
            holder = nombreArray[i];
            iniciales += holder.charAt(0);
        }   
        
        // ----------------
        // FILE LOCATION
        // ----------------
        // En mi laptop, no me deja escribir en "C:\\" (Acess Denied)
        // Línea debería ser → fileName = "C:\\" + iniciales.toUpperCase() + ".txt";        
        fileName = "C:\\Users\\Armando\\Desktop\\" + iniciales.toUpperCase() + ".txt";        
            
        // ----------------
        // Crear el archivo
        // ----------------
        FileWriter nuevoArchivo = null; 
        PrintWriter archivoEscritor = null;
        try
        {
            nuevoArchivo = new FileWriter(fileName);
            archivoEscritor = new PrintWriter(nuevoArchivo);
            String linea = nombre; 
            archivoEscritor.println(linea);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());            
        } finally {
            archivoEscritor.close();
        }
        
        // ----------------
        // Leer el Archivo
        // ----------------
        File archivo = null;                  // Localizar archivo en la PC
        FileReader archivoOpener = null;      // Abre el archivo
        BufferedReader lector = null;         // Lee el archivo
        try
        {         
            archivo = new File(fileName);
            archivoOpener = new FileReader(archivo);
            lector = new BufferedReader(archivoOpener);

            String linea = "";

            System.out.println("\n\nMostrando contenido del archivo");
            while((linea = lector.readLine())!= null)
            {
              System.out.println(linea);
            }
        } 
        catch (Exception e)
        {
            System.out.println(e.toString());
        } finally
        { 
            try
            {
                archivoOpener.close();
            } 
            catch (IOException ex) 
            {
               // OTRA OPCIÓN → Logger.getLogger(ArchivosAJCR.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println(ex.toString());
            }
        }   
    }
}
