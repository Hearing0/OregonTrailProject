package wagonLoadPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MenuUI {

	// Initialize variables 
	private JFrame frame;	
	String title= "";
	String desc = "";
	String prompt = "";
	private JTextField promptField;
	
	
	/**
	 * Create the application.
	 */
	public MenuUI( String title, String desc, String prompt) {
		this.title = title;
		this.desc = desc;
		this.prompt = prompt;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 418, 212);
		frame.getContentPane().add(tabbedPane);
		
		JLabel lblNewLabel = new JLabel("New label nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnjioh jijij");
		tabbedPane.addTab("New tab", null, lblNewLabel, null);
		
		promptField = new JTextField();
		promptField.setBounds(213, 234, 215, 20);
		frame.getContentPane().add(promptField);
		promptField.setColumns(10);
		
		JLabel promptLabel = new JLabel("Prompt:");
		promptLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		promptLabel.setBounds(10, 234, 193, 14);
		frame.getContentPane().add(promptLabel);
	}
}
