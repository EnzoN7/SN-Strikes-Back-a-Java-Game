package equipe.specialisation;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import equipe.InfosEquipe;

public class Infos3EA extends InfosEquipe {

	public Infos3EA() throws SlickException {
		super.setNom("3EA");
		super.setCouleur(Color.yellow);

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

		super.setIdle(new Integer[] { 7, 8, 76 });
		super.setMvm(new Integer[] { 0, 4, 76 });
		
		SpriteSheet spriteTombe = new SpriteSheet("res/autre/tombe.png", 32, 32);
        setTombe(spriteTombe.getSubImage(2, 0));
	}
}
