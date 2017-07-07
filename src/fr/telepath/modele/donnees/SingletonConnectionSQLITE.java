package fr.telepath.modele.donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class SingletonConnectionSQLITE {
	private static final String NOM_FICHIER_SQLITE = "idSav.tel";
	private Connection connection;

	private SingletonConnectionSQLITE() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:sqlite:" + NOM_FICHIER_SQLITE + "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static final SingletonConnectionSQLITE Singleton = new SingletonConnectionSQLITE();

	private Connection getConnection() {
		return connection;
	}

	public static Connection getConnexion() {
		return SingletonConnectionSQLITE.getSingleton().getConnection();
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public static SingletonConnectionSQLITE getSingleton() {
		return Singleton;
	}
}











