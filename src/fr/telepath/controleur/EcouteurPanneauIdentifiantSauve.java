package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import fr.telepath.modele.IdentifiantSauve;
import fr.telepath.vue.DialogIdentifiantsSauves;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurPanneauIdentifiantSauve implements ActionListener {
	private int idAction;
	private DialogIdentifiantsSauves dialogIdSav; //Pour pouvoir lancer l'actualisation
	//de l'affichage dans la dialog parent
	private ArrayList<IdentifiantSauve> listeIdentifiants;
	private IdentifiantSauve identifiantAssocie;

	public EcouteurPanneauIdentifiantSauve(DialogIdentifiantsSauves dialogIdSav, int idAction, 
			ArrayList<IdentifiantSauve> listeIdentifiants, IdentifiantSauve idSauv) {
		super();
		this.dialogIdSav = dialogIdSav;
		this.idAction = idAction;
		this.listeIdentifiants = listeIdentifiants;
		this.identifiantAssocie = idSauv;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.idAction){
		case 1: //Connexion
			//On renseigne le champs
			this.dialogIdSav.getIdPasswordField().setText(this.identifiantAssocie.getId());
			//On ferme la fenêtre de dialog et on renseigne le champs
			this.dialogIdSav.setVisible(false);
			break;
		case 2: //Suppression
			this.listeIdentifiants.remove(this.identifiantAssocie);
			this.dialogIdSav.actualiserAffichage();
			break;
		}
	}
}










