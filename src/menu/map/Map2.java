package menu.map;

import java.util.ArrayList;

import jeu.MapState;
import jeu.MenuState;
import jeu.SoundManager;
import menu.MenuChoix;

public class Map2 extends MenuChoix {
	
	private static String desc = "L'appartement d'un élève de MF2E";
	private MenuState menust;
	private ArrayList<Integer[]> spawns = new ArrayList<Integer[]>();
	
	public Map2(int key, MenuState menust) {
		super(key, desc);
		this.menust = menust;
		
		this.spawns.add(new Integer[] {195, 830});
		this.spawns.add(new Integer[] {1530, 675});
		this.spawns.add(new Integer[] {885, 710});
		this.spawns.add(new Integer[] {970, 345});
		this.spawns.add(new Integer[] {70, 200});
		this.spawns.add(new Integer[] {175, 165});
		this.spawns.add(new Integer[] {1270, 290});
		this.spawns.add(new Integer[] {590, 260});
		this.spawns.add(new Integer[] {1220, 740});
		this.spawns.add(new Integer[] {230, 575});
		this.spawns.add(new Integer[] {1515, 290});
		this.spawns.add(new Integer[] {800, 515});
		this.spawns.add(new Integer[] {145, 425});
		this.spawns.add(new Integer[] {520, 680});
		this.spawns.add(new Integer[] {730, 830});
		this.spawns.add(new Integer[] {1130, 580});
	}

	public void run() {
		MapState.setChoixMap("res/carte2.tmx");
		MapState.setSpawn(spawns);
		SoundManager.setMapCourante(2);
		this.menust.goToNextState();
	}

}
