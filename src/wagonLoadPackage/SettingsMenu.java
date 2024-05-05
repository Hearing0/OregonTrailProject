package wagonLoadPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import wagonLoadPackage.Menus.PreLoadingMenu;

import javax.swing.event.ChangeEvent;

/**
 * SettingsMenu.java
 * Created on 05/01/2024
 * By Rod Piton
 * 
 * Creates the UI for the settings menu.
 * Allows user to change volume and start a new game
 * uses slider to adjust volume
 * User can also mute volume
 */

public class SettingsMenu extends JFrame {

	Sound sound = new Sound();
	JSlider slider;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SettingsMenu(Sound sound, JFrame travelMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("New game");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame f1 = (JFrame) SwingUtilities.getWindowAncestor(contentPane);

				f1.dispose();

				travelMenu.dispose();

				PreLoadingMenu frame = new PreLoadingMenu();
				frame.setVisible(true);

			}
		});
		btnNewButton_1.setBounds(151, 208, 114, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Settings Menu");
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(74, 11, 263, 43);
		contentPane.add(lblNewLabel);

		JSlider slider = new JSlider(-40, 6);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				sound.currentVolume = slider.getValue();

				if (sound.currentVolume == -40) {
					sound.currentVolume = -80;
				}

				sound.fc.setValue(sound.currentVolume);
			}
		});
		slider.setBounds(106, 91, 200, 26);
		contentPane.add(slider);

		JLabel VolumeLabel = new JLabel("Volume");
		VolumeLabel.setBounds(189, 65, 76, 14);
		contentPane.add(VolumeLabel);

		JButton MuteBtn = new JButton("Mute");
		MuteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sound.volumeMute();

			}
		});
		MuteBtn.setBounds(164, 162, 89, 23);
		contentPane.add(MuteBtn);
	}
}
