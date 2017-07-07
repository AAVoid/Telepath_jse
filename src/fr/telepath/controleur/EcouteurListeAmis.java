package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.telepath.modele.GestionDiscussion;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurListeAmis implements ActionListener {
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
			
			break;
		}
	}
}









