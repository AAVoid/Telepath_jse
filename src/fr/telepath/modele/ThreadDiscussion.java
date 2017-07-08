package fr.telepath.modele;

import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.telepath.modele.service.UtiliserWS;
import fr.telepath.vue.Fenetre;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



//Classe utilisée pour automatiser la récupération des messages sur le serveur
public class ThreadDiscussion extends Thread {
	private static final String MESSAGE_ECHEC_RECEPTION = "Les messages n'ont pas pu être récupérés !\n"
			+ "L'application rencontre peut-être des difficultés.";
	
	private Fenetre fenetre; //Panneau message dans la classe Fenetre, dans la discussion
	private JPanel panneauMessages; //Panneau dans la fenêtre, dans la discussion
	private Ami ami; //L'ami dont il faut récupérer les messages
	private boolean continuer;

	public ThreadDiscussion(Fenetre fenetre, JPanel panneauMessages, Ami ami) {
		super();
		this.fenetre = fenetre;
		this.panneauMessages = panneauMessages;
		this.ami = ami;
		this.continuer = true;
	}

	public void arreter() {
		this.continuer = false;
	}

	public void run() {
		while(this.continuer) {
			//On récupère les messages et on les insère dans le panneau Messages
			String reponse = "";
			try {
				reponse = UtiliserWS.serviceLireMessages(ami.getIdentifiantRelation());
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Parsage JSON
			int rep = UtiliserWS.getReponse(reponse);
			if(rep == 1) {
				//AFFICHAGE DES MESSAGES DANS L'INTERFACE
				JSONObject jo = null;
				JSONArray tab = null;
				try {
					jo = new JSONObject(reponse);
					tab = jo.getJSONArray("messages");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String mes = "";
				String identiteExpediteur = "";
				for(int i = 0 ; i < tab.length() ; i++) {
					try {
						identiteExpediteur = tab.getJSONObject(i).getString("identite");
						mes = tab.getJSONObject(i).getString("message");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					/*ON RAJOUTE LE MESSAGE A LA DISCUSSION EN FONCTION DE L'EXPEDITEUR QUI PEUT ETRE
					LE CORRESPONDANT OU NOUS-MEME*/
					if(identiteExpediteur.equals(GestionDiscussion.getIdentiteUtilisateur()))
						fenetre.ajouterMessagesDiscussionMoi(mes, this.panneauMessages);
					else
						fenetre.ajouterMessagesDiscussionCorrespondant(mes, this.panneauMessages);
					fenetre.updateAffichage(); //Mise à jour de la fenêtre et donc du panneau discussion
				}
			}
			else if(rep == 0) //On affiche un message système
				fenetre.ajouterMessagesDiscussionSysteme(MESSAGE_ECHEC_RECEPTION, this.panneauMessages);
			//On attend pour la prochaine récupération
			try {
				sleep(GestionDiscussion.getFrequenceRecuperationMessageLong());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}







