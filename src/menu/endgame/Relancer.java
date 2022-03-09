package menu.endgame;

import jeu.MapGameState;
import jeu.MenuState;
import jeu.SoundManager;
import jeu.TeamsState;
import menu.MenuChoix;

/** Representation de l'action Commencer dans le menu principal */
public class Relancer extends MenuChoix {
	
	private static String desc = "Retour au menu principal";
	private MenuState menust;
	
	public Relancer(int key, MenuState menust) {
		super(key, desc);
		this.menust = menust;
	}

	@Override
	public void run() {
		this.menust.goToNextState();
		MapGameState.clearTimer();
		MapGameState.viderListeJoueurs();
		TeamsState.resetEquipe();
		SoundManager.playMusiqueAccueil();
	}
}
