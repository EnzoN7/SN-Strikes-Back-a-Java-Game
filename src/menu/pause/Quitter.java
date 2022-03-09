package menu.pause;

import jeu.MapGameState;
import jeu.MenuState;
import jeu.SoundManager;
import jeu.TeamsState;
import menu.MenuChoix;

public class Quitter extends MenuChoix {
	
	private final static String desc = "Quitter";
	private MenuState menust;

	public Quitter(int key, MenuState menust) {
		super(key, desc);
		this.menust = menust;
	}

	@Override
	public void run() {
		this.menust.goToNextState();
		MapGameState.clearTimer();
		MapGameState.viderListeJoueurs();
		SoundManager.playMusiqueAccueil();
		TeamsState.resetEquipe();
	}

}
