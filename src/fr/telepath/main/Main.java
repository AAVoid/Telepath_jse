package fr.telepath.main;

import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class Main {
	public static void main(String[] args) {
		Fenetre fenetre = new Fenetre();
		fenetre.afficher();
	}
}


//String login;
//String mdp;
//String requete;
//boolean recupInfo = true;
////CONNEXION A LA BASE
//Class.forName("org.gjt.mm.mysql.Driver");
//Connection con = null;
//con = DriverManager.getConnection(
//		"jdbc:mysql://mysql-miage-antilles.alwaysdata.net/miage-antilles_test", 
//		"120432_admin", 
//		"jesuisunmotdepasse");
//Statement state = null;
//state = con.createStatement();
//Statement state2 = null;
//state2 = con.createStatement();
//ResultSet r = null;
////RECUPERATION INFORMATIONS DE CONNEXION
//@SuppressWarnings("resource")
//Scanner sc = new Scanner(System.in);
//while(true) {
//	do {
//		System.out.print("Login(adresse mail) : ");
//		login = sc.nextLine();
//		System.out.print("Mot de passe : ");
//		mdp = sc.nextLine();
//		//On vérifie si l'utilisateur existe dans la base
//		requete = "SELECT * "
//				+ "FROM user "
//				+ "WHERE mail = '" + login + "' AND mdp = '" + mdp + "'";
//		r = state.executeQuery(requete);
//		if(!r.next()) //Si l'utilisateur n'existe pas
//			System.out.println("Erreur de login ou de mot de passe !\n\n");
//		else
//			recupInfo = false;
//	} while(recupInfo);
//	//System.out.println("*** TEST 1 ***");
//	//AFFICHAGE DES CONTACTS
//	//On récupère l'ID de l'utilisateur
//	int idUser = r.getInt("id");
//	//ON RECUPERE LES CATEGORIES
//	requete = "SELECT * "
//			+ "FROM categorie";
//	r = state.executeQuery(requete);
//	ResultSet r2 = null;
//	while(r!= null && r.next()) {
//		//POUR CHAQUE CATEGORIE ON AFFICHE LES CONTACTS QUI S'Y TROUVENT
//		System.out.println("\n********* " + r.getString("libelle").toUpperCase() + " *********");
//		requete = "SELECT nom, prenom, portable, bureau, mail "
//				+ "FROM contact JOIN categorie ON contact.idCategorie = categorie.id "
//				+ "WHERE idUser = " + idUser + " AND idCategorie = " + r.getInt("id")
//				+ " ORDER BY prenom, nom";
//		r2 = state2.executeQuery(requete);
//		while(r2!= null && r2.next()) {
//			System.out.println(r2.getString("prenom") + "\t" + r2.getString("nom") + "\t" + r2.getString("portable")
//					+ "\t" + r2.getString("mail") + "\t");
//		}
//	}
//	System.out.println("\n\n");
//	recupInfo = true;
//}






