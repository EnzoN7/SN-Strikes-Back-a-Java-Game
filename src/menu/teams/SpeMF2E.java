package menu.teams;

import org.newdawn.slick.SlickException;

import equipe.InfosEquipe;
import equipe.specialisation.InfosMF2E;

/** Classe representant le menu de choix de specialisation MF2E. */
public class SpeMF2E extends SpeMenuChoix {

	private static final String desc = "MF2E";

	public SpeMF2E(int key) throws SlickException {
		super(key, desc);
	}
	
	@Override
	protected InfosEquipe getInfos() throws SlickException {
		return new InfosMF2E();
	}
}
