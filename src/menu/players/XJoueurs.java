package menu.players;

import jeu.MenuState;
import jeu.PlayersState;
import menu.MenuChoix;

/** Classe permettant de specifier le menu qui demande le nombre x de joueur(s) */
public class XJoueurs extends MenuChoix {

	private int nbJoueurs;
	private MenuState menust;
	
	public XJoueurs(int nbJoueurs, int key, MenuState menust) {
		super(key, nbJoueurs + " Joueur" + (nbJoueurs > 1 ? "s" : ""));
		this.nbJoueurs = nbJoueurs;
		this.menust = menust;
	}
	
	public void run() {
		PlayersState.setNbJoueurs(nbJoueurs);
		PlayersState.setFinChoix(true);
		this.menust.goToNextState();
	}

}
