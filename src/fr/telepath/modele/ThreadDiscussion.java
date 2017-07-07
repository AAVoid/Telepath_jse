package fr.telepath.modele;

import fr.telepath.vue.Fenetre;

//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Universit� des Antilles - Guadeloupe
//2016 - 2017



//Classe utilis�e pour automatiser la r�cup�ration des messages sur le serveur
public class ThreadDiscussion extends Thread {
	private Fenetre fenetre; //Panneau message dans la classe Fenetre, dans la discussion
	private Ami ami; //L'ami dont il faut r�cup�rer les messages

	public ThreadDiscussion(Fenetre fenetre, Ami ami) {
		super();
		this.fenetre = fenetre;
		this.ami = ami;
	}

	public void run() {
		//On r�cup�re les messages et on les ins�re dans le panneau Messages
		//On attend pour la prochaine r�cup�ration
		while(true) {
			try {
				sleep(GestionDiscussion.getFrequenceRecuperationMessageLong());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}







