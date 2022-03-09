package equipe;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import armes.SpecialisationID;

/** Classe regroupant les informations relatives à une equipe. */
public abstract class InfosEquipe {

	private String nom;
	private Color couleur;
	private SpecialisationID specialisation;

	// Construire animation
	SpriteSheet sheetSprite[]; // gauche / droite
	private Integer[] width; // largeur spriteSheet / largeur sprite
	private Integer[] idle;
	private Integer[] mvm;
	
	private Image tombe;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public SpecialisationID getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(SpecialisationID specialisation) {
		this.specialisation = specialisation;
	}

	public SpriteSheet[] getSheetSprite() {
		return sheetSprite;
	}

	public void setSheetSprite(SpriteSheet[] sprite) {
		this.sheetSprite = sprite;
	}

	public Integer[] getWidth() {
		return width;
	}

	public void setWidth(Integer[] wei) {
		this.width = wei;
	}

	public Integer[] getIdle() {
		return idle;
	}

	public void setIdle(Integer[] idle) {
		this.idle = idle;
	}

	public Integer[] getMvm() {
		return mvm;
	}

	public void setMvm(Integer[] mvm) {
		this.mvm = mvm;
	}
	
	public Image getTombe() {
		return this.tombe;
	}

	public void setTombe(Image tombe) {
		this.tombe = tombe;
	}

}
