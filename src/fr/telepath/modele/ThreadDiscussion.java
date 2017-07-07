package fr.telepath.modele;

import fr.telepath.vue.Fenetre;

//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//Classe utilisée pour automatiser la récupération des messages sur le serveur
public class ThreadDiscussion extends Thread {
	private Fenetre fenetre; //Panneau message dans la classe Fenetre, dans la discussion
	private Ami ami; //L'ami dont il faut récupérer les messages

	public ThreadDiscussion(Fenetre fenetre, Ami ami) {
		super();
		this.fenetre = fenetre;
		this.ami = ami;
	}

	public void run() {
		//On récupère les messages et on les insère dans le panneau Messages
		//On attend pour la prochaine récupération
		while(true) {
			try {
				sleep(GestionDiscussion.getFrequenceRecuperationMessageLong());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}







