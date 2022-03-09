package armes;

import physique.Vitesse;

public abstract class Projectile extends Vitesse {
	private float masse;
	private float frottement;

	/** Le montant des dégats à l'impact */
	protected float degats;

	/* position du projectile */
	protected float x;
	protected float y;

	public Projectile(float masse, float frottement, float degats, float directionX, float directionY, float xPerso, float yPerso) {
		super(directionX, directionY, xPerso, yPerso - 32);
		this.masse = masse;
		this.frottement = frottement;
		this.degats = degats;
		this.x = xPerso ;
		this.y = yPerso - 32 ;
	}
	
	public float getFrottement() {
		return frottement;
	}
	
	public float getMasse() {
		return masse;
	}
	
	public float getDegat() {
		return this.degats;
	}
	
	public static Projectile getProjectile(ProjectileID projectile, float degats, float directionX, float directionY, float xPerso, float yPerso) {
		switch (projectile) {
		case BALLE:
			return new Balle(10, 10, degats, directionX, directionY, xPerso, yPerso);
		case GRENADE:
			return new Grenade(25, 15, degats, directionX, directionY, xPerso, yPerso);
		case CLIC:
			return new Clic(2, 1, degats, directionX, directionY, xPerso, yPerso);
		case JETDEAU:
			return new JetDEau(5, 10, degats, directionX, directionY, xPerso, yPerso);
		case COUPDJUS:
			return new CoupDJus(7, 2, degats, directionX, directionY, xPerso, yPerso);
		}
		return null;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}
}
