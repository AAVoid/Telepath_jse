package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.telepath.modele.UtiliserWS;
import fr.telepath.vue.DialogActivation;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurDialogActivation implements ActionListener {
	private static final String NOM_POPUP_INSC_REUSSI = "Inscription reussi !";
	private static final String MESSAGE_INSC_REUSSI = "Activation reussi !\n"
			+ "Vous pouvez maintenant vous connecter et utiliser votre compte !\n";
	private static final String NOM_POPUP_INSC_ECHEC = "Echec de l'activation !";
	private static final String MESSAGE_INSC_ECHEC = "Une erreur est survenue ! L'activation a echoue !\n\n"
			+ "Votre code d'activation est incorrect ou a deja ete utilise.";
	private static final String NOM_POP_UP_CTRL_CHAMPS_WARNING_1 = "Attention !";
	private static final String MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_1 = "Veuillez renseigner le code !";
	private static final String CHAINE_AFFICHAGE_ID = "\nVotre identifiant de connexion :\n\n";
	private static final String NOM_POP_UP_COPIER_PRESSE_PAPIER = "Copier l'identifiant de connexion ?";
	private static final String MESSAGE_POP_UP_COPIER_PRESSE_PAPIER = "Voulez-vous copier l'identifiant de connexion dans le presse papier ?\n\n"
			+ "Un fichier contenant l'identifiant sera genere sur votre bureau dans tous les cas.";
	
	private int id;
	private DialogActivation dialog;
	private JTextField champCode;

	public EcouteurDialogActivation(DialogActivation d, int id, JTextField code) {
		super();
		this.dialog = d;
		this.id = id;
		this.champCode = code;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.id){
		case 1: //Bouton Valider
			//Vérification des champs
			if(champCode.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_1, NOM_POP_UP_CTRL_CHAMPS_WARNING_1, JOptionPane.WARNING_MESSAGE);
				break;
			}
			String reponse = "";
			try {
				reponse = UtiliserWS.serviceActivation(champCode.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Parsage JSON
			int rep = UtiliserWS.getReponse(reponse);
			//Affichage du résultat
			if(rep == 1) {
				//Recuperation de l'identifiant
				String id = UtiliserWS.getIdApresActivation(reponse);
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_INSC_REUSSI +
						CHAINE_AFFICHAGE_ID + id, 
						NOM_POPUP_INSC_REUSSI, JOptionPane.INFORMATION_MESSAGE);
				//Copie de l'id dans le presse papier ?
				int choixCopierId = JOptionPane.showConfirmDialog(null, MESSAGE_POP_UP_COPIER_PRESSE_PAPIER, 
						NOM_POP_UP_COPIER_PRESSE_PAPIER, 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				//Copie de l'identifiant dans le presse papier
				if(choixCopierId == JOptionPane.OK_OPTION)
					Fenetre.copierDansPressePapier(id);
				//Création fichier identifiant / ajout à la suite
				dialog.effacer();
			}
			else if(rep == 0)
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_INSC_ECHEC, NOM_POPUP_INSC_ECHEC, JOptionPane.ERROR_MESSAGE);
			break;
		case 2: //Bouton Annuler
			dialog.effacer(); 
			break;
		}
	}
}










