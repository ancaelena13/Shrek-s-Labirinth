package game;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Graphics.AssetManager;
import Menu.MenuListener;
import Menu.MenuPanel;
import Sounds.SoundPlayer;

/**
 * Main class
 */
public class Main implements MenuListener {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 480;


	public static final SoundPlayer soundPlayer = new SoundPlayer();

	private JFrame frame;
	private MenuPanel menuPanel;
	private GamePanel gamePanel;
	private JPanel container;
	private CardLayout cardLayout;

	/**
	 * Constructor
	 */
	public Main() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AssetManager.load();

		gamePanel = new GamePanel();
		menuPanel = new MenuPanel(this);

		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		container.add(gamePanel, "game");
		container.add(menuPanel, "Menu");
		cardLayout.show(container, "Menu");

		frame.add(container);
		frame.pack();

		frame.setVisible(true);
	}

	/**
	 * Functia MAIN
	 */
	public static void main(String args[]) {
		new Main();
	}

	@Override
	public void startGame() {
		cardLayout.show(container, "game");
		gamePanel.requestFocus();
		gamePanel.startGame();

	}
}
