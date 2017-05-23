package fr.telepath.vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class PanneauImage extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public PanneauImage(String cheminImage) throws IOException {
		image = ImageIO.read(new File(cheminImage));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage (image, 0, 0, null);
		this.repaint();
	}
}










