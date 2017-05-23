package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.telepath.controleur.EcouteurDialogActivation;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogActivation extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(300, 100);
	private static final String LABEL_CODE = "Code ";
	private static final boolean REDIMENSIONNABLE = false;
	private static final String LABEL_BOUTON_VALIDER = "Valider";
	private static final String LABEL_BOUTON_ANNULER = "Annuler";
	private static final Dimension DIMENSION_CHAMP_CODE = new Dimension(225, 30);
	private static final String NOM_IMAGE_BACKGROUND = "background2.jpg";
	private static final Color COULEUR_LABEL_CODE = new Color(255, 255, 255);
	
	private Container contenu;
	
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

	public DialogActivation(Fenetre fenetreParent, String nom, boolean modal){
		super(fenetreParent, nom, modal);
		setSize(DIMENSION_DIALOG.width, DIMENSION_DIALOG.height);
		setLocationRelativeTo(fenetreParent);
		setResizable(REDIMENSIONNABLE);
		
		this.contenu = this.getContentPane();
		contenu.setLayout(new BorderLayout());		
		JPanel panneauBackground = null;
		try {
			panneauBackground = new PanneauImage(Fenetre.getNomDossierRessource() + NOM_IMAGE_BACKGROUND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panneauBackground.setLayout(new BorderLayout());
		this.contenu.add(panneauBackground, BorderLayout.CENTER);
		
		JLabel labelCode = new JLabel(LABEL_CODE);
		labelCode.setForeground(COULEUR_LABEL_CODE);
		JButton boutonValider = new JButton(LABEL_BOUTON_VALIDER);
		JButton boutonAnnuler = new JButton(LABEL_BOUTON_ANNULER);
		JTextField champCode = new JTextField();
		champCode.setPreferredSize(DIMENSION_CHAMP_CODE);
		
		boutonValider.addActionListener(new EcouteurDialogActivation(this, 1, champCode));
		boutonAnnuler.addActionListener(new EcouteurDialogActivation(this, 2, null));
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.add(labelCode);
		p1.add(champCode);
		
		
		JPanel p2 = new JPanel();
		p2.setOpaque(false);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.add(boutonValider);
		p2.add(boutonAnnuler);
		
		panneauBackground.add(p1, BorderLayout.CENTER);
		panneauBackground.add(p2, BorderLayout.SOUTH);
	}
}












