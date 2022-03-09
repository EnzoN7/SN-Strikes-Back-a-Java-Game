package armes;

public class Ordinateur extends Arme {

	private static final int MUNITIONS_MAX = 10;
	
	public Ordinateur() {
		super(ArmeID.ORDINATEUR, ProjectileID.CLIC, MUNITIONS_MAX, SpecialisationID.SN);
	}

	@Override
	public Projectile tirer(float x, float y, ProjectileID projectile, float xPerso, float yPerso) throws MunitionVideException {
		System.out.println("[" + id + "] en direction de ("
				+ x + "," + y + ")");
		return super.tirer(x, y, ProjectileID.CLIC, xPerso, yPerso);
	}
	
	protected float getDegat(ProjectileID proj) {
		if (this.spe == SpecialisationID.SN) {
			return 2 * getDegatMunition(proj);
		} else if (this.spe == SpecialisationID.EEEA) {
			return (float) 1.2 * getDegatMunition(proj);
		} else if (this.spe == SpecialisationID.MFEE) {
			return getDegatMunition(proj);
		}
		return 3 * getDegatMunition(proj);
	}
	
	protected int getDegatMunition(ProjectileID proj) {
		if (proj == ProjectileID.CLIC) {
			return 8;
		}
		return 1;
	}

}
