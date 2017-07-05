package fr.telepath.modele;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//Classe utilisée pour la gestion des conversations
public class Conversation {
	private static String identifiantUtilisateur; //Identifiant de l'utilisateur une fois connecté
	private static String identiteUtilisateur; //Identite de l'utilisateur

	public static String getIdentifiantUtilisateur() {
		return identifiantUtilisateur;
	}

	public static void setIdentifiantUtilisateur(String identifiantUtilisateur) {
		Conversation.identifiantUtilisateur = identifiantUtilisateur;
	}

	public static String getIdentiteUtilisateur() {
		return identiteUtilisateur;
	}

	public static void setIdentiteUtilisateur(String identiteUtilisateur) {
		Conversation.identiteUtilisateur = identiteUtilisateur;
	}
}








