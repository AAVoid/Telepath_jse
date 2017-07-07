package fr.telepath.modele;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//Information obtenu via le listing des relation de l'utilisateur connecté
public class Ami {
	private String identite;
	private int identifiantRelation;
	private int identifiant;
	
	public Ami(String identite, int identifiantRelation, int identifiant) {
		super();
		this.identite = identite;
		this.identifiantRelation = identifiantRelation;
		this.identifiant = identifiant;
	}

	public String getIdentite() {
		return identite;
	}

	@SuppressWarnings("unused")
	private void setIdentite(String identite) {
		this.identite = identite;
	}

	public int getIdentifiantRelation() {
		return identifiantRelation;
	}

	@SuppressWarnings("unused")
	private void setIdentifiantRelation(int identifiantRelation) {
		this.identifiantRelation = identifiantRelation;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	@SuppressWarnings("unused")
	private void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
}










