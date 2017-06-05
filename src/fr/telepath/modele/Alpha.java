package fr.telepath.modele;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//Classe implémentant le chiffrement Alpha que j'ai créé
//Alpha est un algorithme de chiffrement symétrique par bloc
public class Alpha {
	private static final int SALT_XOR = 13 + 14 + 17;

	//XOR entre deux bytes
	private static byte XOR_Byte_Byte(byte A, byte B) {
		return (byte)(A ^ B);
	}

	//Chiffrement d'un byte par une clé byte[]
	private static byte XOR_Byte(byte caractere, byte[] cle, int saltNumCaracBloc) {
		byte charChiff = caractere;
		int taille = cle.length;
		for(int i = 0 ; i < taille ; i++) {
			charChiff = XOR_Byte_Byte(charChiff, cle[i]);
			charChiff = XOR_Byte_Byte(charChiff, (byte)((i * i) + SALT_XOR)); 
			//Salage avec i et SALT_XOR pour éviter que la répétition d'un caractère 
			//de la clé annule le XOR avec ce caractère identique de la clé utilisé précédemment pour le XOR
		}
		return XOR_Byte_Byte(charChiff, (byte)saltNumCaracBloc); 
		//Salage avec la position du caractère à chiffrer dans le bloc à chiffrer, pour éviter que 
		//la répétition d'un caractère de la chaine à chiffrer donne le même résultat
	}

	//Retourne le SHA256 de chaine
	public static String hasher_SHA_256(String chaine) {
		String hash = "";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(chaine.getBytes());
		byte[] digest = messageDigest.digest();
		hash = new String(digest);
		return hash;
	}

	//Retourne le SHA1 de chaine
	public static String hasher_SHA_1(String chaine) {
		String hash = "";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(chaine.getBytes());
		byte[] digest = messageDigest.digest();
		hash = new String(digest);
		return hash;
	}

	//Chiffrement d'un byte[] par une clé byte[] : Publique (SHA256)
	//Le chiffrement via cette méthode et celui pour les fichiers n'est pas le même
	//Le chiffrement pour le fichier est différent étant donné le chiffrement de grands volumes
	//de données
	public static byte[] chiffrer(byte[] chaine, byte[] cle) { 
		byte[] chiffre = new byte[chaine.length];
		int taille = chaine.length;
		//Chiffrement par la clé
		for(int i = 0 ; i < taille ; i++)
			chiffre[i] = XOR_Byte(chaine[i], cle, i);
		//Chiffrement par un SHA256 de la clé pour être sûr d'éviter une collision
		cle = hasher_SHA_256(new String(cle)).getBytes();
		for(int i = 0 ; i < taille ; i++)
			chiffre[i] = XOR_Byte(chiffre[i], cle, i);
		return chiffre;
	}
}
















