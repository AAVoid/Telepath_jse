package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import fr.telepath.controleur.Controleur;
import fr.telepath.controleur.EcouteurDialogIdentifiantSauve;
import fr.telepath.modele.IdentifiantSauve;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogIdentifiantsSauves extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(700, 400);
	private static final boolean REDIMENSIONNABLE = false;
	private static final Color COULEUR_FOND = new Color(199, 249, 226);
	private static final Color COULEUR_FOND2 = new Color(160, 196, 255);
	private static final Color COULEUR_FOND_PANNEAU_BAS = new Color(30, 115, 252);
	private static final String TEXTE_BOUTON_AJOUTER = "Ajouter";
	private static final String TEXTE_BOUTON_ENREGISTRER = "Enregistrer";
	private static final Dimension DIMENSION_BOUTON_AJOUTER = new Dimension(200, 30);
	private static final Dimension DIMENSION_BOUTON_ENREGISTRER = new Dimension(200, 30);
	private static final String NOM_ICONE_AJOUTER = "ajouterId.png";
	private static final String NOM_ICONE_ENREGISTRER = "sauverId.png";

	private Fenetre fenetreParent;
	private Container contenu;
	private JPasswordField idPasswordField; //Celui de la fenêtre parente 
	//pour pouvoir renseigner le champs
	private ArrayList<IdentifiantSauve> listeIdentifiants;
	private JButton boutonAjouter;
	private JButton boutonEnregistrer;
	private JScrollPane scrollPane; //Pour le scrolling
	private JPanel panneauId; //scrolling fait sur ce panneau

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

	public static Dimension getDimensionDialog() {
		return DIMENSION_DIALOG;
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
		this.panneauId.setOpaque(true);
		//BoxLayout
		LayoutManager boxLayout = new BoxLayout(this.panneauId, BoxLayout.Y_AXIS); //Vertical
		this.panneauId.setLayout(boxLayout);
		this.scrollPane = new JScrollPane(this.panneauId);

		this.boutonAjouter = new JButton(TEXTE_BOUTON_AJOUTER);
		this.boutonAjouter.setPreferredSize(DIMENSION_BOUTON_AJOUTER);
		try {
			Image img = ImageIO.read(new File(Fenetre.getNomDossierIcone() + NOM_ICONE_AJOUTER));
			this.boutonAjouter.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.boutonEnregistrer = new JButton(TEXTE_BOUTON_ENREGISTRER);
		this.boutonEnregistrer.setPreferredSize(DIMENSION_BOUTON_ENREGISTRER);
		try {
			Image img = ImageIO.read(new File(Fenetre.getNomDossierIcone() + NOM_ICONE_ENREGISTRER));
			this.boutonEnregistrer.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel panneauSud = new JPanel();
		panneauSud.setOpaque(true);
		panneauSud.setBackground(COULEUR_FOND_PANNEAU_BAS);
		panneauSud.setLayout(new FlowLayout());
		panneauSud.add(this.boutonAjouter);
		panneauSud.add(this.boutonEnregistrer);
		this.contenu.add(panneauSud, BorderLayout.SOUTH);
		contenu.add(this.scrollPane, BorderLayout.CENTER); //Ajout du scrollpane

		//chargement des identifiants sauvegardés depuis le fichier SQLite
		this.listeIdentifiants = Controleur.chargerIdentifiantsSauve();
		/*for(IdentifiantSauve ident : this.listeIdentifiants)
			System.out.println(ident.getId() + " / " + ident.getLabel());*/

		//Ecouteurs
		this.boutonAjouter.addActionListener(new EcouteurDialogIdentifiantSauve(this, 1, this.listeIdentifiants));
		this.boutonEnregistrer.addActionListener(new EcouteurDialogIdentifiantSauve(this, 2, this.listeIdentifiants));
		actualiserAffichage();
	}

	//Ajoute les panneaux dans la fenêtre en fonction du contenu de la liste d'identifiants actuelle
	public void actualiserAffichage() {
		this.panneauId.removeAll();
		int couleur = 0;
		for(IdentifiantSauve idAssocie : this.listeIdentifiants) {
			this.panneauId.add(new PanneauIdentifiantSauve(this, this.listeIdentifiants, idAssocie,
					(couleur == 0) ? COULEUR_FOND : COULEUR_FOND2));
			couleur = ++couleur % 2;
		}
		this.validate();
		this.repaint();
	}
	
	//Crée une liste d'identifiants à partir du contenu de la fenêtre
	//Utilisé pour l'enregistrements dans l'écouteur dialog identifiant sauvés
	public ArrayList<IdentifiantSauve> creerListeIdFromContenu() {
		ArrayList<IdentifiantSauve> listeIdentifiants = new ArrayList<IdentifiantSauve>();
		for(Component comp : this.panneauId.getComponents()) {
			listeIdentifiants.add(new IdentifiantSauve(((PanneauIdentifiantSauve)comp).getIdentifiant().getText().replaceAll(" ", ""), 
					((PanneauIdentifiantSauve)comp).getLabel().getText()));
		}
		return listeIdentifiants;
	}

	public JPasswordField getIdPasswordField() {
		return idPasswordField;
	}

	@SuppressWarnings("unused")
	private void setIdPasswordField(JPasswordField idPasswordField) {
	this.idPasswordField = idPasswordField;
	}

	public JPanel getPanneauId() {
		return panneauId;
	}

	@SuppressWarnings("unused")
	private void setPanneauId(JPanel panneauId) {
		this.panneauId = panneauId;
	}
	
	public ArrayList<IdentifiantSauve> getListeIdentifiants() {
		return listeIdentifiants;
	}

	public void setListeIdentifiants(ArrayList<IdentifiantSauve> listeIdentifiants) {
		this.listeIdentifiants = listeIdentifiants;
	}
}












