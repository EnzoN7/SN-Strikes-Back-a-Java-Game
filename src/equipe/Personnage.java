package equipe;

import org.newdawn.slick.Animation;

import armes.*;
import jeu.Direction;
import jeu.SoundManager;


/** Cette classe modelise un personnage, ses caracteristiques et ses actions. */
public class Personnage {

	private float velociteSaut = 0;
	private boolean isJumping = false;

	//private static final int MAXARME = 20;
	
	/** Masse du personnage. */
	private final int MASSE = 25;

	/** Arme du personnage. */
	private Arme arme;

	/** Ensemble des armes disponibles. */
	//private boolean[] arsenal;
	
	/** Premiere coordonnee de la position du personnage sur le terrain. */
	private float x;

	/** Seconde coordonnee de la position du personnage sur le terrain. */
	private float y;

	/** Vitalite du personnage à 100 par défaut, si nulle alors le personnage meurt. */
	private int vie = 100;
	
	/** Vitesse du personnage */
	private float velocite;
	
	/** Direction du personnage */
	private Direction direction;
	
	/** Animations du personnage */
	private Animation[] animations = new Animation[8];
	
	
	public Personnage(int spawnX, int spawnY, float velocite, Animation[] animations, SpecialisationID specialisation) {
		//this.arsenal = new boolean[MAXARME];
		this.x = spawnX;
		this.y = spawnY;
		this.velocite = velocite;
		this.direction = Direction.GAUCHE;
		this.animations = animations;
		
		switch (specialisation) {
			case SN:
				this.arme = new Ordinateur();
				break;
			case MFEE:
				this.arme = new PistoletAEau();
				break;
			case EEEA:
				this.arme = new Taser();
				break;
		}
	}

	public Projectile tirer(float directionX, float directionY) throws MunitionVideException{
		SoundManager.playSonTir();
		return this.arme.tirer(directionX, directionY, null, this.x, this.y);
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public float getVelociteSaut(){
		return this.velociteSaut;
	}

	public boolean getIsJumping(){
		return this.isJumping;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public Animation[] getAnimations() {
		return animations;
	}

	public float getVelocite(){
		return this.velocite;
	}

	public int getMasse(){
		return this.MASSE;
	}

	public double getVie() {
		return this.vie;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setVelociteSaut(float v){
		this.velociteSaut = v;
	}

	public void setIsJumping(boolean isJumping){
		this.isJumping = isJumping;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setAnimations(Animation[] animations) {
		this.animations = animations;
	}

	public void retirerVie(double dmg) {
		this.vie -= dmg;
		if (!this.isAlive()) SoundManager.playSonMort();
	}
	
    public boolean isAlive() {
        return this.vie > 0;
    }
}
