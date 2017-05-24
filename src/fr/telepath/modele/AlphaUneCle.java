package fr.telepath.modele;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//POUR POUVOIR CHIFFRER LES MESSAGES RECU ET ENVOYES
public class AlphaUneCle {
	//private static final int MODULO_XOR_INT = 65_536;
	private static final int MODULO_XOR_BYTE = 128;
	private static final int SALT_XOR = 1;

	//XOR ENTRE DEUX BYTE
	private static byte XOR_Byte_Byte(byte A, byte B) {
		return (byte)(A ^ B);
	}

	//CHIFFREMENT D'UN BYTE PAR UNE CLE BYTE[] AVEC UN SALAGE
	private static byte XOR_Byte(byte caractere, byte[] cle, int saltNumCaracBloc) {
		byte charChiff = caractere;
		int taille = cle.length; //TAILLE < 46 339 POUR EVITER DEBORDEMENT INT AVEC i * i
		for(int i = 0 ; i < taille ; i++) {
			charChiff = XOR_Byte_Byte(charChiff, cle[i]);
			charChiff = XOR_Byte_Byte(charChiff, (byte)(((i * i ) + SALT_XOR) % MODULO_XOR_BYTE)); //Salage
		}
		return XOR_Byte_Byte(charChiff, (byte)saltNumCaracBloc); //Salage
	}

	//CHIFFREMENT D'UNE CHAINE BYTE[] PAR UNE CLE BYTE[]
	public static byte[] XOR_Chiff_ByteS(byte[] chaine, byte[] cle) { 
		byte[] chiffre = new byte[chaine.length]; //TAILLE < 46 339 POUR EVITER DEBORDEMENT INT AVEC i * i
		int taille = chaine.length;
		for(int i = 0 ; i < taille ; i++)
			chiffre[i] += XOR_Byte(chaine[i], cle, i);
		return chiffre;
	}
}











