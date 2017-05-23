package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPasswordField;

import fr.telepath.controleur.Controleur;
import fr.telepath.modele.IdentifiantSauve;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogIdentifiantsSauves extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(600, 600);
	private static final boolean REDIMENSIONNABLE = false;
	private static final Color COULEUR_FOND = new Color(199, 249, 226);
	
	private Fenetre fenetreParent;
	private Container contenu;
	private JPasswordField idPasswordField; //Celui de la fenêtre parente pour pouvoir renseigner le champs
	private ArrayList<IdentifiantSauve> listeIdentifiants;
	
	public void updateAffichage() {
		this.contenu.validate();
	}

	public void afficher() {
		updateAffichage();
		this.setVisible(true);
	}

	public void effacer() {
		this.setVisible(false);
	}
	
	//Lit les identifiants sauvegardés dans le fichier SQLite
	private void chargerListeIdSQLITE() {
		this.listeIdentifiants = Controleur.chargerIdentifiantsSauve();
	}

	public DialogIdentifiantsSauves(Fenetre fenetreParent, String nom, boolean modal, JPasswordField idPasswordField){
		super(fenetreParent, nom, modal);
		this.fenetreParent = fenetreParent;
		setSize(DIMENSION_DIALOG.width, DIMENSION_DIALOG.height);
		setLocationRelativeTo(fenetreParent);
		setResizable(REDIMENSIONNABLE);
		
		this.contenu = this.getContentPane();
		contenu.setLayout(new BorderLayout());		
//		JPanel panneauBackground = null;
//		try {
//			panneauBackground = new PanneauImage(Fenetre.getNomDossierRessource() + NOM_IMAGE_BACKGROUND);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		panneauBackground.setLayout(new BorderLayout());
//		this.contenu.add(panneauBackground, BorderLayout.CENTER);
		contenu.setBackground(COULEUR_FOND);
		this.idPasswordField = idPasswordField;
		
		//chargement des identifiants sauvegardés depuis le fichier SQLite
		chargerListeIdSQLITE();
	}
}












