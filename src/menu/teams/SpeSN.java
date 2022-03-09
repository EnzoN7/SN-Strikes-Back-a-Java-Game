package menu.teams;

import org.newdawn.slick.SlickException;

import equipe.InfosEquipe;
import equipe.specialisation.InfosSN;

/** Classe representant le menu de choix de specialisation SN. */
public class SpeSN extends SpeMenuChoix {

	private static final String desc = "SN";

	public SpeSN(int key) throws SlickException {
		super(key, desc);
	}
	
	@Override
	protected InfosEquipe getInfos() throws SlickException {
		return new InfosSN();
	}
}
