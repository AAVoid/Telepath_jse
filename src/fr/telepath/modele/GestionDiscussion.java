package fr.telepath.modele;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//Classe utilisée pour la gestion des conversations
public class GestionDiscussion {
	private static final int FREQUENCE_RECUPERATION_MESSAGE_INT = 3; //Messages récupérés toutes les X secondes
	private static final long FREQUENCE_RECUPERATION_MESSAGE_LONG = FREQUENCE_RECUPERATION_MESSAGE_INT * 1000;
	private static String identifiantUtilisateur; //Identifiant de l'utilisateur une fois connecté
	private static String identiteUtilisateur; //Identite de l'utilisateur

	public static String getIdentifiantUtilisateur() {
		return identifiantUtilisateur;
	}

	public static void setIdentifiantUtilisateur(String identifiantUtilisateur) {
		GestionDiscussion.identifiantUtilisateur = identifiantUtilisateur;
	}

	public static String getIdentiteUtilisateur() {
		return identiteUtilisateur;
	}

	public static void setIdentiteUtilisateur(String identiteUtilisateur) {
		GestionDiscussion.identiteUtilisateur = identiteUtilisateur;
	}

	public static int getFrequenceRecuperationMessageInt() {
		return FREQUENCE_RECUPERATION_MESSAGE_INT;
	}

	public static long getFrequenceRecuperationMessageLong() {
		return FREQUENCE_RECUPERATION_MESSAGE_LONG;
	}
}








