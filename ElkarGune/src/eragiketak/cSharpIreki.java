package eragiketak;
import java.io.IOException;
import java.nio.file.Paths;

public class cSharpIreki {
    public static void ejekutatu() {
        try {
            // Obtener la ruta del directorio actual
            String currentDir = Paths.get("").toAbsolutePath().toString();
            // Construir la ruta del ejecutable
            ProcessBuilder processBuilder = new ProcessBuilder(currentDir + "/ElkarGune.exe");
            // Iniciar el proceso
            processBuilder.start();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
