package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Items.DIRECTION;
import Items.Hero;
import Graphics.AssetManager;
import Map.Map;
import Map.MapLoader;

/**
 * Defineste logica jocului
 *
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private static final Font font = new Font("Arial", Font.BOLD, 20);

	private Timer timer;
	private Map map;
	private Hero hero;
	private int level = 1;

	/// Timpul
	private int minutes, seconds;
	private float counter;
	
	/**
	 * Constructor
	 */
	public GamePanel() {
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		setLayout(null);

		addKeyListener(this);
		setFocusable(true);
		requestFocus();

		timer = new Timer(16, this);
		
	}

	public void startGame() {
		map = MapLoader.loadMap("/Level 1 matrix.txt");
		hero = map.getHero();
		///1 minut pentru nivelul 1
		seconds = 60;

		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		map.update(0.016f);

		/// Update la timp
		counter += 0.016f;
		if (counter >= 1f) {
			seconds--;
			counter = 0;

			if (seconds <= 0) {
				seconds = 60;
				minutes--;

				///Afisez un mesaj in cazul in care nu s-a iesit la timp din labirint
				if (minutes < 0)
					endGame("TIMPUL S-A SCURS! AI PIERDUT!");
			}
			/// Ticaitul din ultimele 10 secunde
			if(seconds <= 10 && minutes == 0)
				Main.soundPlayer.playSoundEffect(AssetManager.getAudioFile("tick"));
		}

		repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		map.render(g2);

		g2.setFont(font);

		/// Afisez nivelul curent
		g2.setColor(Color.DARK_GRAY);
		g2.drawString("Nivel: " + level, 360, font.getSize());

		/// Afisez numarul de diamante colectate
		g2.setColor(Color.BLACK);
		g2.drawString("Diamante: " + hero.getNumDiamonds(), 360, font.getSize() * 2);

		/// Afisez timpul ramas
		String timeStr = String.format("%d%d:%d%d", minutes / 10, minutes % 10, seconds / 10, seconds % 10);
		g2.drawString(timeStr, 200, font.getSize());
	}

	private void move(DIRECTION dir) {
		int heroX = hero.getBounds().x;
		int heroY = hero.getBounds().y;

		if (map.isSolidTile(heroX + dir.getDx(), heroY + dir.getDy()))
			return;

		/// Trecem la nivelul urmator
		if (map.isExitAt(heroX + dir.getDx(), heroY + dir.getDy())) {
			nextLevel();
			return;
		}

		hero.move(dir);

	}

	private void nextLevel() {
		level++;

		/// Update la harta
		if (level == 2) {
			map = MapLoader.loadMap("/Level 2 matrix.txt");
			///5 minute pentru nivelul 2
			minutes = 5;
			seconds = 0;

		} else if (level == 3) {
			map = MapLoader.loadMap("/Level 3 matrix.txt");
			///15 minute pentru nivelul 3
			minutes = 15;
			seconds = 0;

		} else {
			///Afisez un mesaj la finalul jocului in functie de numarul de
			/// diamante colectate ( >=13 jucatorul a castigat, <13 jucatorul a pierdut)
			boolean won = hero.getNumDiamonds() >= 1;
			endGame(won ? "FELICITARI! AI CASTIGAT!" : "NU AI DESTULE DIAMANTE! AI PIERDUT!");
		}

		/// Update jucator
		Hero newHero = map.getHero();
		hero.setLocation(newHero.getX(), newHero.getY());
		hero.setBounds(newHero.getBounds());
		map.setHero(hero);

	}

	private void endGame(String message) {
		JOptionPane.showMessageDialog(this, message);
		System.exit(0);
	}

	@Override
	///Tastele pentru miscarea eroului pe harta
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			move(DIRECTION.UP);
			break;
		case KeyEvent.VK_DOWN:
			move(DIRECTION.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
			move(DIRECTION.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			move(DIRECTION.LEFT);
			break;

		case KeyEvent.VK_SPACE:
			nextLevel();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
