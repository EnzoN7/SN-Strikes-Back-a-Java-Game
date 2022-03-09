package menu.endgame;

import org.newdawn.slick.GameContainer;
import menu.MenuChoix;

/** Representation de l'action quitter dans le menu principal */
public class Quitter extends MenuChoix {
	
	private static String desc = "Quitter";
	private GameContainer container;

	public Quitter(int key, GameContainer container) {
		super(key, desc);
		this.container = container;
	}

	@Override
	public void run() {
		container.exit();
	}

}
