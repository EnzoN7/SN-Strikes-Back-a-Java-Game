package physique;

/** Classe modelisant l'ensemble R^2. */
public class Coordonnees {

	private Vecteur2 c;

	/** Représentation d'une position avec des coordonnées cartésienne.
	 * @param x
	 * @param y
	 */
	public Coordonnees(float x, float y) {
		this.c.setX(x);
		this.c.setY(y);
	}

	/** Modifie la valeur x de la coordonnée cartésienne.
	 * @param x
	 */
	public void setX(float x) {
		this.c.setX(x);
	}

	/** Modifie la valeur y de la coordonnée cartésienne.
	 * @param y
	 */
	public void setY(float y) {
		this.c.setY(y);
	}

	/** Récupère la valeur x de la coordonnée cartésienne. */
	public float getX() {
		return this.c.getX();
	}

	/** Récupère la valeur y de la coordonnée cartésienne. */
	public float getY() {
		return this.c.getY();
	}
}
