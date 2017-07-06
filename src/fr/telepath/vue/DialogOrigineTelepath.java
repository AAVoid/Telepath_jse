package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogOrigineTelepath extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(500, 300);
	private static final boolean REDIMENSIONNABLE = false;
	private static final Color COULEUR_FOND = new Color(199, 249, 226);
	private static final int MARGE_TEXTE_GAUCHE = 10;
	private static final String TEXTE = 
			"<html>"
			+ "<h1>Origine de <i>" + Fenetre.getNomApplication() + "</i></h1>"
			+ "<i>" + Fenetre.getNomApplication() + " </i>est une application de discussion créée par <i><br><font size=\"4\">" + Fenetre.getNomAuteur() + "</font></i>.<br>" 
			+ "Une partie de la réalisation a été faite par son professeur, monsieur<br>Jimmy NAGAU.<br>"
			+ Fenetre.getNomApplication() + " a été réalisée en 2017 dans le cadre d'un projet universitaire de "
			+ "<br><i>Master 1 MIAGE de l'université des Antilles en Guadeloupe (Caraïbes).</i>"
			+ "</html>";

	private Fenetre fenetreParent;
	private Container contenu;
	private JLabel labelTexte;
	private JScrollPane scrollPane; //Pour le scrolling
	private JPanel panneauFond; //scrolling fait sur ce panneau

	public void updateAffichage() {
		this.contenu.validate();
		this.contenu.repaint();
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
		setLocationRelativeTo(this.fenetreParent);
		setResizable(REDIMENSIONNABLE);
		
		this.labelTexte = new JLabel(TEXTE);
		this.panneauFond = new JPanel();
		this.panneauFond.setLayout(new BorderLayout());
		this.panneauFond.add(labelTexte, BorderLayout.CENTER);
		
		JPanel panneauMargeGauche = new JPanel();
		panneauMargeGauche.setPreferredSize(new Dimension(MARGE_TEXTE_GAUCHE, DIMENSION_DIALOG.height));
		panneauMargeGauche.setBackground(COULEUR_FOND);
		
		panneauFond.add(panneauMargeGauche, BorderLayout.WEST);
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










