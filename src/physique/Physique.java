package physique;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import equipe.Personnage;

import java.util.ArrayList;

/**
 * Classe modélisant la physique du jeu (déplacement des corps et objets,
 * gravité, etc...).
 */
public class Physique {

	private static final float EPSILON = .1f;

	private final float GRAVITE;
	private final float VITESSE_INITIALE = 0.9f;

	private final float HAUTEUR_HITBOX = 25;
	private final float LARGEUR_HITBOX = 15;
	
	private final float HAUTEUR_HITBOX_PROJ = 60;
	private final float LARGEUR_HITBOX_PROJ = 20;

	// taille d'une case de la carte
	private final int TILE_W;
	private final int TILE_H;

	public Physique(float gravite, int W, int H) {
		this.GRAVITE = gravite;
		this.TILE_W = W;
		this.TILE_H = H;

	}

	// Accesseurs de la hitbox d'un personnage
	private int getHitboxGauche(float x) {
		return (int) (x - LARGEUR_HITBOX / 2) / TILE_W;
	}

	private int getHitboxDroite(float x) {
		return (int) (x + LARGEUR_HITBOX / 2) / TILE_W;
	}

	private int getHitboxBas(float y) {
		return (int) (y - 1) / TILE_H;
	}

	private int getHitboxHaut(float y) {
		return (int) (y - HAUTEUR_HITBOX) / TILE_H;
	}
	
	public int getHitboxGaucheProj(float x) {
		return (int) (x - LARGEUR_HITBOX_PROJ);
	}

	public int getHitboxDroiteProj(float x) {
		return (int) x;
	}

	public int getHitboxBasProj(float y) {
		return (int) y - 10;
	}

	public int getHitboxHautProj(float y) {
		return (int) (y - HAUTEUR_HITBOX_PROJ);
	}


	public boolean isGrounded(TiledMap map, Personnage perso) {
		int coucheCollision = map.getLayerIndex("collision");
		boolean grounded = false;

		// hitbox du personnage
		int hitboxGauche = getHitboxGauche(perso.getX());
		int hitboxDroite = getHitboxDroite(perso.getX());

		for (int x = hitboxGauche; x <= hitboxDroite; x++) {

			Image tile = map.getTileImage(x, ((int) perso.getY() + 1) / TILE_H, coucheCollision);
			grounded = (tile != null) || grounded;
		}
		return grounded;

	}

	private boolean isCollision(float prochainX, float prochainY, TiledMap map) {
		boolean collision = false;
		int coucheCollision = map.getLayerIndex("collision");

		// hitbox du personnage
		int hitboxGauche = getHitboxGauche(prochainX);
		int hitboxDroite = getHitboxDroite(prochainX);
		int hitboxBas = getHitboxBas(prochainY);
		int hitboxHaut = getHitboxHaut(prochainY);

		// Si le personnage est � la limite de la fen�tre, il ne pourra que rebrousser
		// chemin.
		if (hitboxDroite == 49 || hitboxGauche == 0 || hitboxBas == 27 || hitboxHaut == 0) {
			collision = true;
		}
		// Sinon, on cherche un �ventuel mur.
		else {
			for (int x = hitboxGauche; x <= hitboxDroite; x++) {
				for (int y = hitboxHaut; y <= hitboxBas; y++) {
					Image tile = map.getTileImage(x, y, coucheCollision);
					collision = (tile != null) || collision;
				}
			}
		}
		return collision;
	}

	/*
	 * Calculer la prochaine position du personnage selon la coordonnées X
	 */
	public void deplacement(TiledMap map, int t, Personnage perso) {
		float prochainX = perso.getX();

		switch (perso.getDirection()) {
		case GAUCHE:
			prochainX = perso.getX() - perso.getVelocite() * t;
			break;
		case DROITE:
			prochainX = perso.getX() + perso.getVelocite() * t;
			break;
		}

		if (!this.isCollision(prochainX + EPSILON, perso.getY() + EPSILON, map)) {
			perso.setX(prochainX);
		}
	}

	/*
	 * Saut si le perso est au sol Return true si le perso a sauté, false sinon
	 */
	public boolean sauter(TiledMap map, Personnage perso) {
		if (isGrounded(map, perso)) {
			perso.setIsJumping(!perso.getIsJumping());
			perso.setVelociteSaut(VITESSE_INITIALE);
			return true;
		}
		
		return false;
	}

	/**
	 * Calculer la prochaine position du personnage selon la coordonnées y
	 */
	public void tomber(TiledMap map, int t, Personnage perso) {
		if (!isGrounded(map, perso) || perso.getIsJumping()) {
			float prochainY = perso.getY() - perso.getVelociteSaut() * t;
			perso.setIsJumping(false);
			
			if (!this.isCollision(perso.getX() + EPSILON, prochainY + EPSILON, map)) {
				perso.setY(prochainY);
				perso.setVelociteSaut(perso.getVelociteSaut() - this.GRAVITE * perso.getMasse() * t);
			} else { /* le personnage touche un obstacle */
				perso.setVelociteSaut(0);
			}
			
		} else { /* le personnage est au sol */
			perso.setVelociteSaut(0);
		}
	}

	/** Indique si le projectile est dans la hitbox du personnage.
	 * @param perso Le personnage reference
	 * @param proj Le projectile reference
	 * @return True si le projectile est dans la hitbox du personnage
	 */
	boolean isHit(Personnage perso, armes.Projectile proj) {
		int hitboxGauche = getHitboxGaucheProj(perso.getX());
		int hitboxDroite = getHitboxDroiteProj(perso.getX());
		int hitboxBas = getHitboxBasProj(perso.getY());
		int hitboxHaut = getHitboxHautProj(perso.getY());

		return	   proj.getX() >= hitboxGauche 
				&& proj.getX() <= hitboxDroite 
				&& proj.getY() <= hitboxBas
				&& proj.getY() >= hitboxHaut;
	}
	
	/*
	 * Calculer la prochaine position du projectile
	 */
	public boolean trajectoireProjectile(TiledMap map, int t, ArrayList<equipe.Equipe> equipes, armes.Projectile proj, int equipeCourante) {
		// Calculer la prochaine position
		//System.out.println(proj.getVx() + " " + proj.getVy());

		float prochainX = proj.getX() + proj.getVx() * t;
		//proj.setVx(proj.getVx() - 1);
		float prochainY = proj.getY() + proj.getVy() * t ;//- this.GRAVITE * t * t / 2;
		//proj.setVy(proj.getVy() - 1);
		boolean touche = false;

		// Verifier si le projectile touche un personnage
		for (equipe.Equipe equ : equipes) {
			for (int i = 0; i < equ.getNbJoueurs(); i++) {
				Personnage perso = equ.getJoueur(i);
				// TODO
				if (perso != equipes.get(equipeCourante).getJoueurCourant()) {
					if (perso.isAlive() && isHit(perso, proj)) {
						// TODO: Qu'est ce qu'on fait si un perso est touché ?
						perso.retirerVie(proj.getDegat());
						touche = true;
					}
				}
			}
		}
		if (touche) {
			// TODO:

		}
		// Verifier si le projectile entre en collision avec le terrain
		else {
			// TODO destruction du terrain ?
			touche = isCollision(prochainX, prochainY, map);
		}
		// Le tir a atteint un personnage ou le décor
		if (touche) {
			// supprimer le projectile du terrain
			System.out.println("TOUCHE! vx " + proj.getVx() + " vy " + proj.getVy() + " x,y " + proj.getX() + " " + proj.getY());
			// TODO proj.supprimer();
		}

		else {
			// appliquer la gravite et le frottement
			//proj.setVy(proj.getVy() - (float) 0.1 * t);
			//proj.setVx((float) (proj.getVy() * Math.pow(1f - proj.getFrottement(), t)));

			// mettre a jour la position du projectile
			proj.setX(prochainX);
			proj.setY(prochainY);
		}
		//System.out.println(proj.getVx() + " " + proj.getVy());

		return touche;
	}

}