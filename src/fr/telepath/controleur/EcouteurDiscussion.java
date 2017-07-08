package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import fr.telepath.modele.Ami;
import fr.telepath.modele.ThreadDiscussion;
import fr.telepath.modele.service.UtiliserWS;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurDiscussion implements ActionListener {
	private int id;
	private Fenetre fenetre; //Pour retourner à l'écran précédent
	private JTextArea zoneDeTexte;
	private ThreadDiscussion threadDiscu; //Pour pouvoir arrêter la récupération de messages
	private Ami ami; //Ami à qui on parle
	
	public EcouteurDiscussion(int id, Fenetre fenetre, JTextArea zoneDeTexte, ThreadDiscussion threadDiscu, Ami ami) {
		super();
		this.id = id;
		this.fenetre = fenetre;
		this.zoneDeTexte = zoneDeTexte;
		this.threadDiscu = threadDiscu;
		this.ami = ami;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(this.id){
		case 1: //BOUTON ENVOYER
			//Vérification des champs
			if(!this.zoneDeTexte.getText().isEmpty()) {
				String reponse = "";
				try {
					reponse = UtiliserWS.serviceActivation(champCode.getText().replaceAll(" ", ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
				//Parsage JSON
				int rep = UtiliserWS.getReponse(reponse);
				if(rep == 1) {
				}
				else if(rep == 0)
					JOptionPane.showMessageDialog(this.dialog, MESSAGE_ACTIV_ECHEC, NOM_POPUP_ACTIV_ECHEC, JOptionPane.ERROR_MESSAGE);
				break;
			}
		case 2: //BOUTON QUITTER DISCUSSION
			this.threadDiscu.arreter();
			this.threadDiscu.join();
			//Retour à la liste des amis
			fenetre.afficherListeAmis();
			break;
		}
	}
}







