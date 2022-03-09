package physique;

import armes.Clic;

import java.lang.Math.*;

/** Classe modélisant la vitesse d'un objet dans l'ensemble R^2 */
public class Vitesse {
	
	private float vx;
	private float vy;

	/** Représentation d'une vitesse.
	 * @param x
	 * @param y
	 */
	public Vitesse(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public Vitesse(float directionX, float directionY, float xPerso, float yPerso) {
		System.out.println("Direction x" + directionX + "Direction Y" + directionY);
		System.out.println("xPerso " + xPerso + " Y Perso " + yPerso);
		float norme = (float) Math.sqrt(Math.pow(directionX - xPerso, 2) + Math.pow(directionY - yPerso, 2));
		this.vx = (directionX - xPerso) / norme;
		this.vy = (directionY - yPerso) / norme;
	}

	/** Récupère la valeur x de la vitesse. */
	public float getVx() {
		return vx;
	}

	/** Modifie la valeur x de la vitesse.
	 * @param x
	 */
	public void setVx(float vx) {
		this.vx = vx;
	}

	/** Récupère la valeur y de la vitesse. */
	public float getVy() {
		return this.vy;
	}

	/** Modifie la valeur u de la vitesse.
	 * @param u
	 */
	public void setVy(float vy) {
		this.vy = vy;
	}
}
