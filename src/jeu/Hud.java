package jeu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public class Hud {

	// Variable image
	private Image playerbars;
	private Image affichage_equipe;

	// Position de la barre de vie et de temps
	private static final int P_BAR_X = 20;
	private static final int P_BAR_Y = 40;
	private static final int BAR_X = P_BAR_X;
	private static final int LIFE_BAR_Y = P_BAR_Y + 4;
	private static final int TIME_BAR_Y = 24 + P_BAR_Y;

	// Taille de la barre de vie et de temps
	private static final int BAR_WIDTH = 60;
	private static final int BAR_HEIGHT = 13;

	// Couleur de la barre de vie et de temps
	private static final Color LIFE_COLOR = new Color(255, 0, 0);
	private static final Color TIME_COLOR = new Color(0, 255, 0);

	public void init() throws SlickException {
		this.playerbars = new Image("res/autre/Hudv3.png");
		this.affichage_equipe = new Image("res/autre/Affichage_equipe.png").getScaledCopy(68, 68);
	}

	public void render(Graphics g, float vie, float temps) {
		g.resetTransform();
		g.setColor(LIFE_COLOR);
		g.fillRect(BAR_X, LIFE_BAR_Y, (vie / 100) * BAR_WIDTH, BAR_HEIGHT);
		g.setColor(TIME_COLOR);
		g.fillRect(BAR_X, TIME_BAR_Y, (temps / 10) * BAR_WIDTH, BAR_HEIGHT);
		g.drawImage(this.playerbars, P_BAR_X - 10, P_BAR_Y);
		g.drawImage(this.affichage_equipe, 10, 100);
	}
}
