package fr.telepath.modele;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Universit� des Antilles - Guadeloupe
//2016 - 2017



//Classe utilis�e pour la gestion des conversations
public class GestionDiscussion {
	private static String identifiantUtilisateur; //Identifiant de l'utilisateur une fois connect�
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
}








