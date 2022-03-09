package menu.principal;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import menu.MenuChoix;

/** Representation de l'action plein ecran dans le menu principal */
public class PleinEcran extends MenuChoix {

	private static String desc = "Plein écran / Fenêtre";
	private GameContainer container;
	
	public PleinEcran(int key, GameContainer container) {
		super(key, desc);
		this.container = container;
	}

	@Override
	public void run() {
		try {
			container.setFullscreen(!container.isFullscreen());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
