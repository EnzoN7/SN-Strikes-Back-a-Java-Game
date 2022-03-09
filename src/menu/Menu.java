package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;

import jeu.SoundManager;

/** Representation d'un menu */
public class Menu implements Iterable<MenuChoix> {
	private Map<Integer, MenuChoix> menu = new HashMap<>();
	private List<MenuChoix> menuList = new ArrayList<>();
	private int selected;
	private int defaultSelected;

	/**
	 * Ajout d'un choix dans le menu.
	 * 
	 * @param key         Raccourci associe au choix
	 * @param m           Représentation du choix a realiser
	 * @param setSelected Si mis a true, alors le choix sera selectionne
	 */
	public void addMenuChoix(int key, MenuChoix m, boolean setSelected) {
		this.menu.put(key, m);
		this.menuList.add(m);

		if (setSelected) {
			this.selected = this.menuList.size() - 1;
			this.defaultSelected = this.selected;
		}
	}

	/**
	 * Ajout d'un choix dans le menu.
	 * 
	 * @param key Raccourci associe au choix
	 * @param m   Représentation du choix a realiser
	 */
	public void addMenuChoix(int key, MenuChoix m) {
		this.addMenuChoix(key, m, false);
	}

	/**
	 * Si le raccourci est reference, alors execute le choix associe. Les raccourcis
	 * suivants sont réservés : KEY_DOWN, KEY_UP, KEY_ENTER
	 * 
	 * @param key Le raccourci a executer
	 */
	public void run(int key) {
		if (Input.KEY_DOWN == key) {
			this.selectNext();
		} else if (Input.KEY_UP == key) {
			this.selectPrevious();
		} else if (Input.KEY_ENTER == key) {
			SoundManager.playSonValidation();
			this.run();
		} else if (this.menu.containsKey(key)) {
			SoundManager.playSonValidation();
			this.resetValidMenuChoix();
			this.menu.get(key).run();
		}
	}

	/** Execute le choix selectionne. */
	public void run() {
		this.resetValidMenuChoix();
		this.menuList.get(selected).run();
	}
	
	/** Rend tous les menu valide */
	public void resetValidMenuChoix() {
		for (MenuChoix mc : this) {
			mc.setIsValid(true);
		}
	}

	/** Selectionne le choix precedent dans le menu. */
	public void selectPrevious() {
		if (this.selected > 0) {
			this.selected = Math.max(this.selected - 1, 0);
			SoundManager.playSonNavigation();
		}
	}

	/** Selectionne le choix suivant dans le menu. */
	public void selectNext() {
		if (this.selected < menuList.size() - 1) {
			this.selected = Math.min(this.selected + 1, menuList.size() - 1);
			SoundManager.playSonNavigation();
		}
	}

	/** Indique si le choix en paramètre est le choix selectionne du menu courant.
	 *  @param m
	 */
	public boolean isSelected(MenuChoix m) {
		return menuList.get(selected) == m;
	}

	@Override
	public Iterator<MenuChoix> iterator() {
		return menuList.iterator();
	}

	public static void drawMenu(Menu menu, String titre, GameContainer container, Graphics g) {
		int w = (int) (((container.getWidth() - g.getFont().getWidth(titre)) / 2) * 1.65f);
		int h = (int) (((container.getHeight() - g.getFont().getHeight(titre)) / 2) * 0.8f);

		g.getFont().drawString(w, h, titre, Color.yellow);

		int i = 1;

		for (MenuChoix menuc : menu) {
			w = (int) (container.getWidth() * 0.75);
			h = ((container.getHeight() / 2 - 90 + 25 * i));
			
			if (!menuc.getIsValid()) {
				g.getFont().drawString(w, h, menuc.toString(), menu.isSelected(menuc) ? new Color(255, 100, 100) : Color.red);
			} else if (menuc.getIsChoosen()) {
				g.getFont().drawString(w, h, menuc.toString(), menu.isSelected(menuc) ? new Color(100, 100, 255) : Color.blue);
			} else {
				g.getFont().drawString(w, h, menuc.toString(), menu.isSelected(menuc) ? Color.yellow : Color.white);
			}
			
			i++;
		}
	}

	/** Remet l'element selectionne par defaut */
	public void resetSelected() {
		this.selected = this.defaultSelected;
	}
}
