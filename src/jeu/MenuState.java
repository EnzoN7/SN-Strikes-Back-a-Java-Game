package jeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import menu.Menu;
import menu.MenuChoix;

/** Classe generalisant la notion de state, qui permet de creer les fenetres avec menu. */
public abstract class MenuState extends BasicGameState {
	protected GameContainer container;
	protected StateBasedGame game;
	protected Menu menu;
	protected Image background;
	
	private static final String JUL = "jul";
	private int cptJul = 0;
	
	/** Initialize the state. It should load any resources it needs at this stage
	 *	@param container The container holding the game
	 *	@param game The game holding this state
	 *	@throws SlickException Indicates a failure to initialize a resource for this state
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		this.game = game;
		this.menu = new Menu();
		this.background = this.getBackground();
		initMenu();
	}

	/** Render this state to the game's graphics context
	 *  @param container The container holding the game
	 *  @param game The game holding this state
	 *  @param g The graphics context to render to
	 *  @throws SlickException Indicates a failure to render an artifact
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());
		Menu.drawMenu(menu, "", container, g);
		SoundManager.renderMusicIcon(container, g);
		drawMessage(g);
	}

	/** Update the state's logic based on the amount of time thats passed
	 *  @param container The container holding the game
	 *	@param game The game holding this state
	 *  @param delta The amount of time thats passed in millisecond since last update
	 *  @throws SlickException Indicates an internal error that will be reported through the standard framework mechanism
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException { }

	/** Initialise le menu */
	protected abstract void initMenu() throws SlickException;
	
	/** Renvoie l'image de fond de la fenetre
	 * @return L'image de fond de la fenetre
	 */
	protected abstract Image getBackground();
	
	/** Renvoie l'ID de la fenetre suivante
	 * @return L'ID de la fenetre suivante
	 */
	protected abstract int getNextState();

	/** Renvoie l'ID de la fenetre precedente
	 * @return L'ID de la fenetre precedente
	 */
	protected abstract int getPrevState();
	
	/** Reset le menu */
	private void resetMenu() {
		for (MenuChoix mc : this.menu) {
			mc.reset();
		}
		
		this.menu.resetSelected();
	}
	
	/** Ecrire un texte supplémentaire. */
	protected void drawMessage(Graphics g) {
		
	}
	
	/** Lance la fenetre suivante */
	public void goToNextState() {
		resetMenu();
		this.game.enterState(this.getNextState());
	}
	
	/** Lance la fenetre precedente */
	public void goToPrevState() {
		resetMenu();
		this.game.enterState(this.getPrevState());
	}

	/** Notification that a key was pressed
	 *	@param key The key code that was pressed
	 *	@param c The character of the key that was pressed
	 */
	@Override
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_M)
			SoundManager.switchMusicMuted();
		else
			this.menu.run(key);
		
		this.easterEgg(c);
	}
	
	public void easterEgg(char c) {
		if (JUL.charAt(cptJul) == c) {
			cptJul++;
		} else {
			cptJul = 0;
		}
		
		if (cptJul == JUL.length()) {
			SoundManager.playMusiqueEasterEgg();
			cptJul = 0;
		}
	}
}
