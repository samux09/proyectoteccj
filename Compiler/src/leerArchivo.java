import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class leerArchivo {
	public leerArchivo() {
		
	}
	  public String leerArchivo(String sNombreArchivo) {
	    	File file = new File(sNombreArchivo);
	    	FileReader fileR = null;
	    	BufferedReader file2 = null;
	    	String lines = "";
	    	String codigoFuente = "";
	    	try {
	    	    fileR = new FileReader(file);
	    	    file2 = new BufferedReader(fileR);


	    	} catch (FileNotFoundException e) {
	    	    System.out.println("No se encontro el archivo "+file.getName());
	    	}

	    	try {
	    	    while( ( lines = file2.readLine()) != null) {
	    	        codigoFuente += lines+"\n";
	    	    }
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
	    	return codigoFuente;
	    }
}
