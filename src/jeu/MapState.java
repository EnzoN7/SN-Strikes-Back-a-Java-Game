package jeu;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import menu.map.*;
import menu.principal.PleinEcran;

public class MapState extends MenuState {

	public static final int ID = 671;
	private static String choixMap;
	private static ArrayList<Integer[]> spawnPoints;
	
	@Override
	public void initMenu() {
		menu.addMenuChoix(Input.KEY_1, new Map1(Input.KEY_1, this), true);
		menu.addMenuChoix(Input.KEY_2, new Map2(Input.KEY_2, this));
		menu.addMenuChoix(Input.KEY_ESCAPE, new Retour(Input.KEY_ESCAPE, this));
		menu.addMenuChoix(Input.KEY_F, new PleinEcran(Input.KEY_F, container));
	}

	@Override
	protected Image getBackground() {
		try {
			return new Image("res/background/menuMap.png");
		} catch (SlickException e) {
			return null;
		}
	}

	@Override
	protected int getNextState() {
		return TeamsState.ID;
	}

	@Override
	protected int getPrevState() {
		return MainScreenGameState.ID;
	}

	public static void setChoixMap(String map) {
		choixMap = map;
	}

	public static void setSpawn(ArrayList<Integer[]> spawns) {
		spawnPoints = spawns;
		Collections.shuffle(spawnPoints);
	}

	@Override
	public int getID() {
		return ID;
	}

	public static String getChoixMap() {
		return choixMap;
	}

	public static ArrayList<Integer[]> getSpawn() {
		return spawnPoints;
	}

}
