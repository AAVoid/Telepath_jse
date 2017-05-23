package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import fr.telepath.controleur.EcouteurAccueil;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int LARGEUR = 600;
	private static final int HAUTEUR = 600;
	private static final boolean REDIMENSIONNABLE = false;
	private static final String VERSION = "0.1";
	private static final String NOM_FENETRE = "Télépath - Aymerik ABOSO ; M1 MIAGE ; 2016 - 2017 ; V" + VERSION;
	private static final String NOM_DOSSIER_RESSOURCE = "Ressources/";
	private static final String NOM_IMAGE_LOGO = "logo.png";
	private static final String NOM_IMAGE_ICONE = "icone_fenetre.png";
	private static final String NOM_IMAGE_CURSEUR = "curseur.png";

	private Container contenu; //Pour la content pane

	public Fenetre() {
		super();
		this.setSize(LARGEUR, HAUTEUR);
		this.setResizable(REDIMENSIONNABLE);
		this.setTitle(NOM_FENETRE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrêter le processus quand la fenêtre est fermée
		this.setLocationRelativeTo(null); //On place la fenêtre au centre de l'écran
		this.contenu = this.getContentPane();
		//ICONE DE LA FENETRE
		try {
			setIconImage(ImageIO.read(new File(NOM_DOSSIER_RESSOURCE + NOM_IMAGE_ICONE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//CURSEUR DANS LA FENETRE
		getRootPane().setCursor(java.awt.Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(NOM_DOSSIER_RESSOURCE + NOM_IMAGE_CURSEUR).getImage(), new Point(1,1),"customCursor"));
		afficherPageAccueil();
	}

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

	public static int getLargeur() {
		return LARGEUR;
	}

	public static int getHauteur() {
		return HAUTEUR;
	}

	public static boolean isRedimensionnable() {
		return REDIMENSIONNABLE;
	}

	public static String getVersion() {
		return VERSION;
	}

	public static String getNomFenetre() {
		return NOM_FENETRE;
	}

	public static String getNomDossierRessource() {
		return NOM_DOSSIER_RESSOURCE;
	}

	public static String getNomImageLogo() {
		return NOM_IMAGE_LOGO;
	}

	public static String getNomImageIcone() {
		return NOM_IMAGE_ICONE;
	}

	public static String getNomImageCurseur() {
		return NOM_IMAGE_CURSEUR;
	}
	
	//COPIE DE CHAINE DANS LE PRESSE PAPIER
	public static void copierDansPressePapier(String chaine) {
		StringSelection ss = new StringSelection(chaine);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	}

	//PAGE D'ACCUEIL
	public void afficherPageAccueil() {
		final String TEXTE_BOUTON_CONNEXION = "Se connecter";
		final Dimension DIMENSION_BOUTON_CONNEXION = new Dimension(200, 30);
		final String TEXTE_BOUTON_INSCRIPTION = "S'inscrire";
		final Dimension DIMENSION_BOUTON_INSCRIPTION = new Dimension(200, 30); //largeur, hauteur
		final String TEXTE_BOUTON_ACTIVER_COMPTE = "Activer un compte";
		final Dimension DIMENSION_BOUTON_ACTIVER_COMPTE = new Dimension(200, 30); //largeur, hauteur
		final String LABEL_IDENTIFIANT = "Identifiant       ";
		final Dimension DIMENSION_PASSWORD_FIELD = new Dimension(230, 30);
		final Dimension DIMENSION_PLOGO = new Dimension(Fenetre.LARGEUR, 250);
		final Dimension DIMENSION_PLOGO_UP = new Dimension(Fenetre.LARGEUR, 100);
		final Dimension DIMENSION_PSOUTH_UP = new Dimension(Fenetre.LARGEUR,200);
		final String NOM_IMAGE_BACKGROUND = "background.jpg";
		final Color COULEUR_LABEL_INSCRIPTION = new Color(255, 255, 255);
		final String TEXTE_BOUTON_IDENTIFIANTS_SAUVES = "Identifiant(s) sauvegarde(s)";

		JButton boutonConnexion = new JButton(TEXTE_BOUTON_CONNEXION);
		boutonConnexion.setPreferredSize(DIMENSION_BOUTON_CONNEXION);
		JButton boutonInscription = new JButton(TEXTE_BOUTON_INSCRIPTION);
		boutonInscription.setPreferredSize(DIMENSION_BOUTON_INSCRIPTION); //Taille bouton
		JButton boutonActiverCompte = new JButton(TEXTE_BOUTON_ACTIVER_COMPTE);
		boutonActiverCompte.setPreferredSize(DIMENSION_BOUTON_ACTIVER_COMPTE);
		JLabel labelIdentifiant = new JLabel(LABEL_IDENTIFIANT);
		JButton boutonIdentifiantsSauves = new JButton(TEXTE_BOUTON_IDENTIFIANTS_SAUVES);
		labelIdentifiant.setForeground(COULEUR_LABEL_INSCRIPTION);
		ImageIcon logo = new ImageIcon(NOM_DOSSIER_RESSOURCE + NOM_IMAGE_LOGO);
		JLabel labelImage = new JLabel();
		labelImage.setIcon(logo);
		JPasswordField idPasswordField = new JPasswordField();
		idPasswordField.setPreferredSize(DIMENSION_PASSWORD_FIELD);

		boutonConnexion.addActionListener(new EcouteurAccueil(this, 1, idPasswordField));
		boutonInscription.addActionListener(new EcouteurAccueil(this, 2, idPasswordField));
		boutonActiverCompte.addActionListener(new EcouteurAccueil(this, 3, idPasswordField));
		boutonIdentifiantsSauves.addActionListener(new EcouteurAccueil(this, 4, idPasswordField));

		contenu.removeAll(); //On efface la content pane

		JPanel panneauBackground = null;
		try {
			panneauBackground = new PanneauImage(NOM_DOSSIER_RESSOURCE + NOM_IMAGE_BACKGROUND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panneauBackground.setLayout(new BorderLayout());
		//panneauBackground.setBackground(new Color(142, 210, 255));
		this.contenu.add(panneauBackground, BorderLayout.CENTER);
		
		JPanel pLogo = new JPanel();
		panneauBackground.add(pLogo, BorderLayout.NORTH);
		pLogo.setOpaque(false); //Panneau transparent
		pLogo.setLayout(new BorderLayout());
		pLogo.setPreferredSize(DIMENSION_PLOGO);
		JPanel pLogoUp = new JPanel();
		pLogoUp.setOpaque(false);
		pLogoUp.setPreferredSize(DIMENSION_PLOGO_UP); //Pour faire descendre l'image
		pLogo.add(pLogoUp, BorderLayout.NORTH);
		JPanel pImage = new JPanel();
		pImage.setOpaque(false);
		pImage.setLayout(new FlowLayout(FlowLayout.CENTER)); //ajout au centre (pour éviter que 
			//le bouton prenne toute la place
		pImage.add(labelImage);
		pLogo.add(pImage, BorderLayout.CENTER);
		
		JPanel pSouth = new JPanel();
		pSouth.setOpaque(false);
		pSouth.setLayout(new BorderLayout());
		JPanel pSouthUp = new JPanel();
		pSouthUp.setOpaque(false);
		pSouthUp.setPreferredSize(DIMENSION_PSOUTH_UP);
		pSouth.add(pSouthUp, BorderLayout.NORTH);
		JPanel p1 = new JPanel();
		pSouth.add(p1, BorderLayout.SOUTH);
		p1.setOpaque(false);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER)); 
		p1.add(boutonInscription);
		p1.add(boutonActiverCompte);
		panneauBackground.add(pSouth, BorderLayout.SOUTH);

		JPanel p2 = new JPanel();
		p2.setOpaque(false);
		p2.setLayout(new GridLayout(2, 1));
		panneauBackground.add(p2, BorderLayout.CENTER);

		JPanel p3 = new JPanel();
		p3.setOpaque(false);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(labelIdentifiant);
		p3.add(idPasswordField);
		p3.add(boutonIdentifiantsSauves);
		p2.add(p3);

		JPanel p4 = new JPanel();
		p4.setOpaque(false);
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		p4.add(boutonConnexion);
		p2.add(p4);
		
		updateAffichage();
	}
}


















