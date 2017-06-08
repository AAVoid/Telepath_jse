package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import fr.telepath.controleur.Controleur;
import fr.telepath.modele.IdentifiantSauve;
import fr.telepath.modele.PanneauIdentifiantSauve;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogIdentifiantsSauves extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(700, 300);
	private static final boolean REDIMENSIONNABLE = false;
	private static final Color COULEUR_FOND = new Color(199, 249, 226);
	private static final String TEXTE_BOUTON_AJOUTER = "Ajouter";
	private static final String TEXTE_BOUTON_ENREGISTRER = "Enregistrer";
	private static final String TEXTE_BOUTON_ANNULER = "Annuler";
	private static final Dimension DIMENSION_BOUTON_AJOUTER = new Dimension(200, 30);
	private static final Dimension DIMENSION_BOUTON_ENREGISTRER = new Dimension(200, 30);
	private static final Dimension DIMENSION_BOUTON_ANNULER = new Dimension(200, 30);
	
	private Fenetre fenetreParent;
	private Container contenu;
	private JPasswordField idPasswordField; //Celui de la fenêtre parente 
	//pour pouvoir renseigner le champs
	private ArrayList<IdentifiantSauve> listeIdentifiants;
	private JButton boutonAjouter;
	private JButton boutonEnregistrer;
	private JButton boutonAnnuler;
	private JScrollPane scrollPane; //Pour le scrolling
	private JPanel panneauId; //scrolling fait sur ce panneau
	
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
	
	public static Dimension getDimensionDialog() {
		return DIMENSION_DIALOG;
	}

	//Lit les identifiants sauvegardés dans le fichier SQLite
	private ArrayList<IdentifiantSauve> chargerListeIdSQLITE() {
		return Controleur.chargerIdentifiantsSauve();
	}

	public DialogIdentifiantsSauves(Fenetre fenetreParent, String nom, boolean modal, JPasswordField idPasswordField){
		super(fenetreParent, nom, modal);
		this.fenetreParent = fenetreParent;
		setSize(DIMENSION_DIALOG.width, DIMENSION_DIALOG.height);
		setLocationRelativeTo(this.fenetreParent);
		setResizable(REDIMENSIONNABLE);
		this.idPasswordField = idPasswordField;
		
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
		
		this.panneauId = new JPanel();
		this.panneauId.setBackground(COULEUR_FOND);
		this.panneauId.setOpaque(false);
		//BoxLayout
		LayoutManager boxLayout = new BoxLayout(this.panneauId, BoxLayout.Y_AXIS); //Vertical
		this.panneauId.setLayout(boxLayout);
		this.scrollPane = new JScrollPane(this.panneauId);
		
		this.boutonAjouter = new JButton(TEXTE_BOUTON_AJOUTER);
		this.boutonAjouter.setPreferredSize(DIMENSION_BOUTON_AJOUTER);
		this.boutonEnregistrer = new JButton(TEXTE_BOUTON_ENREGISTRER);
		this.boutonEnregistrer.setPreferredSize(DIMENSION_BOUTON_ENREGISTRER);
		this.boutonAnnuler = new JButton(TEXTE_BOUTON_ANNULER);
		this.boutonAnnuler.setPreferredSize(DIMENSION_BOUTON_ANNULER);
		JPanel panneauSud = new JPanel();
		panneauSud.setOpaque(false);
		panneauSud.setBackground(COULEUR_FOND);
		panneauSud.setLayout(new FlowLayout());
		panneauSud.add(this.boutonAjouter);
		panneauSud.add(this.boutonEnregistrer);
		panneauSud.add(this.boutonAnnuler);
		this.contenu.add(panneauSud, BorderLayout.SOUTH);
		contenu.add(this.scrollPane, BorderLayout.CENTER); //Ajout du scrollpane
		
		//Ecouteurs
		
		//chargement des identifiants sauvegardés depuis le fichier SQLite
		this.listeIdentifiants = chargerListeIdSQLITE();
		
		for(IdentifiantSauve ident : this.listeIdentifiants)
			System.out.println(ident.getId() + " / " + ident.getLabel());
		actualiserAffichage();
	}
	
	//Ajoute les panneaux dans la fenêtre en fonction du contenu de la liste d'identifiants actuelle
	public void actualiserAffichage() {
		this.panneauId.removeAll();
		for(IdentifiantSauve id : this.listeIdentifiants)
			this.panneauId.add(new PanneauIdentifiantSauve(this, id));
		this.repaint();
	}
}












