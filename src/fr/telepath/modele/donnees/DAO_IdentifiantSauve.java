package fr.telepath.modele.donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.telepath.modele.IdentifiantSauve;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//CLASSE UTILISEE AVEC LA CONNECTION SQLITE
///NOM DE LA TABLE : ID_CONN avec 2 COLONNES : ID et LABEL QUI SONT DES 
//CHAINES DE CARACTERES
public class DAO_IdentifiantSauve extends DAO<IdentifiantSauve> {
	private static final String REQUETE_TRUNCATE_TABLE = "DELETE FROM ID_CONN";
	private static final String REQUETE_LIRE_TABLE = "SELECT id, label FROM ID_CONN ORDER BY label ASC";
			
	public DAO_IdentifiantSauve(Connection c) {
		super(c);
	}

	@Override
	public IdentifiantSauve lire(int id) {
		System.out.println("DAO Identifiant Sauve, lire non utilise");
		return null; //NON UTILISE
	}

	@Override
	public boolean creer(IdentifiantSauve Obj) {
		System.out.println("DAO Identifiant Sauve, creer non utilise");
		return false; //NON UTILISE
	}

	@Override
	public boolean mettreAJour(IdentifiantSauve Obj) {
		System.out.println("DAO Identifiant Sauve, mettre a jour non utilise");
		return false; //NON UTILISE
	}

	@Override
	public boolean supprimer(IdentifiantSauve Obj) {
		System.out.println("DAO Identifiant Sauve, supprimer non utilise");
		return false; //NON UTILISE
	}
	
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	
	@Override
	public boolean truncateTable() {
		String requete = REQUETE_TRUNCATE_TABLE;
		try {
			Statement state = this.getConnection().createStatement();
			int nbLigneEff = state.executeUpdate(requete);
			if(nbLigneEff == 0) //Nombre de lignes affectées par la requête
				return false;  
			return true; //la suppression s'est bien passé
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<IdentifiantSauve> lireTable() {
		ArrayList<IdentifiantSauve> listeIdentifiant = new ArrayList<IdentifiantSauve>();
		String requete = REQUETE_LIRE_TABLE;
		try {
			Statement state = this.getConnection().createStatement();
			ResultSet r = state.executeQuery(requete);
			while(r!= null && r.next()) { //tant qu'il y a des lignes
				listeIdentifiant.add(new IdentifiantSauve(r.getString("id"), r.getString("label")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeIdentifiant;
	}

	@Override
	public boolean chargerTable(ArrayList<IdentifiantSauve> listeIdentifiants) {
		String requete = "";
		Statement state;
		try {
			for(IdentifiantSauve identifiant : listeIdentifiants) {
				requete = "INSERT INTO ID_CONN (id, label) VALUES ('"
						+ identifiant.getId() + "', '" + identifiant.getLabel() + "')";
				state = this.getConnection().createStatement();
				@SuppressWarnings("unused")
				int retour = state.executeUpdate(requete);
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}











