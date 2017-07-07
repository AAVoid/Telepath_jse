package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.telepath.modele.GestionDiscussion;
import fr.telepath.vue.DialogAjoutAmi;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurListeAmis implements ActionListener {
	private static final String NOM_DIALOG_AJOUT_AMI = "Ajout d'un ami";
	private static final boolean DIALOG_MODAL = true;
	
	private int id;
	private Fenetre fenetre;
	
	public EcouteurListeAmis(Fenetre fen, int id) {
		super();
		this.fenetre = fen;
		this.id = id;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.id){
		case 1: //BOUTON SE DECONNECTER
			GestionDiscussion.setIdentifiantUtilisateur("");
			GestionDiscussion.setIdentiteUtilisateur("");
			this.fenetre.afficherPageAccueil();
			break;
		case 2: //BOUTON AJOUTER UN AMI
			DialogAjoutAmi dialogAjoutAmi = new DialogAjoutAmi(fenetre, NOM_DIALOG_AJOUT_AMI, DIALOG_MODAL);
			dialogAjoutAmi.afficher();
			break;
		}
	}
}









