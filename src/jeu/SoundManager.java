package jeu;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.Graphics;

/** Classe gestionnaire de la musique et des bruitages de l'application. */
public class SoundManager {
	
	private static Music musiqueAccueil;
	private static Music musiqueMap1;
	private static Music musiqueMap2;
	private static Music musiqueEasterEgg;
	private static Music musiqueCourante;
	private static Music mapCourante;
	private static Music musiqueVictoire;
	private static Sound sonValidation;
	private static Sound sonNavigation;
	private static Sound sonTir;
	private static Sound sonMort;
	private static float volume = 0.5f;
	private static final float volumeSon = 0.5f;
	private static Image volumeOn;
	private static Image volumeOff;

	static {
		try {
			musiqueAccueil = new Music("res/sounds/mainMenuMusic.ogg");
			musiqueMap1 = new Music("res/sounds/caverneAntique.ogg");
			musiqueMap2 = new Music("res/sounds/grotteOubliee.ogg");
			musiqueVictoire = new Music("res/sounds/victory.ogg");
			musiqueEasterEgg = new Music("res/sounds/easterEgg.ogg");
			sonValidation = new Sound("res/sounds/buttonSound.ogg");
			sonNavigation = new Sound("res/sounds/navigationSound.ogg");
			sonTir = new Sound("res/sounds/shot.ogg");
			sonMort = new Sound("res/sounds/death.ogg");
			volumeOn = new Image("res/autre/volume.png").getScaledCopy(0.1f);
			volumeOff = new Image("res/autre/mute.png").getScaledCopy(0.1f);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void setMapCourante(int numMap) {
		switch (numMap) {
			case 1: mapCourante = musiqueMap1; break;
			case 2: mapCourante = musiqueMap2; break;
		}
	}
	
	public static void stopAllMusics() {
		musiqueAccueil.stop();
		musiqueMap1.stop();
		musiqueMap2.stop();
	}
	
	public static void playMusiqueAccueil() {
		stopAllMusics();
		musiqueAccueil.loop(1, volume);
		musiqueCourante = musiqueAccueil;
	}

	public static void playMusiqueMap() {
		stopAllMusics();
		mapCourante.loop(1, volume);
		musiqueCourante = mapCourante;
	}
	
	public static void playMusiqueVictoire() {
		stopAllMusics();
		musiqueVictoire.loop(1, volume);
		musiqueCourante = musiqueVictoire;
	}

	public static void playMusiqueEasterEgg() {
		stopAllMusics();
		musiqueEasterEgg.loop(1, 1);
		musiqueCourante = musiqueEasterEgg;
	}
	
	public static void stopMusique() {
		musiqueCourante.stop();
	}

	public static void pauseMusique() {
		musiqueCourante.pause();
	}
	
	public static void resumeMusique() {
		musiqueCourante.resume();
	}
	
	public static void playSonValidation() {
		sonValidation.play();
	}
	
	public static void playSonNavigation() {
		sonNavigation.play();
	}
	
	public static void playSonTir() {
		sonTir.play(1, volumeSon);
	}
	
	public static void playSonMort() {
		sonMort.play(1, volumeSon);
	}
	
	public static void renderMusicIcon(GameContainer container, Graphics g) {
		Image img = volume == 0 ? volumeOff : volumeOn;
		float offset = 10;
		
		g.drawImage(img, container.getWidth() - img.getWidth() - offset, offset);
	}
	
	public static void switchMusicMuted() {
		volume = volume == 0 ? 0.5f : 0;
		
		if (musiqueCourante != null)
			musiqueCourante.setVolume(volume);
	}
	
	public static boolean sonValidationPlaying() {
		return sonValidation.playing();
	}
	
	public static boolean sonTirPlaying() {
		return sonTir.playing();
	}
	
	public static boolean sonMortPlaying() {
		return sonMort.playing();
	}
	
	public static boolean musiqueVictoirePlaying() {
		return musiqueVictoire.playing();
	}

}
