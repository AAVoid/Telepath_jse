package fr.telepath.modele;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class Fichier {
	public static void EcrireFichierBinaire(FileOutputStream fos, byte[] data) {
		try {
			fos.write(data);
		}
		catch (Exception e) {
		}
	}

	public static String LireFichierBinaireEntier(String filename) {
		File file = new File(filename);
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} 
		catch (FileNotFoundException e1) {
		}
		String str = "";
		int i = 0;
		try {
			while((i = fr.read()) != -1)
				str += (char)i;
		} 
		catch (IOException e1) {
		}
		try {
			fr.close();
		} 
		catch (IOException e) {
		}
		return str;
	}
}















