package fr.telepath.modele;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.telepath.vue.DialogIdentifiantsSauves;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class PanneauIdentifiantSauve extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String LABEL_IDENTIFIANT = "Identifiant";
	private static final String LABEL_LABEL = "Label";
	private static final String TEXTE_BOUTON_CONNECTER = "Se connecter";
	private static final String TEXTE_BOUTON_SUPPRIMER = "Supprimer";
	
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
		
		setLayout(new GridLayout(2, 1)); //2 ligne 1 colonne
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new FlowLayout());
		panneauHaut.add(this.labelIdentifiant);
		panneauHaut.add(this.identifiant);
		panneauHaut.add(this.labelLabel);
		panneauHaut.add(this.label);
		
		JPanel panneauBas = new JPanel();
		panneauBas.setLayout(new FlowLayout());
		panneauBas.add(this.boutonConnecter);
		panneauBas.add(this.boutonSupprimer);
		
		//Ecouteur
	}
}












