package fr.telepath.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.telepath.controleur.EcouteurPanneauIdentifiantSauve;
import fr.telepath.modele.IdentifiantSauve;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class PanneauIdentifiantSauve extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String LABEL_IDENTIFIANT = "<html><font size=\"5\">Identifiant</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>";
	private static final String LABEL_LABEL = "<html><font size=\"5\">Label</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html>";
	private static final String TEXTE_BOUTON_CONNECTER = "Se connecter";
	private static final String TEXTE_BOUTON_SUPPRIMER = "Supprimer";
	private static final Dimension DIMENSION_CHAMP_IDENTIFIANT = new Dimension(230, 30);
	private static final Dimension DIMENSION_CHAMP_LABEL = new Dimension(230, 30);
	private static final Dimension DIMENSION_BOUTON_CONNECTER = new Dimension(200, 30);
	private static final Dimension DIMENSION_BOUTON_SUPPRIMER = new Dimension(200, 30);
	private static final Color COULEUR_BORDURE = new Color(66, 152, 244);
	private static final String NOM_BORDURE = "";
	private static final Dimension DIMENSION_PANNEAUX_MARGE = 
			new Dimension(DialogIdentifiantsSauves.getDimensionDialog().width, 10);
	private static final String NOM_ICONE_CONNECTER = "valider.png";
	private static final String NOM_ICONE_SUPPRIMER = "annuler.png";

	private DialogIdentifiantsSauves dialogParent; //Pour pouvoir lancer l'actualisation
	//de l'affichage dans la dialog parent
	private ArrayList<IdentifiantSauve> listeIdentifiants;
	private IdentifiantSauve identifiantAssocie;
	private JTextField identifiant;
	private JTextField label;
	private JLabel labelIdentifiant;
	private JLabel labelLabel;
	private JButton boutonConnecter;
	private JButton boutonSupprimer;

	public PanneauIdentifiantSauve(DialogIdentifiantsSauves dialogParent, 
			ArrayList<IdentifiantSauve> listeIdentifiants, IdentifiantSauve identifiantAssocie, Color couleur_fond) {
		super();
		this.dialogParent = dialogParent;
		this.listeIdentifiants = listeIdentifiants;
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
		try {
			Image img = ImageIO.read(new File(Fenetre.getNomDossierIcone() + NOM_ICONE_CONNECTER));
			this.boutonConnecter.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.boutonSupprimer.setPreferredSize(DIMENSION_BOUTON_SUPPRIMER);
		try {
			Image img = ImageIO.read(new File(Fenetre.getNomDossierIcone() + NOM_ICONE_SUPPRIMER));
			this.boutonSupprimer.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setBackground(couleur_fond);
		setLayout(new GridLayout(5, 1)); //3 ligne 1 colonne
		setOpaque(true); 

		JPanel panneauMargeHaut = new JPanel();
		panneauMargeHaut.setBackground(couleur_fond);
		panneauMargeHaut.setPreferredSize(DIMENSION_PANNEAUX_MARGE);

		JPanel panneauHaut = new JPanel();
		panneauHaut.setBackground(couleur_fond);
		panneauHaut.setLayout(new FlowLayout());
		panneauHaut.add(this.labelIdentifiant);
		panneauHaut.add(this.identifiant);

		JPanel panneauMilieu = new JPanel();
		panneauMilieu.setBackground(couleur_fond);
		panneauMilieu.setLayout(new FlowLayout());
		panneauMilieu.add(this.labelLabel);
		panneauMilieu.add(this.label);

		JPanel panneauBas = new JPanel();
		panneauBas.setBackground(couleur_fond);
		panneauBas.setLayout(new FlowLayout());
		panneauBas.add(this.boutonConnecter);
		panneauBas.add(this.boutonSupprimer);

		JPanel panneauMargeBas = new JPanel();
		panneauMargeBas.setBackground(couleur_fond);
		panneauMargeBas.setPreferredSize(DIMENSION_PANNEAUX_MARGE);

		//this.add(panneauMargeHaut);
		this.add(panneauMargeHaut);
		this.add(panneauHaut);
		this.add(panneauMilieu);
		this.add(panneauBas);
		this.add(panneauMargeBas);

		//Ecouteur
		boutonConnecter.addActionListener(new EcouteurPanneauIdentifiantSauve(this.dialogParent, 1, 
				this.listeIdentifiants, this.identifiantAssocie));
		boutonSupprimer.addActionListener(new EcouteurPanneauIdentifiantSauve(this.dialogParent, 2, 
				this.listeIdentifiants, this.identifiantAssocie));
	}

	public JTextField getIdentifiant() {
		return identifiant;
	}

	/*public void setIdentifiant(JTextField identifiant) {
		this.identifiant = identifiant;
	}*/

	public JTextField getLabel() {
		return label;
	}

	/*public void setLabel(JTextField label) {
		this.label = label;
	}*/
}












