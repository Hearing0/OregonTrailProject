package wagonLoadPackage;

import javax.swing.*;


public class Menu {

	
	private JFrame frame;
	private int width;
	private int height;
	
	String title= "";
	String desc = "";
	String prompt = "";
	
	
	
	/**
	 * Initialize Menu
	 * @param title
	 * @param desc
	 * @param prompt
	 */
	public Menu( String title, String desc, String prompt, int w, int h) {
		this.title = title;
		this.desc = desc;
		this.prompt = prompt;
		this.width = w;
		this.height = h;
	}
	
	
	/**
	 * Creates a popup menu with the following details
	 */
	public void setupMenu() {
		frame.setSize(width, height);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	

}
