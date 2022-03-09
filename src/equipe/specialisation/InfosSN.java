package equipe.specialisation;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import equipe.InfosEquipe;

public class InfosSN extends InfosEquipe {

	public InfosSN() throws SlickException {
		super.setNom("SN");
		super.setCouleur(Color.cyan);

		try {
			Image sprites = new Image("res/sprites/spritesRobot.png");
			
			super.setWidth(new Integer[] { sprites.getWidth(), 63 });
			super.setSheetSprite(new SpriteSheet[] { 
				new SpriteSheet(sprites, super.getWidth()[1], 62),
				new SpriteSheet(sprites.getFlippedCopy(true, false), super.getWidth()[1], 62)
			});
		} catch (SlickException e) {
			e.printStackTrace();
		}

		super.setIdle(new Integer[] { 0, 9, 0 });
		super.setMvm(new Integer[] { 10, 17, 0 });
		
		SpriteSheet spriteTombe = new SpriteSheet("res/autre/tombe.png", 32, 32);
        setTombe(spriteTombe.getSubImage(2, 1));
	}
}
