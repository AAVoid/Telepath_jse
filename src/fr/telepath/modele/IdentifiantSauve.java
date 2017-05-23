package fr.telepath.modele;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Universit� des Antilles - Guadeloupe
//2016 - 2017



//Classe utilis�e pour le stockage des identifiants de connexion pour �viter d'avoir � les
//retaper
public class IdentifiantSauve {
	private String id;
	private String label;
	
	public IdentifiantSauve(String id, String label) {
		super();
		this.id = id;
		this.label = label;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}










