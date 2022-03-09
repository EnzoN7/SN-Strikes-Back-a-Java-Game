package jeu;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import equipe.InfosEquipe;
import menu.principal.PleinEcran;
import menu.teams.*;

/**
 * Classe correspondant au menu du choix des spécialités des équipes
 */
public class TeamsState extends MenuState {

	public static final int ID = 66;
	private static ArrayList<InfosEquipe> infos = new ArrayList<InfosEquipe>();

	/** Initialise le menu */
	@Override
	public void initMenu() throws SlickException {
		this.menu.addMenuChoix(Input.KEY_1, new SpeSN(Input.KEY_1), true);
		this.menu.addMenuChoix(Input.KEY_2, new Spe3EA(Input.KEY_2));
		this.menu.addMenuChoix(Input.KEY_3, new SpeMF2E(Input.KEY_3));
		this.menu.addMenuChoix(Input.KEY_4, new SpeSHS(Input.KEY_4));
		this.menu.addMenuChoix(Input.KEY_SPACE, new ChoixSpe(Input.KEY_SPACE, this));
		this.menu.addMenuChoix(Input.KEY_ESCAPE, new Retour(Input.KEY_ESCAPE, this));
		this.menu.addMenuChoix(Input.KEY_F, new PleinEcran(Input.KEY_F, this.container));
	}

	@Override
	protected Image getBackground() {
		try {
			return new Image("res/background/menuEquipes.png");
		} catch (SlickException e) {
			return null;
		}
	}

	@Override
	protected int getNextState() {
		return PlayersState.ID;
	}

	@Override
	protected int getPrevState() {
		return MapState.ID;
	}

	@Override
	public void goToPrevState() {
		super.goToPrevState();
		resetEquipe();
	}

	@Override
	public int getID() {
		return ID;
	}

	/** Selectionne une equipe.
	 *  @param equ l'equipe a selectionner
	 */
	public static void ajouterEquipe(InfosEquipe equ) {
		infos.add(equ);
	}

	/** Retire une equipe des equipes selectionnees.
	 *  @param equ l'equipe a verifier
	 */
	public static void retirerEquipe(InfosEquipe equ) {
		infos.remove(equ);
	}
	
	/** Verifie si une equipe a deja ete choisie.
	 * @param info l'equipe a verifier
	 * @return Vrai si equ a deja ete choisie
	 */
	public static boolean equipeChoisie(InfosEquipe equ) {
		return infos.contains(equ);
	}

	/** Nombre de specialites choisies actuellement.
	 *  @return le nombre de specialite choisie
	 */
	public static int getNbEquipes() {
		return infos.size();
	}

	/** Obtenir la ie équipe choisie.
	 *  @param i l'indice de l'equipe
	 *  @return l'equipe situe a l'indice i
	 */
	public static InfosEquipe getEquipe(int i) {
		return infos.get(i);
	}
	
	/** Vide la liste des equipes. */
	public static void resetEquipe() {
		infos.clear();
	}
}
