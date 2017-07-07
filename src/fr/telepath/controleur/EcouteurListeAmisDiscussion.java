package fr.telepath.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.telepath.modele.Ami;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class EcouteurListeAmisDiscussion implements ActionListener {
	private Ami ami;
	private Fenetre fenetre; //Pour pouvoir afficher la discussion
	
	public EcouteurListeAmisDiscussion(Ami ami, Fenetre fenetre) {
		super();
		this.ami = ami;
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//On lance la discussion avec cet ami
		fenetre.afficherDiscussion(ami);
	}
}







