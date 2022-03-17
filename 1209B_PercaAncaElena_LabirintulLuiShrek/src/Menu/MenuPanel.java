package Menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.Main;
import Graphics.AssetManager;

/**
 *	Meniul jocului

 */
public class MenuPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private BufferedImage backgroundImage,menuImage,povesteImage,reguliImage;
	private Button startButton, povesteButton,reguliButton,exitButton,menuButton;
	private MenuListener menuListener;

	public MenuPanel(MenuListener menuListener) {
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		this.menuListener = menuListener;
		this.menuImage = AssetManager.getImage("Menu");
		this.povesteImage = AssetManager.getImage("reguli");
		this.reguliImage = AssetManager.getImage("poveste");
		backgroundImage = menuImage;

		startButton = new Button("/start.png", 0.5f);
		povesteButton = new Button("/poveste.png", 0.5f);
		reguliButton = new Button("/reguli.png", 0.5f);
		exitButton = new Button("/lesire.png", 0.5f);
		menuButton = new Button("/main_menu.png","/main_menu_hover.png",0.5f);
		menuButton.setVisible(false);

		setLayout(null);
		startButton.setPosition(130, 220);
		povesteButton.setPosition(125, 280);
		reguliButton.setPosition(125, 340);
		exitButton.setPosition(125, 400);
		menuButton.setPosition(165, 440);

		add(startButton);
		add(povesteButton);
		add(reguliButton);
		add(menuButton);
		add(exitButton);

		startButton.addActionListener(this);
		povesteButton.addActionListener(this);
		reguliButton.addActionListener(this);
		exitButton.addActionListener(this);
		menuButton.addActionListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, Main.WIDTH, Main.HEIGHT,null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton)
			menuListener.startGame();
		else if(e.getSource() == povesteButton) {
			backgroundImage = povesteImage;
			hideAllButtons();
			menuButton.setVisible(true);
			repaint();
		}
		else if(e.getSource() == reguliButton){
			backgroundImage = reguliImage;
			hideAllButtons();
			menuButton.setVisible(true);
			repaint();
		}
		else if(e.getSource() == menuButton) {
			backgroundImage = menuImage;
			showAllButtons();
			menuButton.setVisible(false);
			repaint();
		}
		else
			System.exit(0);
	}

	
	private void hideAllButtons() {
		startButton.setVisible(false);
		povesteButton.setVisible(false);
		reguliButton.setVisible(false);
		exitButton.setVisible(false);
		menuButton.setVisible(false);
	}
	private void showAllButtons() {
		startButton.setVisible(true);
		povesteButton.setVisible(true);
		reguliButton.setVisible(true);
		exitButton.setVisible(true);
		menuButton.setVisible(true);
	}
}
