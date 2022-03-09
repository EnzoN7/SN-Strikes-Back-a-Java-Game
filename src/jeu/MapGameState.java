package jeu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalTime;
import java.time.Duration;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import armes.MunitionVideException;
import armes.Projectile;
import equipe.Equipe;
import equipe.Personnage;
import physique.Physique;

import org.newdawn.slick.Image;

/** Classe responsable de la fen�tre de jeu. */
public class MapGameState extends BasicGameState {

	public static final int ID = 667;

	/** Contenant de la fenêtre. */
	private GameContainer container;
	/** Contenu de la fenetre. */
	private TiledMap map;
	private StateBasedGame game;

	/** physique */
	private Physique physique;

	/** Dernière direction indiquée au jeu. */
	private Direction direction = Direction.GAUCHE;
	/** Définir si le joueur courant de l'équipe courante est en mouvement. */
	private boolean isMoving = false;

	/** Ensemble des équipes. */
	private static ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	/** Numéro de l'équipe courante dans l'ensemble des équipes du jeu. */
	private int equipeCourante = 0;
	private int nbJoueurs = 0;
	private int nbEquipes = 0;
	/** Le joueur a-t'il deja tire pendant son tour. */
	private boolean aTire = false; 

	/** Mesure le temps laissé à chaque joueur pour faire ses actions. */
	private static Timer timer = new Timer();
	/** Instant marquant le début du tour courant. */
	private LocalTime tempsDebutTour;
	/** Temps maximal du tour d'un joueur (en secondes). */
	private long tempsTour = 10;
	/** temps d'un joueur apres avoir tire. */
	private long tempsTire = 5;
	/**
	 * Temps restant avant la fin du tour si le jeu vient d'être mis en pause. Vaut
	 * -1 sinon.
	 */	
	private static long tempsRestantPause = -1;
	private Sound sonSaut;
	private Sound sonPas;

	/* Hud du jeu */
	private Hud hud = new Hud();

	/* Projectile tir� par un personnage */
	private Projectile projectileTire = null;

	/** Initialize the state. It should load any resources it needs at this stage
	 *	@param container The container holding the game
	 *	@param game The game holding this state
	 *	@throws SlickException Indicates a failure to initialize a resource for this state
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		nbJoueurs = PlayersState.getNbJoueurs();
		nbEquipes = TeamsState.getNbEquipes();

		// Création de la fenêtre de jeu et du terrain
		this.container = container;
		this.game = game;
		container.setTargetFrameRate(120);

		// On initialise une partie apres avoir selectionne un nombre de joueurs par
		// equipe
		// (et donc un nombre d'equipe).
		if (nbJoueurs != 0) {
			equipeCourante = 0;
			this.sonSaut = new Sound("res/sounds/jump.ogg");
			this.sonPas = new Sound("res/sounds/walk.ogg");
			this.map = new TiledMap(MapState.getChoixMap());
			PlayersState.setFinChoix(false);

			SoundManager.playMusiqueMap();

			// Création des équipes.
			for (int i = 0; i < nbEquipes; i++) {
				ajouterEquipe(nbJoueurs, MapState.getSpawn(), i);
			}

			// Initialisation du timer
			timer = new Timer();
			timer.scheduleAtFixedRate(new FinTimer(), this.tempsTour * 1000, 1);
			this.tempsDebutTour = LocalTime.now();

			// Creation de la physique du jeu
			this.physique = new Physique(0.0001f, 32, 32); // map.getHeight() et getWidth ne renvoient pas la bonne
															// valeur à l'initialisation ?

			this.hud.init();
		}
	}
	
	/** Update the state's logic based on the amount of time thats passed
	 *  @param container The container holding the game
	 *	@param game The game holding this state
	 *  @param delta The amount of time thats passed in millisecond since last update
	 *  @throws SlickException Indicates an internal error that will be reported through the standard framework mechanism
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		// Si on sort du menu pause, remettre le timer
		if (tempsRestantPause != -1) {
			timer = new Timer();
			timer.scheduleAtFixedRate(new FinTimer(), tempsRestantPause, 1);
			this.tempsDebutTour = LocalTime.now()
					.minusNanos(this.tempsTour * 1000 * 1000 * 1000 - tempsRestantPause * 1000 * 1000);
			tempsRestantPause = -1;
		}

		// Déplacer le personnage
		if (this.isMoving) {
			Personnage joueurCourant = equipes.get(this.equipeCourante).getJoueurCourant();
			joueurCourant.setDirection(this.direction);
			this.physique.deplacement(this.map, delta, joueurCourant);
			if (this.physique.isGrounded(map, equipes.get(this.equipeCourante).getJoueurCourant())
					&& !this.sonPas.playing()) {
				this.sonPas.play(1, (float) 0.1);
			}
		}

		for (Equipe equ : equipes) {
			for (int i = 0; i < equ.getNbJoueurs(); i++) {
				Personnage perso = equ.getJoueur(i);
				this.physique.tomber(this.map, delta, perso);
			}
		}
		// Tant que l'on est dans les menus de s�lection, on actualise les donn�es
		// suivantes.
		if (nbJoueurs == 0)
			nbJoueurs = PlayersState.getNbJoueurs();
		if (nbEquipes == 0)
			nbEquipes = TeamsState.getNbEquipes();

		// Une fois le nombre de personnages par �quipe entr�e, on peut initialiser le
		// jeu.
		if (nbJoueurs != 0 && PlayersState.getFinChoix())
			init(container, game);

		if (projectileTire != null) {
			//System.out.println("projectile: x: " + this.projectileTire.getX() + " y: " + this.projectileTire.getY());
			if (physique.trajectoireProjectile(this.map, delta, equipes, this.projectileTire, this.equipeCourante)) { /* la cible est touchée */
				/* supprimer le projectile */
				projectileTire = null;
			}
		}
	}

	/** Render this state to the game's graphics context
	 *  @param container The container holding the game
	 *  @param game The game holding this state
	 *  @param g The graphics context to render to
	 *  @throws SlickException Indicates a failure to render an artifact
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// Affichage map
		this.map.render(0, 0);
		Color couleur = new Color(0, 0, 0, .5f);
		g.setColor(couleur);

		Image flecheJC = new Image("res/autre/fleche.png").getScaledCopy(30, 40);// (this.width, this.height);

		float tempsRestant = getTempsRestant();

		if(this.projectileTire != null) {
			g.setColor(Color.red);
			g.fillOval( this.projectileTire.getX(),  this.projectileTire.getY(),  10,  10,  40);
		}

		// Affichage joueurs
		for (Equipe equ : equipes) {
			for (int i = 0; i < equ.getNbJoueurs(); i++) {
				Personnage perso = equ.getJoueur(i);

				if (perso.isAlive()) {
					// Affichage PVs
					g.setColor(equ.getCouleur());
					// g.drawString(Integer.toString(perso.getVie(),perso.getX() - 15, perso.getY()
					// - 70);
					g.drawString(String.valueOf((int) perso.getVie()), perso.getX() - 20, perso.getY() - 70); // TODO : Remplacer
																										// par la ligne de
																										// dessus
	
					g.setColor(couleur);
					g.fillOval(perso.getX() - 16, perso.getY() - 8, 32, 16);
	
					// Affichage infos HUD
					this.hud.render(g, (float) perso.getVie(), tempsRestant / 1000);
					// Information Equipe courante
					g.setColor(equipes.get(this.equipeCourante).getCouleur());
					g.drawString(equipes.get(this.equipeCourante).getNom(), 32, 125);
	
					if (perso == equipes.get(this.equipeCourante).getJoueurCourant()) {
						g.drawImage(flecheJC, perso.getX() - 17, perso.getY() - 102); // TODO : changer la taille
	
						g.drawAnimation(
								perso.getAnimations()[(this.direction == Direction.GAUCHE ? 0 : 1) + (isMoving ? 4 : 0)],
								perso.getX() - 32, perso.getY() - 60);
					}
	
					else {
						g.drawAnimation(perso.getAnimations()[1], perso.getX() - 32, perso.getY() - 60);
					}
				}
				else {
					g.drawImage(equ.getTombe(), perso.getX() - 32, perso.getY() -32);
	            }
			}
		}
	}

	/*
	 * Action listener touches enfoncées.
	 */
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		// Déplacements côtés
		case Input.KEY_Q:
			this.direction = Direction.GAUCHE;
			this.isMoving = true;
			break;
		case Input.KEY_D:
			this.direction = Direction.DROITE;
			this.isMoving = true;
			break;
		// Saut simple
		case Input.KEY_SPACE:
		case Input.KEY_Z:
			Personnage joueurCourant = equipes.get(this.equipeCourante).getJoueurCourant();
			if (this.physique.sauter(map, joueurCourant)) {
				this.sonSaut.play(1, (float) 0.2);
				this.sonPas.stop();
			}
			break;
		// Changement d'�quipe
		case Input.KEY_E:
			changerJoueur();
			break;
		case Input.KEY_F:
			try {
				SoundManager.playSonValidation();
				container.setFullscreen(!container.isFullscreen());
			} catch (SlickException e) {
				e.printStackTrace();
			}
			break;
		case Input.KEY_M:
			SoundManager.switchMusicMuted();
			break;
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (button == Input.MOUSE_LEFT_BUTTON) {
			if (!this.aTire) {
				try {
					Personnage joueurCourant = equipes.get(this.equipeCourante).getJoueurCourant();
					this.projectileTire = joueurCourant.tirer(x, y);
					this.aTire = true;
					clearTimer();
					timer = new Timer();
					timer.scheduleAtFixedRate(new FinTimer(), this.tempsTire * 1000, 1);
					this.tempsDebutTour = LocalTime.now();
					// TODO gerer la position initiale du projectile
				} catch (MunitionVideException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Else button clicked");
		}
	}

	/*
	 * Ajouter une équipe au jeu.
	 */
	public void ajouterEquipe(int nbJoueurs, ArrayList<Integer[]> spawnStart, int numEquipe) throws SlickException {
		Equipe nouvelleEquipe = new Equipe(nbJoueurs, this.map.getTileHeight(), spawnStart,
				TeamsState.getEquipe(numEquipe), numEquipe);
		equipes.add(nouvelleEquipe);
	}

	/*
	 * Change le joueur courant de l'équipe courante. Le joueur courant devient le
	 * joueur courant de l'équipe suivante.
	 */
	public void changerJoueur() {
		clearTimer();
		this.aTire = false;
		int prevEq = this.equipeCourante;
		do {
			this.equipeCourante = ++this.equipeCourante % equipes.size();
			equipes.get(equipeCourante).changerJoueurCourant();
		} while (equipes.get(equipeCourante).isAneantie() && prevEq != equipeCourante);
		
		// Toutes les équipes sont mortes
		if (prevEq == equipeCourante) {
			EndGameState.setNomGagnant(equipes.get(equipeCourante).getNom());
			
			if (!SoundManager.sonValidationPlaying() && !SoundManager.musiqueVictoirePlaying()) {
				SoundManager.playSonValidation();
				SoundManager.playMusiqueVictoire();
			}
			
			tempsRestantPause = this.tempsTour;
			
			game.enterState(EndGameState.ID);
		}

		timer = new Timer();
		timer.scheduleAtFixedRate(new FinTimer(), this.tempsTour * 1000, 1);
		this.tempsDebutTour = LocalTime.now();
	}

	/*
	 * Vide la liste des équipes.
	 */
	public static void viderListeJoueurs() {
		equipes.clear();
	}

	/*
	 * Action listener touches relâchées.
	 */
	@Override
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_Q:
		case Input.KEY_D:
			this.isMoving = false;
			this.sonPas.stop();
			break;
		case Input.KEY_ESCAPE:
			SoundManager.playSonValidation();
			SoundManager.pauseMusique();
			tempsRestantPause = (long) Math.floor(getTempsRestant());
			timer.cancel();
			game.enterState(PauseState.ID);
			break;
		}
	}

	@Override
	public int getID() {
		return ID;
	}

	/*
	 * Supprimer un timer.
	 */
	public static void clearTimer() {
		timer.cancel();
		timer.purge();
		tempsRestantPause = -1;
	}

	/*
	 * Obtenir le temps restant avant la fin du tour courant.
	 */
	public float getTempsRestant() {
		if (tempsRestantPause != -1) {
			return (float) tempsRestantPause;
		} else if (!this.aTire) {
			Duration tempsEcoule = Duration.between(this.tempsDebutTour, LocalTime.now());
			return this.tempsTour * 1000 - tempsEcoule.toMillis();
		} else {
			Duration tempsEcoule = Duration.between(this.tempsDebutTour, LocalTime.now());
			return this.tempsTire * 1000 - tempsEcoule.toMillis();
		}
	}

	/*
	 * Classe dont la méthode run est exécutée si le timer arrive à son terme.
	 */
	class FinTimer extends TimerTask {
		@Override
		public void run() {
			changerJoueur();
		}
	}

}