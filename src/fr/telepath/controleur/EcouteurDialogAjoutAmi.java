package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.telepath.modele.GestionDiscussion;
import fr.telepath.modele.UtiliserWS;
import fr.telepath.vue.DialogAjoutAmi;

//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurDialogAjoutAmi implements ActionListener {
	private static final String NOM_POP_UP_CTRL_CHAMPS_WARNING_1 = "Attention !";
	private static final String MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_1 = "Veuillez renseigner l'adresse e-mail !";
	private static final String NOM_POPUP_AJOUT_REUSSI = "Ajout de l'ami réussi !";
	private static final String MESSAGE_AJOUT_REUSSI = "Ajout de l'ami réussi !\nVous pouvez maintenant discuter !";
	private static final String NOM_POPUP_AJOUT_ECHEC = "Echec de l'ajout d'ami !";
	private static final String MESSAGE_AJOUT_ECHEC = "Une erreur est survenue !\nL'ajout d'ami a échoué !\nCet "
			+ "ami est peut-être déjà dans votre liste d'amis.";
	private static final String REGEX_EMAIL = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";
	private static final String NOM_POP_UP_CTRL_CHAMPS_WARNING_2 = "Attention !";
	private static final String MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_2 = "Veuillez utiliser une adresse e-mail valide !";
	
	private int id;
	private DialogAjoutAmi dialog;
	private JTextField champEmail;
	
	public EcouteurDialogAjoutAmi(DialogAjoutAmi d, int id, JTextField champEmail) {
		super();
		this.dialog = d;
		this.id = id;
		this.champEmail = champEmail;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.id){
		case 1: //Bouton Valider
			//Vérification des champs
			if(this.champEmail.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_1, NOM_POP_UP_CTRL_CHAMPS_WARNING_1, JOptionPane.WARNING_MESSAGE);
				break;
			}
			if(!this.champEmail.getText().matches(REGEX_EMAIL)) {
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_2, NOM_POP_UP_CTRL_CHAMPS_WARNING_2, JOptionPane.WARNING_MESSAGE);
				break;
			}
			String reponse = "";
			try {
				reponse = UtiliserWS.serviceAjoutAmi(GestionDiscussion.getIdentifiantUtilisateur(), champEmail.getText().replaceAll(" ", ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Parsage JSON
			int rep = UtiliserWS.getReponse(reponse);
			if(rep == 1) {
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_AJOUT_REUSSI, 
						NOM_POPUP_AJOUT_REUSSI, JOptionPane.INFORMATION_MESSAGE);
				dialog.effacer();
				//On actualise la fenêtre
				dialog.getFenetre().afficherListeAmis();
			}
			else if(rep == 0)
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_AJOUT_ECHEC, NOM_POPUP_AJOUT_ECHEC, JOptionPane.ERROR_MESSAGE);
			break;
		case 2: //BOUTON ANNULER
			dialog.effacer(); 
			break;
		}
	}
}








