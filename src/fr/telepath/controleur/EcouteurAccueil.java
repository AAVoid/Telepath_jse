package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import fr.telepath.modele.GestionDiscussion;
import fr.telepath.modele.UtiliserWS;
import fr.telepath.vue.DialogActivation;
import fr.telepath.vue.DialogIdentifiantsSauves;
import fr.telepath.vue.DialogInscription;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Universit� des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurAccueil implements ActionListener {
	private static final String NOM_DIALOG_INSCRIPTION = "Inscription";
	private static final String NOM_DIALOG_ACTIVATION = "Activation de compte";
	private static final String NOM_DIALOG_IDENTIFIANTS_SAUVES = "Identifiants sauvegard�s";
	private static final boolean DIALOG_MODAL = true;
	private static final String NOM_POPUP_INFO_ID_SAUVES = "Informations";
	private static final String MESSAGE_POPUP_INFO_ID_SAUVES = "Vous pouvez g�rer vos identifiants dans la fen�tre "
			+ "de gestion des identifiants.\n\nProcessus : Suppressions / Ajouts �ventuels, �dition puis enregistrement\n\n"
			+ "En effet :\nSi vous �ditez un identifiant pour ensuite le supprimer ou supprimer un autre identifiant\n"
			+ "sans avoir enregistr� apr�s l'�dition vous devrez resaisir les informations du ou des identifiants �dit�s / ajout�s !";
	private static final String NOM_POPUP_CONNEXION_ECHEC = "Erreur de connexion";
	private static final String MESSAGE_POPUP_CONNEXION_ECHEC = "Erreur de connexion !\n\nVotre identifiant est "
			+ "peut-�tre incorrect."
			+ "\nLe syst�me est peut-�tre indisponible.\n\nVeuillez essayer de vous connecter de nouveau.";
	
	private int id;
	private Fenetre fenetre;
	private JPasswordField passwordField;
	
	public EcouteurAccueil(Fenetre fen, int id, JPasswordField passwordField) {
		super();
		this.fenetre = fen;
		this.id = id;
		this.passwordField = passwordField;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.id){
		case 1: //Bouton connexion
			String identifiant = (new String(passwordField.getPassword())).replaceAll(" ", "");
			String reponse = "";
			try {
				reponse = UtiliserWS.serviceObtenirParametres(identifiant);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Parsage JSON
			int rep = UtiliserWS.getReponse(reponse);
			if(rep == 1) { //Connect�
				GestionDiscussion.setIdentifiantUtilisateur(identifiant); //Enregistrement de l'identifiant pour la suite
				String identite = UtiliserWS.getIdentiteApresParametre(reponse);
				GestionDiscussion.setIdentiteUtilisateur(identite);
				this.fenetre.afficherListeAmis();
			}
			else { //La connexion a �chou�
				JOptionPane.showMessageDialog(this.fenetre, MESSAGE_POPUP_CONNEXION_ECHEC, 
						NOM_POPUP_CONNEXION_ECHEC, JOptionPane.ERROR_MESSAGE);
			}
			break;
		case 2: //Bouton inscription
			DialogInscription dialogInscription = new DialogInscription(fenetre, NOM_DIALOG_INSCRIPTION, DIALOG_MODAL);
			dialogInscription.afficher();
			break;
		case 3: //Bouton activer compte
			DialogActivation dialogActivation = new DialogActivation(fenetre, NOM_DIALOG_ACTIVATION, DIALOG_MODAL);
			dialogActivation.afficher();
			break;
		case 4: //Bouton identifiants sauv�s
			//Affichage informations
			JOptionPane.showMessageDialog(this.fenetre, MESSAGE_POPUP_INFO_ID_SAUVES, 
					NOM_POPUP_INFO_ID_SAUVES, JOptionPane.INFORMATION_MESSAGE);
			DialogIdentifiantsSauves dialogIdentifiantsSauves = new DialogIdentifiantsSauves(fenetre, 
					NOM_DIALOG_IDENTIFIANTS_SAUVES, DIALOG_MODAL, passwordField);
			dialogIdentifiantsSauves.afficher();
			break;
		}
	}
}






