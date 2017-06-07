package fr.telepath.controleur;

import java.util.ArrayList;

import fr.telepath.modele.IdentifiantSauve;
import fr.telepath.modele.donnees.DAO_IdentifiantSauve;
import fr.telepath.modele.donnees.SingletonConnectionSQLITE;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//CONTROLEUR AUTRE QUE LES ECOUTEURS
public class Controleur {
	public static ArrayList<IdentifiantSauve> chargerIdentifiantsSauve() {
		ArrayList<IdentifiantSauve> listeIdentifiants = new ArrayList<IdentifiantSauve>();
		DAO_IdentifiantSauve daoId = new DAO_IdentifiantSauve(SingletonConnectionSQLITE.getCon());
		listeIdentifiants = daoId.lireTable();
		return listeIdentifiants;
	}
}








