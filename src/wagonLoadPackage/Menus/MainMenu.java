package wagonLoadPackage.Menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import wagonLoadPackage.WagonLoad;


/**
 * MainMenu.java 
 * Created on 4/27/2024
 * By David Flores
 * 
 * Creates a new UI Frame for the Main Menu UI. 
 * Displays splash art.
 * Allows user to select between Start, Continue, and Settings.
 */
public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static MainMenu mainFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame = new MainMenu();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Art Label & Border - David Flores
        JPanel artPanel = new JPanel();
        artPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chimmey Rock, John Estel CC NY-BY", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        artPanel.setBounds(170, 0, 270, 152);
        contentPane.add(artPanel);
        artPanel.setLayout(null);
        
        JLabel artLabel = new JLabel();
        artLabel.setBounds(6, 16, 256, 128);
        artPanel.add(artLabel);
        artLabel.setIcon(new ImageIcon(WagonLoad.class.getResource("/images/ChimneyRock1.pixel.WebSafe.2.png")));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 136, 150, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		/// Buttons
		
		// Start Game Button - David Flores
		JButton btnStartGame = new JButton("New Game");
		btnStartGame.setBounds(0, 0, 150, 23);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreLoadingMenu frame = new PreLoadingMenu();
					frame.setVisible(true);
					mainFrame.dispose();					
				} catch (Exception evt) {
					evt.printStackTrace();
				}
			}
		});
		panel.add(btnStartGame);
		
		
		// Continue Game Button
		JButton btnContinueGame = new JButton("Continue Game");
		btnContinueGame.setBounds(0, 34, 150, 23);
		btnContinueGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel.add(btnContinueGame);
		
		
		// Settings Button
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(0, 68, 150, 23);
		panel.add(btnSettings);
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
				
		JLabel lblTitle = new JLabel("The Oregon Trail");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 45, 150, 23);
		contentPane.add(lblTitle);
	}
}
