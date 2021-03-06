
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A swing menu with a generic layout.
 * 
 * @author Joachim Antfolk
 * @version 2020-02-08
 * @param size : the size of the menu
 * @param elements : the number of elements to be added to the menu   
 */
public class Menu {

	private final JFrame menuFrame;
	private final JPanel contentPane;
	private final JPanel menuPane;
	
	/**
	 * Creates a generic menu
	 */
	public Menu() {
		this(new Dimension(200, 200), 1);
	}
	
	/**
	 * Creates a menu according to user specifications 
	 */
	public Menu(Dimension size, int elements) {
		this.menuFrame = new JFrame();
		this.contentPane = (JPanel)menuFrame.getContentPane();
		this.menuPane = new JPanel();

		menuSetup(size, elements);
	}
	
	/**
	 * Applies a layout to the menu according to the callers size and element requirenments
	 * @param dimension : the size of the menu
	 * @param elements : the number of elements to be added to the menu
	 */
	private void menuSetup(Dimension size, int elements) {
		this.menuPane.setLayout(new GridLayout(elements,1,1,1));
		this.menuPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.contentPane.setBorder(new EmptyBorder(20,20,20,20));
		this.contentPane.add(menuPane);
		this.menuFrame.setSize(size);
		this.menuFrame.setResizable(false);	
	}
	
	/**
	 * Adds an element to the menu
	 * @param element : element to be added to the menu
	 */
	public void addElement(Container element) {
		menuPane.add(element);
	}
	
	/**
	 * Adds an image as a title
	 * @param fileName : directory of the image  to used
	 */
	public void addTitle(String fileName) {
		BufferedImage image;
		JLabel title;
		try {
			image = ImageIO.read(new File(fileName));
			title = new JLabel(new ImageIcon(image));
			this.menuPane.add(title, 0);
		} catch (IOException e) {
			System.out.println("ERROR: File not found!");// placeholder
		}
	}
	
	/**
	 * Opens the menu
	 */
	public void openMenu() {
		this.menuFrame.setVisible(true);
	}
	
	/**
	 * Closes the menu
	 */
	public void closeMenu() {
		this.menuFrame.setVisible(false);
	}	
}
