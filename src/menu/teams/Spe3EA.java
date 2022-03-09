package menu.teams;

import org.newdawn.slick.SlickException;

import equipe.InfosEquipe;
import equipe.specialisation.Infos3EA;

/** Classe representant le menu de choix de specialisation 3EA. */
public class Spe3EA extends SpeMenuChoix {

	private static final String desc = "3EA";

	public Spe3EA(int key) throws SlickException {
		super(key, desc);
	}
	
	@Override
	protected InfosEquipe getInfos() throws SlickException {
		return new Infos3EA();
	}
}