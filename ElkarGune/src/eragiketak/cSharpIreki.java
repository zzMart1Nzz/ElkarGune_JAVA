package eragiketak;
import java.io.IOException;

public class cSharpIreki {
	 public static void ejekutatu() {
	        try {
	            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\anere\\OneDrive - Goierri Eskola\\Escritorio\\EJECUTABLES\\ElkarGuneC.exe");
	            processBuilder.start();
	            System.exit(0);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
