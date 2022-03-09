package jeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import menu.principal.*;

/** Classe responsable du menu principal basique. */
public class MainScreenGameState extends MenuState {

	public static final int ID = 666;

	@Override
	public void initMenu() {
		menu.addMenuChoix(Input.KEY_SPACE, new Commencer(Input.KEY_SPACE, this), true);
		menu.addMenuChoix(Input.KEY_ESCAPE, new Quitter(Input.KEY_ESCAPE, container));
		menu.addMenuChoix(Input.KEY_F, new PleinEcran(Input.KEY_F, container));
	}

	@Override
	protected Image getBackground() {
		try {
			return new Image("res/background/menuAccueil.png");
		} catch (SlickException e) {
			return null;
		}
	}

	@Override
	protected int getNextState() {
		return MapState.ID;
	}

	@Override
	protected int getPrevState() {
		return -1; // Il n'y en a pas
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		SoundManager.playMusiqueAccueil();
	}

	/** Recupere l'id unique de la fenetre.
	 * @return The game unique ID of this state
	 */
	@Override
	public int getID() {
		return ID;
	}
}
