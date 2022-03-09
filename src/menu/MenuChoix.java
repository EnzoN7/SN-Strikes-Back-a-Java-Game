package menu;

import org.newdawn.slick.Input;

/** Représentation d'un choix dans un menu. */
public abstract class MenuChoix {
	
	private int key;
	private String desc;
	private boolean isChoosen;
	private boolean isValid;
	
	/** Représentation d'un choix dans un menu.
	 * @param key Le raccourci associé au choix
	 * @param desc La description du choix
	 */
	public MenuChoix(int key, String desc) {
		this.key = key;
		this.desc = desc;
		this.reset();
	}
	
	/** Execute l'action qui doit etre realise */
	public abstract void run();
	
	public void reset() {
		this.isChoosen = false;
		this.isValid = true;
	}
	
	@Override
	public String toString() {
		return "[" + Input.getKeyName(key) + "] " + desc; 
	}

	public boolean getIsChoosen() {
		return isChoosen;
	}

	public void setIsChoosen(boolean isChoosen) {
		this.isChoosen = isChoosen;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isPossible) {
		this.isValid = isPossible;
	}
}
