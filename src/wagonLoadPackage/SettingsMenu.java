package wagonLoadPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;

public class SettingsMenu extends JFrame {

	
	Sound sound = new Sound();
	JSlider slider;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsMenu frame = new SettingsMenu();
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
	public SettingsMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New game");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(151, 208, 114, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Settings Menu");
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(74, 11, 263, 43);
		contentPane.add(lblNewLabel);
		
		JSlider slider = new JSlider();
		slider.setBounds(106, 91, 200, 26);
		contentPane.add(slider);
		
		JSlider slider_1 = new JSlider();
		slider_1.setBounds(106, 157, 200, 26);
		contentPane.add(slider_1);
		
		JLabel VolumeLabel = new JLabel("Volume");
		VolumeLabel.setBounds(189, 65, 76, 14);
		contentPane.add(VolumeLabel);
		
		JLabel BrightnessLabel = new JLabel("Brightness");
		BrightnessLabel.setBounds(181, 132, 76, 14);
		contentPane.add(BrightnessLabel);
	}
}
