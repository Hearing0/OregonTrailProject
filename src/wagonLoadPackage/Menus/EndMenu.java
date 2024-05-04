package wagonLoadPackage.Menus;

import java.awt.EventQueue;
import wagonLoadPackage.Menus.MainMenu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EndMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	public JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndMenu window = new EndMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EndMenu() {
		initialize();
		frame.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Game Over!");
		frame.getContentPane().setLayout(null);
		
		JLabel deathTextLabel = new JLabel("You Died!");
		deathTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		deathTextLabel.setFont(new Font("Vineta BT", Font.PLAIN, 26));
		deathTextLabel.setBounds(0, 26, 444, 43);
		frame.getContentPane().add(deathTextLabel);
		
		JLabel lblNewLabel = new JLabel("What would you like to do?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Vineta BT", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 68, 444, 52);
		frame.getContentPane().add(lblNewLabel);
		
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mainFrame = new MainMenu();
				mainFrame.setVisible(true);
			}
		});
		restartButton.setFont(new Font("Rockwell", Font.PLAIN, 14));
		restartButton.setBounds(90, 158, 100, 30);
		frame.getContentPane().add(restartButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setFont(new Font("Rockwell", Font.PLAIN, 14));
		quitButton.setForeground(new Color(0, 0, 0));
		quitButton.setBounds(241, 158, 100, 30);
		frame.getContentPane().add(quitButton);
	}
}
