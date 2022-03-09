package menu.teams;

import jeu.MenuState;
import jeu.TeamsState;
import menu.MenuChoix;

public class ChoixSpe extends MenuChoix {

	private static String desc = "Valider";
	private MenuState menust;

	public ChoixSpe(int key, MenuState menust) {
		super(key, desc);
		this.menust = menust;
	}

	public void run() {
		if (TeamsState.getNbEquipes() >= 2) {
			this.menust.goToNextState();
		} else {
			// TODO: afficher pas assez d'équipes choisies / faire bruit d'échecs
			setIsValid(false);
		}
	}
}
