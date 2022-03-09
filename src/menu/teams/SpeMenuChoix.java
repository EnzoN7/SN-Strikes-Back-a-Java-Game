package menu.teams;

import org.newdawn.slick.SlickException;

import equipe.InfosEquipe;
import jeu.TeamsState;
import menu.MenuChoix;

/** Classe representant le menu de choix de specialisation generique. */
public abstract class SpeMenuChoix extends MenuChoix {

	private InfosEquipe infos;

	public SpeMenuChoix(int key, String desc) throws SlickException {
		super(key, desc);
		this.infos = getInfos();
	}
	
	/** Recupere les informations relative a une specialisation d'equipe. */
	protected abstract InfosEquipe getInfos() throws SlickException;

	public void run() {
		if (!TeamsState.equipeChoisie(this.infos)) {
			TeamsState.ajouterEquipe(this.infos);
			super.setIsChoosen(true);
		} else {
			TeamsState.retirerEquipe(this.infos);
			super.setIsChoosen(false);
		}
	}
}
