package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import fr.telepath.controleur.Controleur;
import fr.telepath.controleur.EcouteurAccueil;
import fr.telepath.controleur.EcouteurDiscussion;
import fr.telepath.controleur.EcouteurJMenu;
import fr.telepath.controleur.EcouteurListeAmis;
import fr.telepath.controleur.EcouteurListeAmisDiscussion;
import fr.telepath.modele.Ami;
import fr.telepath.modele.GestionDiscussion;
import fr.telepath.modele.ThreadDiscussion;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int LARGEUR = 600;
	private static final int HAUTEUR = 600;
	private static final boolean REDIMENSIONNABLE = false;
	private static final String NOM_APPLICATION = "Télépath";
	private static final String NOM_AUTEUR = "Aymerik ABOSO";
	private static final String VERSION = "1.0";
	private static final String NOM_FENETRE = NOM_APPLICATION + " V" + VERSION;
	private static final String NOM_DOSSIER_RESSOURCE = "Ressources/";
	private static final String NOM_DOSSIER_BACKGROUND = NOM_DOSSIER_RESSOURCE + "Background/";
	private static final String NOM_DOSSIER_ICONE = NOM_DOSSIER_RESSOURCE + "Icone/";
	private static final String NOM_DOSSIER_AUTRE = NOM_DOSSIER_RESSOURCE + "Autre/";
	private static final String NOM_IMAGE_LOGO = "logo.png";
	private static final String NOM_IMAGE_ICONE = "icone_fenetre.png";
	private static final String NOM_IMAGE_CURSEUR = "curseur.png";
	private static final String NOM_MENU_A_PROPOS_DE = "A propos de...";
	private static final String NOM_MENU_A_PROPOS_DE_ORIGINE_DE_TELEPATH = "L'origine de " + NOM_APPLICATION;
	private static final String NOM_MENU_A_PROPOS_DE_ESPRIT_DE_TELEPATH = "L'esprit de " + NOM_APPLICATION;

	private Container contenu; //Pour la content pane
	private JMenuBar menuBar; //MENU
	private JMenu menuEntryAProposDe;
	private JMenuItem menuItemOrigine;
	private JMenuItem menuItemEsprit;

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
			setIconImage(ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_IMAGE_ICONE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//CURSEUR DANS LA FENETRE
		getRootPane().setCursor(java.awt.Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(NOM_DOSSIER_ICONE + NOM_IMAGE_CURSEUR).getImage(), new Point(1,1),"customCursor"));
		//CREATION DU MENU
		this.menuBar = new JMenuBar();
		this.menuEntryAProposDe = new JMenu(NOM_MENU_A_PROPOS_DE);
		this.menuItemOrigine = new JMenuItem(NOM_MENU_A_PROPOS_DE_ORIGINE_DE_TELEPATH);
		this.menuItemOrigine.addActionListener(new EcouteurJMenu(this, 1));
		this.menuItemEsprit = new JMenuItem(NOM_MENU_A_PROPOS_DE_ESPRIT_DE_TELEPATH);
		this.menuItemEsprit.addActionListener(new EcouteurJMenu(this, 2));

		this.menuEntryAProposDe.add(this.menuItemOrigine);
		this.menuEntryAProposDe.add(this.menuItemEsprit);
		this.menuBar.add(this.menuEntryAProposDe);
		setJMenuBar(this.menuBar);

		afficherPageAccueil();
	}

	public void afficherSplashScreen() {
		final int AJOUT_LARGEUR = 30;
		final int AJOUT_HAUTEUR = 20;
		final long DUREE_SECONDE = 5;
		final long DUREE_MILLIS = DUREE_SECONDE * 1000;

		SplashScreen splash = new SplashScreen(AJOUT_LARGEUR, AJOUT_HAUTEUR, 
				NOM_DOSSIER_AUTRE + NOM_IMAGE_LOGO, DUREE_MILLIS);
		splash.afficher();
	}

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

	public static String getNomAuteur() {
		return NOM_AUTEUR;
	}

	public static String getNomApplication() {
		return NOM_APPLICATION;
	}

	public static String getNomMenuAProposDe() {
		return NOM_MENU_A_PROPOS_DE;
	}

	public static String getNomMenuAProposDeEspritDeTelepath() {
		return NOM_MENU_A_PROPOS_DE_ESPRIT_DE_TELEPATH;
	}

	public static String getNomMenuAProposDeOrigineDeTelepath() {
		return NOM_MENU_A_PROPOS_DE_ORIGINE_DE_TELEPATH;
	}

	public static String getNomDossierBackground() {
		return NOM_DOSSIER_BACKGROUND;
	}

	public static String getNomDossierIcone() {
		return NOM_DOSSIER_ICONE;
	}

	public static String getNomDossierAutre() {
		return NOM_DOSSIER_AUTRE;
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
		final String LABEL_IDENTIFIANT = "<html><i><font size=\"5\">Identifiant</i></font>&nbsp&nbsp&nbsp</html>";
		final Dimension DIMENSION_PASSWORD_FIELD = new Dimension(230, 30);
		final Dimension DIMENSION_PLOGO = new Dimension(Fenetre.LARGEUR, 250);
		final Dimension DIMENSION_PLOGO_UP = new Dimension(Fenetre.LARGEUR, 60);
		final Dimension DIMENSION_PSOUTH_UP = new Dimension(Fenetre.LARGEUR, 150);
		final String NOM_IMAGE_BACKGROUND = "background.jpg";
		final Color COULEUR_LABEL_INSCRIPTION = new Color(255, 255, 255);
		final String TEXTE_BOUTON_IDENTIFIANTS_SAUVES = "Gestionnaire d'identifiants";
		final String NOM_ICONE_INSCRIPTION = "groupe.png";
		final String NOM_ICONE_ACTIVATION = "ok.png";
		final String NOM_ICONE_ID_SAUVES = "utilisateur.png";
		final String NOM_ICONE_SE_CONNECTER = "reseau.png";

		JButton boutonConnexion = new JButton(TEXTE_BOUTON_CONNEXION);
		boutonConnexion.setPreferredSize(DIMENSION_BOUTON_CONNEXION);
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_SE_CONNECTER));
			boutonConnexion.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JButton boutonInscription = new JButton(TEXTE_BOUTON_INSCRIPTION);
		boutonInscription.setPreferredSize(DIMENSION_BOUTON_INSCRIPTION); //Taille bouton
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_INSCRIPTION));
			boutonInscription.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JButton boutonActiverCompte = new JButton(TEXTE_BOUTON_ACTIVER_COMPTE);
		boutonActiverCompte.setPreferredSize(DIMENSION_BOUTON_ACTIVER_COMPTE);
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_ACTIVATION));
			boutonActiverCompte.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLabel labelIdentifiant = new JLabel(LABEL_IDENTIFIANT);
		JButton boutonIdentifiantsSauves = new JButton(TEXTE_BOUTON_IDENTIFIANTS_SAUVES);
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_ID_SAUVES));
			boutonIdentifiantsSauves.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		labelIdentifiant.setForeground(COULEUR_LABEL_INSCRIPTION);
		JLabel labelImage = new JLabel();
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_AUTRE + NOM_IMAGE_LOGO));
			labelImage.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPasswordField idPasswordField = new JPasswordField();
		idPasswordField.setPreferredSize(DIMENSION_PASSWORD_FIELD);

		boutonConnexion.addActionListener(new EcouteurAccueil(this, 1, idPasswordField));
		boutonInscription.addActionListener(new EcouteurAccueil(this, 2, idPasswordField));
		boutonActiverCompte.addActionListener(new EcouteurAccueil(this, 3, idPasswordField));
		boutonIdentifiantsSauves.addActionListener(new EcouteurAccueil(this, 4, idPasswordField));

		contenu.removeAll(); //On efface la content pane
		contenu.setLayout(new BorderLayout());

		JPanel panneauBackground = null;
		try {
			panneauBackground = new PanneauImage(NOM_DOSSIER_BACKGROUND + NOM_IMAGE_BACKGROUND);
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
		p2.setLayout(new GridLayout(2, 1)); //2 lignes une colonne
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

	//PAGE LISTE AMIS
	public void afficherListeAmis() {
		final Color COULEUR_FOND = new Color(199, 249, 226);
		final Color COULEUR_FOND_PANNEAU_HAUT_BAS = new Color(160, 196, 255);
		final Dimension DIMENSION_PLOGO = new Dimension(Fenetre.LARGEUR, 170);
		final Dimension DIMENSION_PLOGO_UP = new Dimension(Fenetre.LARGEUR, 10);
		final String TEXTE_BOUTON_DECONNEXION = "Se déconnecter";
		final String TEXTE_BOUTON_AJOUTER_AMI = "Ajouter un ami";
		final Dimension DIMENSION_BOUTON_DECONNEXION = new Dimension(200, 30); //largeur, hauteur
		final Dimension DIMENSION_BOUTON_AJOUTER_AMI = new Dimension(200, 30);
		final String NOM_ICONE_BOUTON_DECONNEXION = "deconnexion.png";
		final String NOM_ICONE_BOUTON_AJOUTER_AMI = "ajouterAmi.png";
		final String TEXTE_BONJOUR_UTILISATEUR = "<html><font size=\"4\">Bonjour <font size=\"6\"><i>" 
				+ GestionDiscussion.getIdentiteUtilisateur() + "</i></font></font> !<br>"
				+ "<font size=\"6\">Votre liste d'amis</font></html>";
		final Dimension DIMENSION_BOUTON_AMI = new Dimension(Fenetre.LARGEUR - 40, 40);
		final int ESPACE_ENTRE_AMIS = 10;
		final String NOM_ICONE_DISCUTER_AMI = "discuter.png";

		contenu.removeAll();
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(COULEUR_FOND);

		//HAUT ET BAS
		JLabel labelImage = new JLabel();
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_AUTRE + NOM_IMAGE_LOGO));
			labelImage.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel pLogo = new JPanel();
		//pLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contenu.add(pLogo, BorderLayout.NORTH);
		pLogo.setOpaque(true);
		pLogo.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		pLogo.setLayout(new BorderLayout());
		pLogo.setPreferredSize(DIMENSION_PLOGO);

		JPanel pLogoUp = new JPanel();
		pLogoUp.setOpaque(true);
		pLogoUp.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		pLogoUp.setPreferredSize(DIMENSION_PLOGO_UP); //Pour faire descendre l'image
		pLogo.add(pLogoUp, BorderLayout.NORTH);
		JPanel pImage = new JPanel();
		pImage.setOpaque(true);
		pImage.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		pImage.setLayout(new FlowLayout(FlowLayout.CENTER)); //ajout au centre (pour éviter que 
		//le bouton prenne toute la place
		pImage.add(labelImage);
		pLogo.add(pImage, BorderLayout.CENTER);
		JPanel pLogoBonjourUtilisateur = new JPanel(); /*Panneau sur lequel est affichée l'identitée de l'utilisateur
		l'identité de l'utilisateur*/
		pLogoBonjourUtilisateur.setLayout(new FlowLayout(FlowLayout.LEFT));
		pLogoBonjourUtilisateur.setOpaque(true);
		pLogoBonjourUtilisateur.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		pLogo.add(pLogoBonjourUtilisateur, BorderLayout.SOUTH);
		JLabel labelBonjourUtilisateur = new JLabel(TEXTE_BONJOUR_UTILISATEUR);
		pLogoBonjourUtilisateur.add(labelBonjourUtilisateur);

		JPanel panneauSouth = new JPanel();
		panneauSouth.setOpaque(true);
		//panneauSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panneauSouth.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		panneauSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton boutonDeconnexion = new JButton(TEXTE_BOUTON_DECONNEXION);
		boutonDeconnexion.setPreferredSize(DIMENSION_BOUTON_DECONNEXION);
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_BOUTON_DECONNEXION));
			boutonDeconnexion.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		boutonDeconnexion.addActionListener(new EcouteurListeAmis(this, 1));
		JButton boutonAjouterAmi = new JButton(TEXTE_BOUTON_AJOUTER_AMI);
		boutonAjouterAmi.setPreferredSize(DIMENSION_BOUTON_AJOUTER_AMI);
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_BOUTON_AJOUTER_AMI));
			boutonAjouterAmi.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		boutonAjouterAmi.addActionListener(new EcouteurListeAmis(this, 2));
		panneauSouth.add(boutonDeconnexion);
		panneauSouth.add(boutonAjouterAmi);
		contenu.add(panneauSouth, BorderLayout.SOUTH);

		//LISTE D'AMIS
		Border bordureInvisible = BorderFactory.createEmptyBorder(); //Pour effacer la bordure du scrollPane
		JPanel panneauListeAmis = new JPanel(); //scrolling fait sur ce panneau
		panneauListeAmis.setOpaque(true);
		panneauListeAmis.setBackground(COULEUR_FOND);
		LayoutManager boxLayout = new BoxLayout(panneauListeAmis, BoxLayout.Y_AXIS); //Vertical
		panneauListeAmis.setLayout(boxLayout);

		JScrollPane scrollPane = new JScrollPane(panneauListeAmis); //Pour le scrolling
		scrollPane.setBorder(bordureInvisible);
		contenu.add(scrollPane, BorderLayout.CENTER);

		ArrayList<Ami> listeAmis = Controleur.obtenirListeAmis(this, GestionDiscussion.getIdentifiantUtilisateur());
		panneauListeAmis.add(Box.createRigidArea(new Dimension(1, ESPACE_ENTRE_AMIS)));
		for(Ami a : listeAmis) {
			JButton boutonAmi = new JButton(a.getIdentite());
			try {
				Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_DISCUTER_AMI));
				boutonAmi.setIcon(new ImageIcon(img));
			} catch (Exception e) {
				e.printStackTrace();
			}
			boutonAmi.setMinimumSize(DIMENSION_BOUTON_AMI);
			boutonAmi.setMaximumSize(DIMENSION_BOUTON_AMI);
			boutonAmi.setAlignmentX(Component.CENTER_ALIGNMENT);
			//Ecouteur
			boutonAmi.addActionListener(new EcouteurListeAmisDiscussion(a, this));
			panneauListeAmis.add(boutonAmi);
			panneauListeAmis.add(Box.createRigidArea(new Dimension(1, ESPACE_ENTRE_AMIS)));
		}

		this.updateAffichage();
	}

	//PAGE DE DISCUSSION
	public void afficherDiscussion(Ami ami) {
		final Color COULEUR_FOND = new Color(199, 249, 226);
		final Color COULEUR_FOND_PANNEAU_HAUT_BAS = new Color(160, 196, 255);
		final Dimension DIMENSION_PLOGO = new Dimension(Fenetre.LARGEUR, 140);
		final Dimension DIMENSION_PLOGO_UP = new Dimension(Fenetre.LARGEUR, 10);
		final String TEXTE_BOUTON_ENVOI_MESSAGE = "Envoyer";
		final String TEXTE_BOUTON_QUITTER_DISCUSSION = "Quitter la discussion";
		final Dimension DIMENSION_BOUTON_ENVOI_MESSAGE = new Dimension(150, 30); //largeur, hauteur
		final Dimension DIMENSION_BOUTON_QUITTER_DISCUSSION = new Dimension(200, 30);
		final String NOM_ICONE_BOUTON_ENVOI_MESSAGE = "envoyerMessage.png";
		final String NOM_ICONE_BOUTON_QUITTER_DISCUSSION = "quitterDiscussion.png";
		final String TEXTE_IDENTITE_CORRESPONDANT = "<html><font size=\"6\"><i>" 
				+ ami.getIdentite() + "</i></font></html>";
		final Color COULEUR_BORDURE_ZONE_TEXTE = Color.BLACK;
		final int NOMBRE_COLONNES_ZONE_TEXTE = 37;
		final int NOMBRE_LIGNES_ZONE_TEXTE = 6;
		final int ESPACEMENT_BOUTON_QUITTER_DISCUSSION = 290;
		final int ESPACE_PREMIER_MESSAGES = 10;

		contenu.removeAll();
		contenu.setLayout(new BorderLayout());
		contenu.setBackground(COULEUR_FOND);

		//HAUT ET BAS
		JLabel labelImage = new JLabel();
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_AUTRE + NOM_IMAGE_LOGO));
			labelImage.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel pLogo = new JPanel();
		//pLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contenu.add(pLogo, BorderLayout.NORTH);
		pLogo.setOpaque(true);
		pLogo.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		pLogo.setLayout(new BorderLayout());
		pLogo.setPreferredSize(DIMENSION_PLOGO);

		JPanel pLogoUp = new JPanel();
		pLogoUp.setOpaque(true);
		pLogoUp.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		pLogoUp.setPreferredSize(DIMENSION_PLOGO_UP); //Pour faire descendre l'image
		pLogo.add(pLogoUp, BorderLayout.NORTH);
		JPanel pImage = new JPanel();
		pImage.setOpaque(true);
		pImage.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		pImage.setLayout(new FlowLayout(FlowLayout.CENTER)); //ajout au centre (pour éviter que 
		//le bouton prenne toute la place
		pImage.add(labelImage);
		pLogo.add(pImage, BorderLayout.CENTER);
		
		JPanel pIdentiteAmi = new JPanel(); /*Panneau sur lequel est affichée l'identité de l'utilisateur*/
		pIdentiteAmi.setLayout(new FlowLayout(FlowLayout.LEFT));
		pIdentiteAmi.setOpaque(true);
		pIdentiteAmi.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		pLogo.add(pIdentiteAmi, BorderLayout.SOUTH);
		JLabel labelIdentiteCorrespondant = new JLabel(TEXTE_IDENTITE_CORRESPONDANT);
		pIdentiteAmi.add(labelIdentiteCorrespondant);

		JPanel panneauSouth = new JPanel();
		panneauSouth.setOpaque(true);
		//panneauSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panneauSouth.setBackground(COULEUR_FOND_PANNEAU_HAUT_BAS);
		panneauSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton boutonEnvoiMessage = new JButton(TEXTE_BOUTON_ENVOI_MESSAGE);
		boutonEnvoiMessage.setPreferredSize(DIMENSION_BOUTON_ENVOI_MESSAGE);
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_BOUTON_ENVOI_MESSAGE));
			boutonEnvoiMessage.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton boutonQuitterDiscussion = new JButton(TEXTE_BOUTON_QUITTER_DISCUSSION);
		boutonQuitterDiscussion.setPreferredSize(DIMENSION_BOUTON_QUITTER_DISCUSSION);
		pIdentiteAmi.add(Box.createRigidArea(new Dimension(ESPACEMENT_BOUTON_QUITTER_DISCUSSION, 1)));
		try {
			Image img = ImageIO.read(new File(NOM_DOSSIER_ICONE + NOM_ICONE_BOUTON_QUITTER_DISCUSSION));
			boutonQuitterDiscussion.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JTextArea zoneTexte = new JTextArea("", NOMBRE_LIGNES_ZONE_TEXTE, NOMBRE_COLONNES_ZONE_TEXTE); //ligne, colonnes
		zoneTexte.setBorder(BorderFactory.createLineBorder(COULEUR_BORDURE_ZONE_TEXTE));
		panneauSouth.add(zoneTexte);
		panneauSouth.add(boutonEnvoiMessage);
		pIdentiteAmi.add(boutonQuitterDiscussion);
		contenu.add(panneauSouth, BorderLayout.SOUTH);

		//Affichage des messages
		Border bordureInvisible = BorderFactory.createEmptyBorder(); //Pour effacer la bordure du scrollPane
		JPanel panneauMessages = new JPanel(); //scrolling fait sur ce panneau
		panneauMessages.setOpaque(true);
		panneauMessages.setBackground(COULEUR_FOND);
		LayoutManager boxLayout = new BoxLayout(panneauMessages, BoxLayout.Y_AXIS); //Vertical
		panneauMessages.setLayout(boxLayout);
		panneauMessages.add(Box.createRigidArea(new Dimension(1, ESPACE_PREMIER_MESSAGES)));

		JScrollPane scrollPane = new JScrollPane(panneauMessages); //Pour le scrolling
		scrollPane.setBorder(bordureInvisible);
		contenu.add(scrollPane, BorderLayout.CENTER);

		//Initialisation du thread de récupération de messages
		ThreadDiscussion threadDiscu = new ThreadDiscussion(this, panneauMessages, ami);
		threadDiscu.start();
		
		boutonEnvoiMessage.addActionListener(new EcouteurDiscussion(1, this, zoneTexte, threadDiscu));
		boutonQuitterDiscussion.addActionListener(new EcouteurDiscussion(2, this, zoneTexte, threadDiscu));
		
		ajouterMessagesDiscussionMoi("test", panneauMessages);

		this.updateAffichage();
	}
	
	public void ajouterMessagesDiscussionMoi(String message, JPanel panneauMessages) {
		final int ESPACE_ENTRE_MESSAGES = 10;
		final Color COULEUR_BOUTON = new Color(86, 151, 255);
		
		JButton boutonMessage = new JButton(message);
		boutonMessage.setBackground(COULEUR_BOUTON);
		boutonMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		panneauMessages.add(boutonMessage);
		panneauMessages.add(Box.createRigidArea(new Dimension(1, ESPACE_ENTRE_MESSAGES)));
	}
	
	public void ajouterMessagesDiscussionCorrespondant(String message, JPanel panneauMessages) {
		final int ESPACE_ENTRE_MESSAGES = 10;
		final Color COULEUR_BOUTON = new Color(40, 252, 100);
		
		JButton boutonMessage = new JButton(message);
		boutonMessage.setBackground(COULEUR_BOUTON);
		boutonMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		panneauMessages.add(boutonMessage);
		panneauMessages.add(Box.createRigidArea(new Dimension(1, ESPACE_ENTRE_MESSAGES)));
	}
}


















