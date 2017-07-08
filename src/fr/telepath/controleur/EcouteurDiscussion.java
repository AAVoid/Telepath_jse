package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import fr.telepath.modele.Ami;
import fr.telepath.modele.ThreadDiscussion;
import fr.telepath.modele.service.UtiliserWS;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurDiscussion implements ActionListener {
	private static final String NOM_POPUP_ENVOI_ECHEC = "Erreur d'envoi du message !";
	private static final String MESSAGE_POPUP_ENVOI_ECHEC = "Votre message n'a pas pu être envoyé !\n"
			+ "L'application rencontre peut-être des problèmes.";
	private static final String FORMAT_DATE_FICHIER_IDENTIFIANT = "EEE dd MMM yyyy - HH'h'mm'm'ss's'";

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
			String messageAEnvoyer = this.zoneDeTexte.getText();
			if(!messageAEnvoyer.isEmpty()) {
				String reponse = "";
				try {
					//On rajoute la date du message au début
					SimpleDateFormat formatDate = new SimpleDateFormat(FORMAT_DATE_FICHIER_IDENTIFIANT);
					messageAEnvoyer = formatDate.format(new Date()) + " : \n" + messageAEnvoyer;
					reponse = UtiliserWS.serviceEnvoyerMessage(ami.getIdentifiant(), messageAEnvoyer.replaceAll("\\n", "<br>"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//Parsage JSON
				int rep = UtiliserWS.getReponse(reponse);
				if(rep == 1) {
					this.zoneDeTexte.setText("");
				}
				else if(rep == 0) {
					JOptionPane.showMessageDialog(fenetre, MESSAGE_POPUP_ENVOI_ECHEC, NOM_POPUP_ENVOI_ECHEC, 
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		case 2: //BOUTON QUITTER DISCUSSION
			this.threadDiscu.arreter();
			try {
				this.threadDiscu.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			//Retour à la liste des amis
			fenetre.afficherListeAmis();
			break;
		}
	}
}







