package menu.map;

import java.util.ArrayList;

import jeu.MapState;
import jeu.MenuState;
import jeu.SoundManager;
import menu.MenuChoix;

public class Map1 extends MenuChoix {
	
	private static String desc = "La salle de TP des MF2E";
	private MenuState menust;
	private ArrayList<Integer[]> spawns = new ArrayList<Integer[]>();
	
	public Map1(int key, MenuState menust) {
		super(key, desc);
		this.menust = menust;
		
		this.spawns.add(new Integer[] {1550, 550});
		this.spawns.add(new Integer[] {40, 555});
		this.spawns.add(new Integer[] {530, 840});
		this.spawns.add(new Integer[] {1510, 740});
		this.spawns.add(new Integer[] {615, 320});
		this.spawns.add(new Integer[] {1135, 200});
		this.spawns.add(new Integer[] {870, 550});
		this.spawns.add(new Integer[] {910, 740});
		this.spawns.add(new Integer[] {1190, 615});
		this.spawns.add(new Integer[] {1450, 290});
		this.spawns.add(new Integer[] {50, 745});
		this.spawns.add(new Integer[] {565, 420});
		this.spawns.add(new Integer[] {250, 360});
		this.spawns.add(new Integer[] {900, 360});
		this.spawns.add(new Integer[] {435, 680});
		this.spawns.add(new Integer[] {325, 490});
	}

	public void run() {
		MapState.setChoixMap("res/carte.tmx");
		MapState.setSpawn(spawns);
		SoundManager.setMapCourante(1);
		this.menust.goToNextState();
	}
}
