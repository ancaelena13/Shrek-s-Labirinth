package Menu;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Loader.ImageLoader;

/**
 * Button Class
 */
public class Button extends JLabel implements MouseListener {

	private ArrayList<ActionListener> actionListeners;
	private ImageIcon buttonImage, buttonHoverImage;

	/**
	 * Constructor
	 */
	public Button(String imagePath, float scale) {
		actionListeners = new ArrayList<ActionListener>();

		/// Scalarea imaginilor
		BufferedImage image = ImageLoader.loadImage(imagePath);
		Image iconImage =  image.getScaledInstance((int) (image.getWidth() * scale),
				(int) (image.getHeight() * scale), Image.SCALE_SMOOTH);

		/// Imagine butoane
		buttonImage = new ImageIcon(iconImage);
		setIcon(buttonImage);

		this.addMouseListener(this);

	}

	/**
	 * Constructor
	 */
	public Button(String imagePath, String hoverImagePath, float scale) {
		this(imagePath,scale);

		BufferedImage image = ImageLoader.loadImage(hoverImagePath);
		Image iconImage =  image.getScaledInstance((int) (image.getWidth() * scale),
				(int) (image.getHeight() * scale), Image.SCALE_SMOOTH);

		buttonHoverImage = new ImageIcon(iconImage);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!actionListeners.isEmpty())
			for (ActionListener actionListener : actionListeners)
				actionListener.actionPerformed(new ActionEvent(this, 0, "button_clicked"));

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (buttonHoverImage != null)
			setIcon(buttonHoverImage);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		setIcon(buttonImage);

	}

	///SETTERS si GETTERS

	public void setPosition(int x, int y) {
		setBounds(x, y, buttonImage.getIconWidth(), buttonImage.getIconHeight());
	}

	public void addActionListener(ActionListener actionListener) {
		actionListeners.add(actionListener);
	}

	public void setButtonImage(ImageIcon buttonImage) {
		this.buttonImage = buttonImage;
		setIcon(buttonImage);
	}

	public ImageIcon getButtonImage() {
		return buttonImage;
	}

}
