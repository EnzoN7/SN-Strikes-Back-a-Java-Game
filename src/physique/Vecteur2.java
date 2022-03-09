package physique;

/** Classe modelisant un vecteur de R^2. */
public class Vecteur2 {

	private float x;
	private float y;

	/** Représentation d'un vecteur de R^2.
	 * @param x
	 * @param y
	 */
	public Vecteur2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/** Modifie la valeur x du vecteur.
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/** Modifie la valeur y du vecteur.
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/** Récupère la valeur x du vecteur. */
	public float getX() {
		return this.x;
	}

	/** Récupère la valeur y du vecteur. */
	public float getY() {
		return this.y;
	}
}
