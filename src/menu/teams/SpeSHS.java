package menu.teams;

import org.newdawn.slick.SlickException;

import equipe.InfosEquipe;
import equipe.specialisation.InfosSHS;

/** Classe representant le menu de choix de specialisation SHS. */
public class SpeSHS extends SpeMenuChoix {

	private static final String desc = "SHS";

	public SpeSHS(int key) throws SlickException {
		super(key, desc);
	}
	
	@Override
	protected InfosEquipe getInfos() throws SlickException {
		return new InfosSHS();
	}
}