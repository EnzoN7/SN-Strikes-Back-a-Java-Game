package armes;

public class CoupDJus extends Projectile {
	private final static float vitesse = 0.5f;
	public CoupDJus(float masse, float frottement, float degats, float directionX, float directionY, float xPerso, float yPerso) {
		super(0.5f, 0.000000001f, 1, directionX, directionY, xPerso, yPerso);
		System.out.println(this.getVx() +" "+ this.getVy() );
		this.degats = degats;
	}

}
