package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LeerArchivo {

	static String   Ruta = "./Main/codigo.txt";

	public static ArrayList<String> leerTexto() {//Lista de funciones y sus datos
		ArrayList<String> informacion = new ArrayList<String>();
		try(BufferedReader bf = new BufferedReader(new FileReader(Ruta))){//Lee los datos
			String s;
			while((s = bf.readLine())!=null) {//Hasta que no haya linea para leer
				if(!s.replace(" ", "").equals(""))
				informacion.add(s);//Se anade la linea
				}
			}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		//Se regresa el ArrayList en Array
		return informacion;
		
	}
}



