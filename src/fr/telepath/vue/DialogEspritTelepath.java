package fr.telepath.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



//AUTEUR : Aymerik ABOSO , MASTER 1 MIAGE , Université des Antilles - Guadeloupe
//2016 - 2017



public class DialogEspritTelepath extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Dimension DIMENSION_DIALOG = new Dimension(500, 300);
	private static final boolean REDIMENSIONNABLE = false;
	private static final Color COULEUR_TEXTE = Color.WHITE;
	private static final int MARGE_TEXTE_GAUCHE = 10;
	private static final String TEXTE = 
			"<html>"
					+ "<h1>Esprit de <i>" + Fenetre.getNomApplication() + "</i></h1>"
					+ "Avec <i>" + Fenetre.getNomApplication() + " </i>vous pouvez discuter avec un correspondant<br></i>" 
					+ "en connectant directement vos esprits !"
					+ "<br><i>" + Fenetre.getNomApplication() + " </i>permet d'établir un lien entre deux personnes. <i>"
					+ "<i>Leurs messages<br>sont effacés du système</i> une fois que les deux correspondants les ont <i>lus</i>."
					+ "<br>Il y a donc <i>aucune trace</i> des messages une fois <i>" + Fenetre.getNomApplication() + " </i> fermé !"
					+ "<br><br>Attention donc à avoir lu tous les messages avant de vous en aller !"
					+ "<br><br>Reset des discussions :<br>"
					+ "Lorsque vous quittez une discussion (pour changer de correspondant<br>par exemple, ou non) le contenu "
					+ "de cette discussion est effacé.<br>Il faut donc d'autant plus avoir lu convenablement une"
					+ "<br>discussion avant de la quitter."
					+ "<br><br><font size=\"6\">Bonne discussion ! :)</font>"
					+ "<br><br>"
					+ "<font size=\"4\">" + Fenetre.getNomAuteur() + "</font>"
					+ "<br><br><br>- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <br><br><br>"
					+ "<h1>A venir dans une prochaine version</h1><br>"
					+ "<br><font size=\"5\">Export de discussion : </font><br>Vous pourrez exporter une conversation dans un fichier"
					+ "<br>pour la conserver.<br><br>"
					+ "<font size=\"5\">Chiffrement des messages :</font><br>"
					+ "Pour vous assurer que vos messages ne sont <i>pas lisibles par un tier</i>, "
					+ "<br>vous pourrez définir un <i>mot de passe</i> qui <i>codera</i> les messages de"
					+ "<br>votre <i>conversation</i>."
					+ "<br>Les messages envoyés seront codés par ce mot de passe."
					+ "<br>Les message reçus seront décodés par ce mot de passe."
					+ "<br>Il faudra donc convenir ou non de ce mot de passe avec votre correspondant"
					+ "<br>pour une conversation donnée ou plusieurs."
					+ "<br>Le mot de passe d'une conversation pourrait par exemple être :"
					+ "<br><br>&lt&lt J'aime manger une pomme rouge au réveil &gt&gt"
					+ "<br><br>Ainsi, même si votre correspondant n'a pas lu le message envoyé, "
					+ "<br>et donc qu'il est encore dans le système en <i>attente de lecture</i>, "
					+ "<br>il ne <i>pourra pas</i> être décodé pour être lu par un tier "
					+ "<br>ou <i>même les créateurs</i> du système <i>" + Fenetre.getNomApplication() + "</i>."
					+ "<br>Votre message ne pourra pas non plus être intercepté et lu à"
					+ "<br>l'envoie ou la reception.<br><br>"
					+ "<font size=\"5\">Suppression du reset de discussion en quittant"
					+ "<br>une discussion :</font><br>"
					+ "Vous pourrez changer de correspondant tout en conservant la discussion "
					+ "<br>actuelle avec chacun d'entre d'eux."
					+ "<br><br><br>"
					+ "</html>";

	private Fenetre fenetreParent;
	private Container contenu;
	private JLabel labelTexte;
	private JScrollPane scrollPane; //Pour le scrolling
	private JPanel panneauFond; //scrolling fait sur ce panneau

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

	public DialogEspritTelepath(Fenetre fenetreParent, String nom, boolean modal){
		super(fenetreParent, nom, modal);
		this.fenetreParent = fenetreParent;
		setSize(DIMENSION_DIALOG.width, DIMENSION_DIALOG.height);
		setLocationRelativeTo(this.fenetreParent);
		setResizable(REDIMENSIONNABLE);

		this.labelTexte = new JLabel(TEXTE);
		this.labelTexte.setForeground(COULEUR_TEXTE);
		try {
			this.panneauFond = new PanneauImage(Fenetre.getNomDossierBackground() + "BackgroundEsprit.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.panneauFond.setLayout(new BorderLayout());
		this.panneauFond.add(labelTexte, BorderLayout.CENTER);
		JPanel panneauMargeGauche = new JPanel();
		panneauMargeGauche.setOpaque(false);
		panneauMargeGauche.setPreferredSize(new Dimension(MARGE_TEXTE_GAUCHE, DIMENSION_DIALOG.height));
		panneauFond.add(panneauMargeGauche, BorderLayout.WEST);
		this.scrollPane = new JScrollPane(this.panneauFond);
		this.contenu = this.getContentPane();
		contenu.setLayout(new BorderLayout());		
		
		contenu.add(this.scrollPane, BorderLayout.CENTER);
	}
}














