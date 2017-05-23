package fr.telepath.vue;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class SplashScreen extends JWindow {
	private static final long serialVersionUID = 1L;
	
	private int ajout_largeur;
	private int ajout_hauteur;
	private String cheminImage;
	private Image image;
	private long dureeMillis;
	private Container contenu;
	
	public SplashScreen(int ajout_largeur, int ajout_hauteur, String cheminImage, long dureeMillis) {
		super();
		this.ajout_largeur = ajout_largeur;
		this.ajout_hauteur = ajout_hauteur;
		this.cheminImage = cheminImage;
		this.dureeMillis = dureeMillis;
		this.image = null;
		this.contenu = getContentPane();
		
		try {
			image = ImageIO.read(new File(this.cheminImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(this.image.getWidth(null) + this.ajout_largeur, 
				this.image.getHeight(null) + this.ajout_hauteur);
		setLocationRelativeTo(null);      
	    JPanel pan = new JPanel();
	    JLabel img = new JLabel(new ImageIcon(image));     
	    pan.setBorder(BorderFactory.createLineBorder(Color.blue));
	    pan.add(img);
	    contenu.add(pan);
	}
	
	public void afficher() {
		setVisible(true);
		//Attente
		try {
			Thread.sleep(this.dureeMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}








