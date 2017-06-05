package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.telepath.vue.DialogEspritTelepath;
import fr.telepath.vue.DialogOrigineTelepath;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurJMenu implements ActionListener {
	private static final String NOM_DIALOG_ORIGINE = "Origine de " + Fenetre.getNomApplication();
	private static final String NOM_DIALOG_ESPRIT = "Esprit de " + Fenetre.getNomApplication();
	private static final boolean DIALOG_MODAL = true;
	
	private int id;
	private Fenetre fenetre;
	
	public EcouteurJMenu(Fenetre fen, int id) {
		super();
		this.fenetre = fen;
		this.id = id;	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.id){
		case 1: //Item origine de Télépath
			DialogOrigineTelepath dialogOrigine = new DialogOrigineTelepath(fenetre, NOM_DIALOG_ORIGINE, DIALOG_MODAL);
			dialogOrigine.afficher();
			break;
		case 2: //Item esprit de Télépath
			DialogEspritTelepath dialogEsprit = new DialogEspritTelepath(fenetre, NOM_DIALOG_ESPRIT, DIALOG_MODAL);
			dialogEsprit.afficher();
			break;
		}
	}
}















