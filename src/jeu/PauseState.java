package jeu;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import menu.pause.*;
import menu.principal.PleinEcran;

public class PauseState extends MenuState {

	public static final int ID = 670;
	
	@Override
	public void initMenu() {
		menu.addMenuChoix(Input.KEY_SPACE, new Reprendre(Input.KEY_SPACE, this), true);
		menu.addMenuChoix(Input.KEY_ESCAPE, new Quitter(Input.KEY_ESCAPE, this));
		menu.addMenuChoix(Input.KEY_F, new PleinEcran(Input.KEY_F, container));
	}

	@Override
	protected Image getBackground() {
		try {
			return new Image("res/background/menuPause.png");
		} catch (SlickException e) {
			return null;
		}
	}

	@Override
	protected int getNextState() {
		return MainScreenGameState.ID;
	}

	@Override
	protected int getPrevState() {
		return MapGameState.ID;
	}

	@Override
	public int getID() {
		return ID;
	}

}
