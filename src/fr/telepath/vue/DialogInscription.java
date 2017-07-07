package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.telepath.controleur.EcouteurDialogInscription;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogInscription extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(300, 150);
	private static final String LABEL_IDENTIFIANT = "<html><i>Identité</i>&nbsp&nbsp&nbsp</html>";
	private static final boolean REDIMENSIONNABLE = false;
	private static final String LABEL_EMAIL = "<html><i>E-Mail</i>&nbsp&nbsp&nbsp</html>";
	private static final String LABEL_BOUTON_VALIDER = "Valider";
	private static final String LABEL_BOUTON_ANNULER = "Annuler";
	private static final Dimension DIMENSION_CHAMP_ID = new Dimension(190, 30);
	private static final Dimension DIMENSION_CHAMP_EMAIL = new Dimension(190, 30);
	private static final String NOM_IMAGE_BACKGROUND = "background2.jpg";
	private static final Color COULEUR_LABEL_IDENTIFIANT = new Color(255, 255, 255);
	private static final Color COULEUR_LABEL_EMAIL = new Color(255, 255, 255);
	private static final String NOM_ICONE_VALIDER = "valider.png";
	private static final String NOM_ICONE_ANNULER = "annuler.png";
	
	private Container contenu;
	
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

	public DialogInscription(Fenetre fenetreParent, String nom, boolean modal){
		super(fenetreParent, nom, modal);
		setSize(DIMENSION_DIALOG.width, DIMENSION_DIALOG.height);
		setLocationRelativeTo(fenetreParent);
		setResizable(REDIMENSIONNABLE);
		
		this.contenu = this.getContentPane();
		contenu.setLayout(new BorderLayout());		
		JPanel panneauBackground = null;
		try {
			panneauBackground = new PanneauImage(Fenetre.getNomDossierBackground() + NOM_IMAGE_BACKGROUND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panneauBackground.setLayout(new BorderLayout());
		this.contenu.add(panneauBackground, BorderLayout.CENTER);
		
		JLabel labelIdentifiant = new JLabel(LABEL_IDENTIFIANT);
		labelIdentifiant.setForeground(COULEUR_LABEL_IDENTIFIANT);
		JLabel labelEMail = new JLabel(LABEL_EMAIL);
		labelEMail.setForeground(COULEUR_LABEL_EMAIL);
		JButton boutonValider = new JButton(LABEL_BOUTON_VALIDER);
		try {
			Image img = ImageIO.read(new File(Fenetre.getNomDossierIcone() + NOM_ICONE_VALIDER));
			boutonValider.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JButton boutonAnnuler = new JButton(LABEL_BOUTON_ANNULER);
		try {
			Image img = ImageIO.read(new File(Fenetre.getNomDossierIcone() + NOM_ICONE_ANNULER));
			boutonAnnuler.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JTextField champIdentite = new JTextField();
		champIdentite.setPreferredSize(DIMENSION_CHAMP_ID);
		JTextField champEMail = new JTextField();
		champEMail.setPreferredSize(DIMENSION_CHAMP_EMAIL);
		
		boutonValider.addActionListener(new EcouteurDialogInscription(this, 1, champIdentite, champEMail));
		boutonAnnuler.addActionListener(new EcouteurDialogInscription(this, 2, null, null));
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new GridLayout(2, 1));
		
		JPanel p2 = new JPanel();
		p2.setOpaque(false);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.add(labelIdentifiant);
		p2.add(champIdentite);
		p1.add(p2);
		
		JPanel p3 = new JPanel();
		p3.setOpaque(false);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(labelEMail);
		p3.add(champEMail);
		p1.add(p3);
		
		JPanel p4 = new JPanel();
		p4.setOpaque(false);
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));
		p4.add(boutonValider);
		p4.add(boutonAnnuler);
		
		panneauBackground.add(p1, BorderLayout.CENTER);
		panneauBackground.add(p4, BorderLayout.SOUTH);
	}
}












