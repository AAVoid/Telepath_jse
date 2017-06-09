package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;

import fr.telepath.vue.DialogActivation;
import fr.telepath.vue.DialogIdentifiantsSauves;
import fr.telepath.vue.DialogInscription;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurAccueil implements ActionListener {
	private static final String NOM_DIALOG_INSCRIPTION = "Inscription";
	private static final String NOM_DIALOG_ACTIVATION = "Activation de compte";
	private static final String NOM_DIALOG_IDENTIFIANTS_SAUVES = "Identifiants sauvegardés";
	private static final boolean DIALOG_MODAL = true;
	
	private int id;
	private Fenetre fenetre;
	private JPasswordField passwordField;
	
	public EcouteurAccueil(Fenetre fen, int id, JPasswordField passwordField) {
		super();
		this.fenetre = fen;
		this.id = id;
		this.passwordField = passwordField;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.id){
		case 1: //Bouton connexion
			String identifiant = new String(passwordField.getPassword());
			System.out.println("CONNEXION ID = " + identifiant);
			break;
		case 2: //Bouton inscription
			DialogInscription dialogInscription = new DialogInscription(fenetre, NOM_DIALOG_INSCRIPTION, DIALOG_MODAL);
			dialogInscription.afficher();
			break;
		case 3: //Bouton activer compte
			DialogActivation dialogActivation = new DialogActivation(fenetre, NOM_DIALOG_ACTIVATION, DIALOG_MODAL);
			dialogActivation.afficher();
			break;
		case 4: //Bouton identifiants sauvés
			DialogIdentifiantsSauves dialogIdentifiantsSauves = new DialogIdentifiantsSauves(fenetre, 
					NOM_DIALOG_IDENTIFIANTS_SAUVES, DIALOG_MODAL, passwordField);
			dialogIdentifiantsSauves.afficher();
			break;
		}
	}
}






