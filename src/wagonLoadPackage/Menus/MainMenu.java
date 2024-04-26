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

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Art Label & Border
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
		
		JButton btnStartGame = new JButton("New Game");
		btnStartGame.setBounds(0, 0, 150, 23);
		panel.add(btnStartGame);
		
		JButton btnContinueGame = new JButton("Continue Game");
		btnContinueGame.setBounds(0, 34, 150, 23);
		panel.add(btnContinueGame);
		
		
		// Settings Button
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(0, 68, 150, 23);
		panel.add(btnSettings);
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		// Continue Game Button
		btnContinueGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// Start Game Button
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					WagonLoad frame = new WagonLoad();
					frame.setVisible(true);
				} catch (Exception evt) {
					evt.printStackTrace();
				}
			}
		});
		
		JLabel lblTitle = new JLabel("The Oregon Trail");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 45, 150, 23);
		contentPane.add(lblTitle);
	}
}
