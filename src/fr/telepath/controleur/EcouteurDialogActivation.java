package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import fr.telepath.modele.Fichier;
import fr.telepath.modele.UtiliserWS;
import fr.telepath.vue.DialogActivation;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurDialogActivation implements ActionListener {
	private static final String NOM_POPUP_ACTIV_REUSSI = "Activation réussi !";
	private static final String MESSAGE_ACTIV_REUSSI = "Activation réussi !\n"
			+ "Vous pouvez maintenant vous connecter et utiliser votre compte !\n";
	private static final String NOM_POPUP_ACTIV_ECHEC = "Echec de l'activation !";
	private static final String MESSAGE_ACTIV_ECHEC = "Une erreur est survenue ! L'activation a échoué !\n\n"
			+ "Votre code d'activation est incorrect ou a déjà été utilisé.";
	private static final String NOM_POP_UP_CTRL_CHAMPS_WARNING_1 = "Attention !";
	private static final String MESSAGE_POP_UP_CTRL_CHAMPS_WARNING_1 = "Veuillez renseigner le code !";
	private static final String CHAINE_AFFICHAGE_ID = "\nVotre identifiant de connexion :\n\n";
	private static final String NOM_POP_UP_COPIER_PRESSE_PAPIER = "Copier l'identifiant de connexion ?";
	private static final String MESSAGE_POP_UP_COPIER_PRESSE_PAPIER = "Voulez-vous copier votre identifiant de connexion dans le presse papier ?\n\n"
			+ "Un fichier contenant votre identifiant sera généré dans tous les cas.";
	private static final String MESSAGE_INFORMATION_GESTIONNAIRE = "Vous pouvez enregistrer votre identifiant dans le gestionnaire\n"
			+ "d'identifiants pour éviter de le retaper.";
	private static final String CHEMIN_FICHIER_IDENTIFIANT_SAUVEGARDE = 
			FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + File.separator + "identifiant.txt";
	private static final String NOM_POPUP_FICHIER_IDENTIFIANT_CREE = "Identifiant sauvegardé !";
	private static final String MESSAGE_POPUP_FICHIER_IDENTIFIANT_CREE = "Votre identifiant a été sauvegardé dans le fichier : \n\n"
			+ CHEMIN_FICHIER_IDENTIFIANT_SAUVEGARDE;

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
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_ACTIV_REUSSI +
						CHAINE_AFFICHAGE_ID + id + "\n\n" + MESSAGE_INFORMATION_GESTIONNAIRE, 
						NOM_POPUP_ACTIV_REUSSI, JOptionPane.INFORMATION_MESSAGE);
				//Copie de l'id dans le presse papier ?
				int choixCopierId = JOptionPane.showConfirmDialog(null, MESSAGE_POP_UP_COPIER_PRESSE_PAPIER, 
						NOM_POP_UP_COPIER_PRESSE_PAPIER, 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				//Copie de l'identifiant dans le presse papier
				if(choixCopierId == JOptionPane.OK_OPTION)
					Fenetre.copierDansPressePapier(id);
				//Création fichier identifiant / ajout à la suite
				/*File home = FileSystemView.getFileSystemView().getHomeDirectory();
				System.out.println(FileSystemView.getFileSystemView().getHomeDirectory());
				System.out.println(home.getAbsolutePath());
				System.out.println(System.getProperty("user.home"));*/
				FileOutputStream fichIdentifiantsSav = null;
				try {
					fichIdentifiantsSav = new FileOutputStream(CHEMIN_FICHIER_IDENTIFIANT_SAUVEGARDE);
				} 
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Fichier.EcrireFichierBinaire(fichIdentifiantsSav, id.getBytes());
				try {
					fichIdentifiantsSav.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_POPUP_FICHIER_IDENTIFIANT_CREE, 
						NOM_POPUP_FICHIER_IDENTIFIANT_CREE, JOptionPane.INFORMATION_MESSAGE);
				dialog.effacer();
			}
			else if(rep == 0)
				JOptionPane.showMessageDialog(this.dialog, MESSAGE_ACTIV_ECHEC, NOM_POPUP_ACTIV_ECHEC, JOptionPane.ERROR_MESSAGE);
			break;
		case 2: //Bouton Annuler
			dialog.effacer(); 
			break;
		}
	}
}










