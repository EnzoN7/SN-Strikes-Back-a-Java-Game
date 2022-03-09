package jeu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/** Classe permettant l'initalisation des menus et le lancement du jeu. */
public class StateGame extends StateBasedGame {
	

	/** Lancement du jeu.
	 * @param args
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new StateGame(), 1600, 900, false).start();
	}

	public StateGame() {
		super("SN Strikes Back");
	}

	/**
	 * Methode initialisant les differentes pages de l'application.
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// Ajout d'un menu principal basique.
		addState(new MainScreenGameState());
		// Choix de la carte
		addState(new MapState());
		// Choix du nombre d'équipes.
		addState(new TeamsState());
		// Choix du nombre de joueurs par équipe.
		addState(new PlayersState());
		// Ajout de l'�cran de jeu.
		addState(new MapGameState());
		// Menu pause
		addState(new PauseState());
		// Fin de partie
		addState(new EndGameState());
	}

}
