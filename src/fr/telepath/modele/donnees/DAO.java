package fr.telepath.modele.donnees;

import java.sql.Connection;
import java.util.ArrayList;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public abstract class DAO<T>{
	private Connection connection;

	public DAO(Connection c){
		this.connection = c;
	}

	//Lire un objet en base
	public abstract T lire(int id);

	//Creer un objet en base
	public abstract boolean creer(T Obj);

	//Mettre à jour un objet en base
	public abstract boolean mettreAJour(T Obj);

	//Supprimer un objet en base
	public abstract boolean supprimer(T Obj);
	
	//Supprimer la table
	public abstract boolean truncateTable();
	
	//Lisre toute la table
	public abstract ArrayList<T> lireTable();
	
	//Charger la table
	public abstract boolean chargerTable(ArrayList<T> listeIdentifiants);

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}







