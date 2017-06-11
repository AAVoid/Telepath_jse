package fr.telepath.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.telepath.modele.IdentifiantSauve;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class PanneauIdentifiantSauve extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String LABEL_IDENTIFIANT = "Identifiant";
	private static final String LABEL_LABEL = "Label";
	private static final String TEXTE_BOUTON_CONNECTER = "Se connecter";
	private static final String TEXTE_BOUTON_SUPPRIMER = "Supprimer";
	private static final Dimension DIMENSION_CHAMP_IDENTIFIANT = new Dimension(230, 30);
	private static final Dimension DIMENSION_CHAMP_LABEL = new Dimension(230, 30);
	private static final Dimension DIMENSION_BOUTON_CONNECTER = new Dimension(200, 30);
	private static final Dimension DIMENSION_BOUTON_SUPPRIMER = new Dimension(200, 30);
	private static final Color COULEUR_BORDURE = new Color(66, 152, 244);
	private static final String NOM_BORDURE = "";
	private static final Dimension DIMENSION_PANNEAUX_MARGE = 
			new Dimension(DialogIdentifiantsSauves.getDimensionDialog().width, 100);
	
	private DialogIdentifiantsSauves dialogParent; //Pour pouvoir lancer l'actualisation
	//de l'affichage dans la dialog parent
	private IdentifiantSauve identifiantAssocie;
	private JTextField identifiant;
	private JTextField label;
	private JLabel labelIdentifiant;
	private JLabel labelLabel;
	private JButton boutonConnecter;
	private JButton boutonSupprimer;
	
	public PanneauIdentifiantSauve(DialogIdentifiantsSauves dialogParent, 
			IdentifiantSauve identifiantAssocie) {
		super();
		this.dialogParent = dialogParent;
		this.identifiantAssocie = identifiantAssocie;
		this.identifiant = new JTextField(identifiantAssocie.getId());
		this.label = new JTextField(identifiantAssocie.getLabel());
		this.labelIdentifiant = new JLabel(LABEL_IDENTIFIANT);
		this.labelLabel = new JLabel(LABEL_LABEL);
		this.boutonConnecter = new JButton(TEXTE_BOUTON_CONNECTER);
		this.boutonSupprimer = new JButton(TEXTE_BOUTON_SUPPRIMER);
		
		//Ajout bordure
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COULEUR_BORDURE), NOM_BORDURE));
		
		this.identifiant.setPreferredSize(DIMENSION_CHAMP_IDENTIFIANT);
		this.label.setPreferredSize(DIMENSION_CHAMP_LABEL);
		this.boutonConnecter.setPreferredSize(DIMENSION_BOUTON_CONNECTER);
		this.boutonSupprimer.setPreferredSize(DIMENSION_BOUTON_SUPPRIMER);
		
		setLayout(new GridLayout(4, 1)); //4 ligne 1 colonne
		setOpaque(false); 
		
		JPanel panneauMargeHaut = new JPanel();
		panneauMargeHaut.setOpaque(false);
		panneauMargeHaut.setPreferredSize(DIMENSION_PANNEAUX_MARGE);
		
		JPanel panneauHaut = new JPanel();
		panneauHaut.setOpaque(false);
		panneauHaut.setLayout(new FlowLayout());
		panneauHaut.add(this.labelIdentifiant);
		panneauHaut.add(this.identifiant);
		panneauHaut.add(this.labelLabel);
		panneauHaut.add(this.label);
		
		JPanel panneauBas = new JPanel();
		panneauBas.setOpaque(false);
		panneauBas.setLayout(new FlowLayout());
		panneauBas.add(this.boutonConnecter);
		panneauBas.add(this.boutonSupprimer);
		
		JPanel panneauMargeBas = new JPanel();
		panneauMargeBas.setOpaque(false);
		panneauMargeBas.setPreferredSize(DIMENSION_PANNEAUX_MARGE);
		
		//this.add(panneauMargeHaut);
		this.add(panneauHaut);
		this.add(panneauBas);
		//this.add(panneauMargeBas);
		
		//Ecouteur
	}
}












