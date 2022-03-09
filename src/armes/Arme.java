package armes;

public abstract class Arme {
	
	/** Identifiant de l'arme */
	protected ArmeID id;
	
	/** Le type de projectile de l'arme */
	protected ProjectileID projectile;
	
	/** L'ID de la spécialisation du joueur portant l'arme */
	protected SpecialisationID spe;
	
	/** Specialisation du porteur d'arme */
	protected SpecialisationID specialisation;
	
	/** Le nombre de munitions max */
	protected int munitionsMax;
	
	/** Le nombre de munitions */
	protected int munitions;
	
	
	/** Crée une arme
	 * @param nom Le nom de l'arme
	 * @param degat Les dégats de l'arme
	 */
	public Arme(ArmeID id, ProjectileID projectile, int munitionsMax, SpecialisationID spe) {
		this.id = id;
		this.projectile = projectile;
		this.munitionsMax = munitionsMax;
		this.spe = spe;
		this.recharger();
	}
	
	/** Tire un projectile dans la direction représenté par le point (x,y) 
	 * @param xPerso TODO
	 * @param yPerso TODO
	 * @throws MunitionVideException Si les munitions sont vides */
	public Projectile tirer(float x, float y, ProjectileID projectile, float xPerso, float yPerso) throws MunitionVideException {
		if (this.munitions <= 0) {
			throw new MunitionVideException();
		}
		if (projectile == null) {
			projectile = this.projectile;
		}
		//this.munitions--;
		return Projectile.getProjectile(projectile, this.getDegat(projectile), x, y, xPerso, yPerso); // renvoie un new projectile avec la bonne masse et le bon coef de frottements
	}
	
	/** Recharge les munitions */
	public void recharger() {
		this.munitions = munitionsMax;
	}
	
	public String toString() {
		return "[" + id + "|" + projectile + "|" + munitions + "/" + munitionsMax + "]";
	}
	
	protected abstract float getDegat(ProjectileID proj);
	
	protected abstract int getDegatMunition(ProjectileID proj);
	
}
