package jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import menu.endgame.*;

/** Classe responsable du menu principal basique. */
public class EndGameState extends MenuState {

	public static final int ID = 1000;
	
	private static String nomGagnant;

	@Override
	public void initMenu() {
		menu.addMenuChoix(Input.KEY_SPACE, new Relancer(Input.KEY_SPACE, this), true);
		menu.addMenuChoix(Input.KEY_ESCAPE, new Quitter(Input.KEY_ESCAPE, container));
		menu.addMenuChoix(Input.KEY_F, new PleinEcran(Input.KEY_F, container));
	}

	@Override
	protected Image getBackground() {
		try {
			return new Image("res/background/menuEndgame.png");
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
		return -1; // Il n'y en a pas
	}
	
	public static void setNomGagnant (String nom) {
		nomGagnant = nom;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
	}
	
	@Override
	protected void drawMessage(Graphics g) {
		g.setColor(Color.red);
		g.drawString("VAINQUEUR : " + nomGagnant + " !", (int) (container.getWidth() * 0.75), ((container.getHeight() / 2 - 115)));
	}
	

	/** Recupere l'id unique de la fenetre.
	 * @return The game unique ID of this state
	 */
	@Override
	public int getID() {
		return ID;
	}
}
