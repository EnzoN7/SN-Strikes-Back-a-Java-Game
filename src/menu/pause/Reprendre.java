package menu.pause;

import jeu.MenuState;
import jeu.SoundManager;
import menu.MenuChoix;

public class Reprendre extends MenuChoix {
	private final static String desc = "Reprendre";
	private MenuState menust;

	public Reprendre(int key, MenuState menust) {
		super(key, desc);
		this.menust = menust;
	}

	@Override
	public void run() {
		SoundManager.resumeMusique();
		this.menust.goToPrevState();
	}

}
