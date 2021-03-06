package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Universit� des Antilles - Guadeloupe
//2016 - 2017



public class DialogOrigineTelepath extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(500, 300);
	private static final boolean REDIMENSIONNABLE = false;
	private static final Color COULEUR_TEXTE = Color.WHITE;
	private static final int MARGE_TEXTE_GAUCHE = 10;
	private static final String NOM_IMAGE_FOND = "BackgroundOrigine.jpg";
	private static final String TEXTE = 
			"<html>"
					+ "<h1>Origine de <i>" + Fenetre.getNomApplication() + "</i></h1>"
					+ "<i>" + Fenetre.getNomApplication() + " </i>est une application de discussion cr��e par <i><br><font size=\"4\">" + Fenetre.getNomAuteur() + "</font></i>.<br>" 
					+ "Une partie de la r�alisation a �t� faite par son professeur, monsieur<br>Jimmy NAGAU.<br>"
					+ Fenetre.getNomApplication() + " a �t� r�alis�e en 2017 dans le cadre d'un projet universitaire de "
					+ "<br><i>Master 1 MIAGE de l'universit� des Antilles en Guadeloupe (Cara�bes).</i>"
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
		this.labelTexte.setForeground(COULEUR_TEXTE);
		try {
			this.panneauFond = new PanneauImage(Fenetre.getNomDossierBackground() + NOM_IMAGE_FOND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.panneauFond.setLayout(new BorderLayout());
		this.panneauFond.add(labelTexte, BorderLayout.CENTER);

		JPanel panneauMargeGauche = new JPanel();
		panneauMargeGauche.setOpaque(false);
		panneauMargeGauche.setPreferredSize(new Dimension(MARGE_TEXTE_GAUCHE, DIMENSION_DIALOG.height));
		panneauFond.add(panneauMargeGauche, BorderLayout.WEST);

		this.scrollPane = new JScrollPane(this.panneauFond);

		this.contenu = this.getContentPane();
		contenu.setLayout(new BorderLayout());		

		contenu.add(this.scrollPane, BorderLayout.CENTER);
	}
}










