package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.telepath.modele.UtiliserWS;
import fr.telepath.vue.DialogInscription;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurDialogInscription implements ActionListener {
	private static final String NOM_POPUP_INSC_REUSSI = "Inscription reussi !";
	private static final String MESSAGE_INSC_REUSSI = "Inscription reussi !\nUn mail vous a ete envoye !\n\n"
			+ "Vous pouvez activer votre compte via le lien dans le mail.\n"
			+ "\nVous pouvez aussi recuperer le code d'activation a la fin du lien\n"
			+ "(apres ''activation='') et le saisir dans l'application.";
	private static final String NOM_POPUP_INSC_ECHEC = "Echec de l'inscription !";
	private static final String MESSAGE_INSC_ECHEC = "Une erreur est survenue ! L'inscription a echoue !";
	private static final String NOM_POP_UP_CTRL_CHAMPS_WARNING_1 = "Attention !";
	private static final String MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_1 = "Veuillez renseigner tous les champs !";
	private static final String NOM_POP_UP_CTRL_CHAMPS_WARNING_2 = "Attention !";
	private static final String MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_2 = "Veuillez utiliser une adresse e-mail valide !";
	private static final String REGEX_EMAIL = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";
	
	private int id;
	private DialogInscription dialog;
	private JTextField champIdentite;
	private JTextField champEMail;

	public EcouteurDialogInscription(DialogInscription d, int id, JTextField identite, JTextField eMail) {
		super();
		this.dialog = d;
		this.id = id;
		this.champIdentite = identite;
		this.champEMail = eMail;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.id){
		case 1: //Bouton Valider
			//Vérification des champs
			if(champIdentite.getText().isEmpty() || champEMail.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_1, NOM_POP_UP_CTRL_CHAMPS_WARNING_1, JOptionPane.WARNING_MESSAGE);
				break;
			}
			if(!champEMail.getText().matches(REGEX_EMAIL)) {
				JOptionPane.showMessageDialog(null, MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_2, NOM_POP_UP_CTRL_CHAMPS_WARNING_2, JOptionPane.WARNING_MESSAGE);
				break;
			}
			String reponse = "";
			try {
				reponse = UtiliserWS.serviceInscription(champIdentite.getText(), champEMail.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Parsage JSON
			int rep = UtiliserWS.getReponse(reponse);
			//Affichage résultat
			if(rep == 1) {
				JOptionPane.showMessageDialog(null, MESSAGE_INSC_REUSSI, NOM_POPUP_INSC_REUSSI, JOptionPane.INFORMATION_MESSAGE);
				dialog.effacer();
			}
			else if(rep == 0)
				JOptionPane.showMessageDialog(null, MESSAGE_INSC_ECHEC, NOM_POPUP_INSC_ECHEC, JOptionPane.ERROR_MESSAGE);
			break;
		case 2: //Bouton Annuler
			dialog.effacer();
			break;
		}
	}
}






