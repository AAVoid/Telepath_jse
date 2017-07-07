package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import fr.telepath.controleur.EcouteurDialogAjoutAmi;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogAjoutAmi extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(300, 110);
	private static final String LABEL_EMAIL = "<html><i>E-mail</i>&nbsp&nbsp&nbsp</html>";
	private static final boolean REDIMENSIONNABLE = false;
	private static final String LABEL_BOUTON_VALIDER = "Valider";
	private static final String LABEL_BOUTON_ANNULER = "Annuler";
	private static final Dimension DIMENSION_CHAMP_EMAIL = new Dimension(225, 30);
	private static final String NOM_IMAGE_BACKGROUND = "background2.jpg";
	private static final Color COULEUR_LABEL_EMAIL = new Color(255, 255, 255);
	private static final String NOM_ICONE_VALIDER = "valider.png";
	private static final String NOM_ICONE_ANNULER = "annuler.png";
	
	private Container contenu;
	private Fenetre fenetre;
	
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
	
	public DialogAjoutAmi(Fenetre fenetreParent, String nom, boolean modal){
		super(fenetreParent, nom, modal);
		this.fenetre = fenetreParent;
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
		JTextField champEmail = new JTextField();
		champEmail.setPreferredSize(DIMENSION_CHAMP_EMAIL);
		
		boutonValider.addActionListener(new EcouteurDialogAjoutAmi(this, 1, champEmail));
		boutonAnnuler.addActionListener(new EcouteurDialogAjoutAmi(this, 2, null));
		
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.add(labelEMail);
		p1.add(champEmail);
		
		
		JPanel p2 = new JPanel();
		p2.setOpaque(false);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.add(boutonValider);
		p2.add(boutonAnnuler);
		
		panneauBackground.add(p1, BorderLayout.CENTER);
		panneauBackground.add(p2, BorderLayout.SOUTH);
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	@SuppressWarnings("unused")
	private void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
}







