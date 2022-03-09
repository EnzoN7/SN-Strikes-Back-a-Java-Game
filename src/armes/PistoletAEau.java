package armes;

public class PistoletAEau extends Arme {

	private static final int MUNITIONS_MAX = 4;
	
	public PistoletAEau() {
		super(ArmeID.PISTOLETAEAU, ProjectileID.JETDEAU, MUNITIONS_MAX, SpecialisationID.MFEE);
	}

	@Override
	public Projectile tirer(float x, float y, ProjectileID projectile, float xPerso, float yPerso) throws MunitionVideException {
		System.out.println("[" + id + "] en direction de ("
				+ x + "," + y + ")");
		return super.tirer(x, y, projectile, xPerso, yPerso);
	}
	
	protected float getDegat(ProjectileID proj) {
		if (this.spe == SpecialisationID.SN) {
			return getDegatMunition(proj);
		} else if (this.spe == SpecialisationID.EEEA) {
			return (float) 1.2 * getDegatMunition(proj);
		} else if (this.spe == SpecialisationID.MFEE) {
			return (float) 2.3 * getDegatMunition(proj);
		}
		return 3 * getDegatMunition(proj);
	}
	
	protected int getDegatMunition(ProjectileID proj) {
		if (proj == ProjectileID.JETDEAU) {
			return 4;
		}
		return 1;
	}

}
