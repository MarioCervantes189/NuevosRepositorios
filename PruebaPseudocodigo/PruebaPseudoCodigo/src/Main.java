import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Lee el archivo de prueba
            File file = new File("prueba2.txt");
            Scanner scanner = new Scanner(file);
            
            // Lee el contenido del archivo y concatena las líneas en una sola cadena
            StringBuilder programaMLPBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                programaMLPBuilder.append(scanner.nextLine()).append("\n");
            }
            
            // Crea una instancia del MLPInterpreter y ejecuta el programa
            MLPInterpreter interpreter = new MLPInterpreter();
            interpreter.interpretProgram(programaMLPBuilder.toString());
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado");
        }
    }
}
