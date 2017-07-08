package fr.telepath.main;

import fr.telepath.vue.Fenetre;

//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class Main {
	public static void main(String[] args) {
		//Changer le thème
		/*try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}*/
		Fenetre fenetre = new Fenetre();
		fenetre.afficherSplashScreen();
		fenetre.afficher();
	}
}


















