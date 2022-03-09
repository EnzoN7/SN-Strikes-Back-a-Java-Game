package equipe.specialisation;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import equipe.InfosEquipe;

public class InfosMF2E extends InfosEquipe {

	public InfosMF2E() throws SlickException {
		super.setNom("MF2E");
		super.setCouleur(Color.green);

		try {
			Image sprites = new Image("res/sprites/redone_charset_antifarea.png");
			
			super.setWidth(new Integer[] { 3 * sprites.getWidth(), 54 });
			super.setSheetSprite(new SpriteSheet[] {
				new SpriteSheet(sprites.getScaledCopy(3 * sprites.getWidth(), 3 * sprites.getHeight()), super.getWidth()[1], 60),
				new SpriteSheet(sprites.getFlippedCopy(true, false).getScaledCopy(3 * sprites.getWidth(), 3 * sprites.getHeight()), super.getWidth()[1], 60)
			});
		} catch (SlickException e) {
			e.printStackTrace();
		}

		super.setIdle(new Integer[] { 19, 20, 70 });
		super.setMvm(new Integer[] { 12, 16, 70 });
		
		SpriteSheet spriteTombe = new SpriteSheet("res/autre/tombe.png", 32, 32);
        setTombe(spriteTombe.getSubImage(2, 0));
	}
}
