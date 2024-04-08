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
	private JTextField promptField;
	Location location;
	private JLabel lblNewLabel;
	
	
	/**
	 * Create the application.
	 */
	public MenuUI(Location location) {
		this.location = location;
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
		
		// Description Label
		lblNewLabel = new JLabel(location.getDesc());
		tabbedPane.addTab("Description", null, lblNewLabel, null);
		
		// Prompt Field
		promptField = new JTextField();
		promptField.setBounds(213, 234, 215, 20);
		frame.getContentPane().add(promptField);
		promptField.setColumns(10);
		
		// Prompt Label
		JLabel promptLabel = new JLabel(location.getPrompt());
		promptLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		promptLabel.setBounds(10, 234, 193, 14);
		frame.getContentPane().add(promptLabel);
	}
	
	public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
