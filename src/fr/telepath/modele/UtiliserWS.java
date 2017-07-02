package fr.telepath.modele;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class UtiliserWS {
	private static final String URL_SERVEUR = "http://ns4004962.ip-198-27-65.net/applications/WAL/";
	
	
	public static String utiliserService(String u) throws Exception{
		//System.out.println(u);
		String t = "";
		//Creation des objets
		URL url = new URL(u);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		//Ouverture de la connextion vers l'url
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String tmp = "";
		while((tmp=in.readLine()) != null){
			t += tmp;
		}
		con.disconnect();
		return t;
	}
	
	public static String serviceInscription(String identite, String eMail) throws Exception {
		String url = URL_SERVEUR;
		url += "wal.php?inscription&identite=" + identite + "&mail=" + eMail + "";
		return utiliserService(url);
	}
	
	public static String serviceActivation(String identifiantActivation) throws Exception {
		String url = URL_SERVEUR;
		url += "wal.php?activation=" + identifiantActivation + "";
		return utiliserService(url);
	}
	
	public static int getReponse(String jsonString) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(jsonString);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		int reponse = 0;
		try {
			reponse = jo.getJSONObject("etat").getInt("reponse");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		/*String message = "";
		try {
			message = jo.getJSONObject("etat").getString("message");
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
		return reponse;
	}
	
	public static String getIdApresActivation(String jsonString) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(jsonString);
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		String id = "";
		try {
			id = jo.getString("identifiant");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return id;
	}
	
	public static String serviceObtenirParametres(String idUser) throws Exception {
		String url = URL_SERVEUR;
		url += "wal.php?identifiant=" + idUser + "";
		return utiliserService(url);
	}
}








