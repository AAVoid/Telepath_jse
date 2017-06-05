package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogOrigineTelepath extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(600, 600);
	private static final boolean REDIMENSIONNABLE = false;
	private static final Color COULEUR_FOND = new Color(199, 249, 226);
	private static final String TEXTE = 
			"<html>le <h1>label du</h1> <span style=\"color:red\">bouton</span><br>ligne suivante</html>";

	private Fenetre fenetreParent;
	private Container contenu;
	private JLabel labelTexte;
	private JScrollPane scrollPane; //Pour le scrolling
	private JPanel panneauFond;

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

	public DialogOrigineTelepath(Fenetre fenetreParent, String nom, boolean modal){
		super(fenetreParent, nom, modal);
		this.fenetreParent = fenetreParent;
		setSize(DIMENSION_DIALOG.width, DIMENSION_DIALOG.height);
		setLocationRelativeTo(fenetreParent);
		setResizable(REDIMENSIONNABLE);
		
		this.labelTexte = new JLabel(TEXTE);
		this.panneauFond = new JPanel();
		this.panneauFond.setLayout(new BorderLayout());
		this.panneauFond.add(labelTexte, BorderLayout.CENTER);
		this.scrollPane = new JScrollPane(this.panneauFond);
		this.contenu = this.getContentPane();
		contenu.setLayout(new BorderLayout());		
		//JPanel panneauBackground = null;
		//try {
		//	panneauBackground = new PanneauImage(Fenetre.getNomDossierRessource() + NOM_IMAGE_BACKGROUND);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		//panneauBackground.setLayout(new BorderLayout());
		//this.contenu.add(panneauBackground, BorderLayout.CENTER);
		this.panneauFond.setBackground(COULEUR_FOND);
		contenu.add(this.scrollPane, BorderLayout.CENTER);
	}
}










