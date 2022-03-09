package equipe;


import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import armes.*;

public class Equipe {
	
    // Ensemble des joueurs de l'équipe
    private ArrayList<Personnage> joueurs = new ArrayList<Personnage>();
    // Numéro dand l'ensemble des joueurs du joueur courant
    private int joueurCourant = 0;
    // Infos relatives à l'équipe
    private InfosEquipe infos;
    
    // L'équipe est anéantie
    private boolean aneantie;
        
	public Equipe(int nbJoueurs, int mapHeight, ArrayList<Integer[]> spawnStart, InfosEquipe infosEquipe, int numEquipe) throws SlickException {
		this.infos = infosEquipe;
		this.aneantie = false;
		
		for (int i = 0; i < nbJoueurs; i++) {
			Animation[] animations = this.buildAnimation(infos);
			this.joueurs.add(new Personnage(spawnStart.get(numEquipe * nbJoueurs + i)[0], spawnStart.get(numEquipe * nbJoueurs + i)[1], .15f, animations, SpecialisationID.SN));
		}	
	}
	
	/**
	* Construction des animations d'un personnage à partir de l'emplacement des graphismes
	* sur un sprite.
	*/
	public Animation[] buildAnimation(InfosEquipe infos) throws SlickException {
		Animation[] anims = new Animation[8];
		anims[0] = loadAnimation(infos.getSheetSprite()[1], ((int) infos.getWidth()[0]/infos.getWidth()[1]) - infos.getIdle()[1], ((int) infos.getWidth()[0]/infos.getWidth()[1]) - infos.getIdle()[0], infos.getIdle()[2]); // Gauche immobile
		anims[1] = loadAnimation(infos.getSheetSprite()[0], infos.getIdle()[0], infos.getIdle()[1], infos.getIdle()[2]); // Droite immobile
		anims[4] = loadAnimation(infos.getSheetSprite()[1], ((int) infos.getWidth()[0]/infos.getWidth()[1]) -infos.getMvm()[1], ((int) infos.getWidth()[0]/infos.getWidth()[1]) - infos.getMvm()[0], infos.getMvm()[2]);    // Gauche mvm
		anims[5] = loadAnimation(infos.getSheetSprite()[0], infos.getMvm()[0], infos.getMvm()[1], infos.getMvm()[2]);    // Droite mvm
		return anims;
	}

	/**
	* Chargement des animations associées à un personnage.
	*/
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		
		return animation;
	}
	
	/**
	* Obtenir le joueur courant de l'équipe.
	*/
	public Personnage getJoueurCourant() {
		return this.joueurs.get(this.joueurCourant);
	}
	
	/**
	* Obtenir le nombre de joueurs de l'équipe.
	*/
	public int getNbJoueurs() {
		return this.joueurs.size();
	}
	
	/**
	* Obtenir la couleur de l'équipe.
	*/
	public Color getCouleur() {
		return this.infos.getCouleur();
	}
	
	/**
	* Obtenir le nom de l'équipe.
	*/
	public String getNom() {
		return this.infos.getNom();
	}
	
	/**
	* Obtenir le ie joueur de l'équipe.
	*/
	public Personnage getJoueur(int i) {
		assert (i < this.joueurs.size() && i >= 0);
		return this.joueurs.get(i);
	}
	
    /**
    * Changer de joueur courant.
    */
    public void changerJoueurCourant() {
    	int prevJoueur = this.joueurCourant;
        do {
            this.joueurCourant = ++this.joueurCourant % this.joueurs.size();
        } while (!this.joueurs.get(joueurCourant).isAlive() && prevJoueur != this.joueurCourant);
        
        if (prevJoueur == this.joueurCourant && !this.joueurs.get(joueurCourant).isAlive()) {
        	this.aneantie = true;
        }
    }
    
	/**
	* Obtenir la tombe du joueur.
	*/
	public Image getTombe() {
		return infos.getTombe();
	}
	
	/**
	* Savoir s'il reste des joueurs vivants dans une équipe
	*/
	public boolean isAneantie() {
		return aneantie;
	}

}

