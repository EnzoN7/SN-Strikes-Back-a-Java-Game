package menu.map;

import jeu.MenuState;
import menu.MenuChoix;

public class Retour extends MenuChoix {

	private static String desc = "Retour";
	private MenuState menust;
	
	public Retour(int key, MenuState menust) {
		super(key, desc);
		this.menust = menust;
	}

	public void run() {
		this.menust.goToPrevState();
	}
}
