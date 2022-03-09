package menu.principal;

import jeu.MenuState;
import menu.MenuChoix;

/** Representation de l'action Commencer dans le menu principal */
public class Commencer extends MenuChoix {
	
	private static String desc = "Commencer";
	private MenuState menust;
	
	public Commencer(int key, MenuState menust) {
		super(key, desc);
		this.menust = menust;
	}

	@Override
	public void run() {
		this.menust.goToNextState();
	}
}
