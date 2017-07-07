package fr.telepath.controleur;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import fr.telepath.modele.Ami;
import fr.telepath.modele.IdentifiantSauve;
import fr.telepath.modele.donnees.DAO_IdentifiantSauve;
import fr.telepath.modele.donnees.SingletonConnectionSQLITE;
import fr.telepath.modele.service.UtiliserWS;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//CONTROLEUR AUTRE QUE LES ECOUTEURS
public class Controleur {
	public static ArrayList<IdentifiantSauve> chargerIdentifiantsSauve() {
		ArrayList<IdentifiantSauve> listeIdentifiants = new ArrayList<IdentifiantSauve>();
		DAO_IdentifiantSauve daoId = new DAO_IdentifiantSauve(SingletonConnectionSQLITE.getConnexion());
		listeIdentifiants = daoId.lireTable();
		return listeIdentifiants;
	}

	public static boolean viderIdentifiantsSauve() {
		DAO_IdentifiantSauve daoId = new DAO_IdentifiantSauve(SingletonConnectionSQLITE.getConnexion());
		return daoId.truncateTable();
	}

	public static boolean sauverIdentifiantsSauve(ArrayList<IdentifiantSauve> listeIdentifiants) {
		DAO_IdentifiantSauve daoId = new DAO_IdentifiantSauve(SingletonConnectionSQLITE.getConnexion());
		return daoId.chargerTable(listeIdentifiants);
	}

	public static ArrayList<Ami> obtenirListeAmis(Fenetre fenetre, String idUser) {
		final String MESSAGE_POPUP_LISTE_AMIS_NON_CHARGEE = "Erreur !\n"
				+ "Votre liste d'amis n'a pas pu être chargée !";
		final String NOM_POPUP_LISTE_AMIS_NON_CHARGEE = "Erreur liste d'amis non chargée";
		
		ArrayList<Ami> listeAmis = new ArrayList<Ami>();
		String reponse = "";
		try {
			reponse = UtiliserWS.serviceListerRelation(idUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Parsage JSON
		int rep = UtiliserWS.getReponse(reponse);
		if(rep == 1) { //Les amis ont pu être récupérés
			UtiliserWS.lireListeAmisApresListage(listeAmis, reponse);
		}
		else { //Les amis n'ont pas pu être récupérés
			JOptionPane.showMessageDialog(fenetre, MESSAGE_POPUP_LISTE_AMIS_NON_CHARGEE, 
					NOM_POPUP_LISTE_AMIS_NON_CHARGEE, JOptionPane.INFORMATION_MESSAGE);
		}
		return listeAmis;
	}
}








