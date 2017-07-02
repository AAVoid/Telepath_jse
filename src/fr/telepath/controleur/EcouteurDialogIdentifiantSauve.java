package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import fr.telepath.modele.IdentifiantSauve;
import fr.telepath.vue.DialogIdentifiantsSauves;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurDialogIdentifiantSauve implements ActionListener {
	private static final String NOM_POPUP_ENREGISTREMENT_FAIT = "Enregistrement effectué";
	private static final String MESSAGE_POPUP_ENREGISTREMENT_FAIT = "Enregistrement des identifiants effectué !";
	
	private int idAction;
	private DialogIdentifiantsSauves dialogIdSav;
	private ArrayList<IdentifiantSauve> listeIdentifiants;

	public EcouteurDialogIdentifiantSauve(DialogIdentifiantsSauves dialogIdSav, int idAction, 
			ArrayList<IdentifiantSauve> listeIdentifiants) {
		super();
		this.dialogIdSav = dialogIdSav;
		this.idAction = idAction;
		this.listeIdentifiants = listeIdentifiants;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.idAction){
		case 1: //Ajout
			IdentifiantSauve nouveauIdentifiant = new IdentifiantSauve("", "");
			this.listeIdentifiants.add(nouveauIdentifiant);
			this.dialogIdSav.actualiserAffichage();
			break;
		case 2: //Enregistrement
			ArrayList<IdentifiantSauve> listeCree = this.dialogIdSav.creerListeIdFromContenu();
			Controleur.viderIdentifiantsSauve();
			Controleur.sauverIdentifiantsSauve(listeCree);
			this.dialogIdSav.setListeIdentifiants(listeCree);
			this.dialogIdSav.actualiserAffichage();
			JOptionPane.showMessageDialog(this.dialogIdSav, MESSAGE_POPUP_ENREGISTREMENT_FAIT, 
					NOM_POPUP_ENREGISTREMENT_FAIT, JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}










