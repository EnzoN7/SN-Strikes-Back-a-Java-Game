package armes;

public class Canon extends Arme {

	private static final int MUNITIONS_MAX = 4;
	
	public Canon() {
		super(ArmeID.CANON, ProjectileID.BALLE, MUNITIONS_MAX, SpecialisationID.MFEE);
	}

	@Override
	public Projectile tirer(float x, float y, ProjectileID projectile, float xPerso, float yPerso) throws MunitionVideException {
		System.out.println("[" + id + "] en direction de ("
				+ x + "," + y + ")");
		return super.tirer(x, y, projectile, xPerso, yPerso);
	}
	
	protected float getDegat(ProjectileID proj) {
		if (this.spe == SpecialisationID.SN) {
			return (float) 1.5 * getDegatMunition(proj);
		} else if (this.spe == SpecialisationID.EEEA) {
			return (float) 1.2 * getDegatMunition(proj);
		} else if (this.spe == SpecialisationID.MFEE) {
			return getDegatMunition(proj);
		}
		return getDegatMunition(proj);
	}
	
	protected int getDegatMunition(ProjectileID proj) {
		if (proj == ProjectileID.BALLE) {
			return 8;
		}
		return 0;
	}

}
