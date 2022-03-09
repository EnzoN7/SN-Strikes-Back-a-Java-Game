package jeu;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import menu.players.*;
import menu.principal.PleinEcran;

public class PlayersState extends MenuState {

	public static final int ID = 668;
	private static int nbJoueurs = 0;
	private static boolean finChoix = false;

	@Override
	public void initMenu() {
		menu.addMenuChoix(Input.KEY_1, new XJoueurs(1, Input.KEY_1, this), true);
		menu.addMenuChoix(Input.KEY_2, new XJoueurs(2, Input.KEY_2, this));
		menu.addMenuChoix(Input.KEY_3, new XJoueurs(3, Input.KEY_3, this));
		menu.addMenuChoix(Input.KEY_4, new XJoueurs(4, Input.KEY_4, this));
		menu.addMenuChoix(Input.KEY_ESCAPE, new Retour(Input.KEY_ESCAPE, this));
		menu.addMenuChoix(Input.KEY_F, new PleinEcran(Input.KEY_F, container));
	}

	@Override
	protected Image getBackground() {
		try {
			return new Image("res/background/menuNBJoueurs.png");
		} catch (SlickException e) {
			return null;
		}
	}

	@Override
	protected int getNextState() {
		return MapGameState.ID;
	}

	@Override
	protected int getPrevState() {
		return TeamsState.ID;
	}
	
	@Override
	public void goToPrevState() {
		super.goToPrevState();
		TeamsState.resetEquipe();
	}

	public static void setFinChoix(boolean bool) {
		finChoix = bool;
	}

	public static void setNbJoueurs(int nb) {
		nbJoueurs = nb;
	}

	@Override
	public int getID() {
		return ID;
	}

	public static int getNbJoueurs() {
		return nbJoueurs;
	}

	public static boolean getFinChoix() {
		return finChoix;
	}

}
