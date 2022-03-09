package armes;

public class Clic extends Projectile {
	private final static float vitesse = 0.5f;
	public Clic(float masse, float frottement, float degats, float directionX, float directionY, float xPerso, float yPerso) {
		//super(masse, frottement, 1,1,1, xPerso, yPerso);
		//super(0.5f, 0.000000001f, 1,Clic.vitesse*directionX,Clic.vitesse*directionY, xPerso, yPerso);
		
		super(0.5f, 0.000000001f, 1, directionX, directionY, xPerso, yPerso);
		System.out.println(this.getVx() +" "+ this.getVy() );
		this.degats = degats;
	}

}
