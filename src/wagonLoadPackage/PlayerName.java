package wagonLoadPackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import wagonLoadPackage.Wagon;

public class PlayerName {
	
	Health health = new Health(null, 0, null, false);
	private JFrame frame;
	private JTextField member1TextField;
	private JTextField member2TextField;
	private JTextField member3TextField;
	private JTextField member4TextField;
	Wagon wagon;
	
	public PlayerName(Wagon wagon) {
		this.wagon = wagon;
		initialize();
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String[] Months = {"January", "Feburary", "March", "April", "May", "June", "July", "August", 
				"September", "October", "November", "December"};
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel nameInstruction = new JLabel("Please enter names for your team");
		nameInstruction.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameInstruction.setBounds(10, 11, 209, 39);
		frame.getContentPane().add(nameInstruction);
		
		JLabel nameInstruction2 = new JLabel("Otherwise, use Randomize button.");
		nameInstruction2.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameInstruction2.setBounds(10, 49, 300, 14);
		frame.getContentPane().add(nameInstruction2);
		
		JLabel member1Label = new JLabel("Member 1:");
		member1Label.setBounds(10, 92, 80, 14);
		frame.getContentPane().add(member1Label);
		
		JLabel member2Label = new JLabel("Member 2: ");
		member2Label.setBounds(10, 120, 80, 14);
		frame.getContentPane().add(member2Label);
		
		JLabel member3Label = new JLabel("Member 3:");
		member3Label.setBounds(10, 145, 69, 14);
		frame.getContentPane().add(member3Label);
		
		JLabel member4Label = new JLabel("Member 4:");
		member4Label.setBounds(10, 170, 80, 14);
		frame.getContentPane().add(member4Label);
		
		member1TextField = new JTextField();
		member1TextField.setText("");
		member1TextField.setBounds(81, 92, 96, 20);
		frame.getContentPane().add(member1TextField);
		member1TextField.setColumns(10);
		
		member2TextField = new JTextField();
		member2TextField.setText("");
		member2TextField.setBounds(81, 117, 96, 20);
		frame.getContentPane().add(member2TextField);
		member2TextField.setColumns(10);
		
		member3TextField = new JTextField();
		member3TextField.setText("");
		member3TextField.setBounds(81, 142, 96, 20);
		frame.getContentPane().add(member3TextField);
		member3TextField.setColumns(10);
		
		member4TextField = new JTextField();
		member4TextField.setText("");
		member4TextField.setBounds(81, 167, 96, 20);
		frame.getContentPane().add(member4TextField);
		member4TextField.setColumns(10);
		
		JLabel dateLabel = new JLabel("When do you want to leave?");
		dateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateLabel.setBounds(222, 92, 173, 14);
		frame.getContentPane().add(dateLabel);
		
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setToolTipText("");
		monthComboBox.setBounds(249, 116, 86, 22);
		frame.getContentPane().add(monthComboBox);
		//monthComboBox.removeAllItems();
		for(int i = 0; i < 12; i++) {
			monthComboBox.addItem(Months[i]);
		}
		
		JButton confirmButton = new JButton("Confirm Choices");
		confirmButton.setBounds(249, 208, 155, 23);
		frame.getContentPane().add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//applies the chosen start month
				int chosenMonth = monthComboBox.getComponentCount();
				Date.setMonth(chosenMonth);
				
				//sets up player names for Health class
				if (member1TextField.getText() != "" && member2TextField.getText() != "" &&
						member3TextField.getText() != "" && member4TextField.getText() != "") {
					health.setPlayerName(member1TextField.getText(), member2TextField.getText(), 
							member3TextField.getText(), member4TextField.getText());
					WagonLoad frame = new WagonLoad(wagon);
					frame.setVisible(true);
				}
				else { //occurs if there is no text to grab
					String textF = "Please input names or click randomize.";
					String titleF = "Error";
					int typeF = JOptionPane.INFORMATION_MESSAGE;
					JOptionPane.showMessageDialog(null, textF, titleF, typeF);
				}
				
			}
		});
		
		JButton randomNameButton = new JButton("Randomize");
		randomNameButton.setBounds(49, 208, 155, 23);
		frame.getContentPane().add(randomNameButton);
		randomNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member1TextField.setText(health.setRandomName());
				member2TextField.setText(health.setRandomName());
				member3TextField.setText(health.setRandomName());
				member4TextField.setText(health.setRandomName());
				
				
			}
		});

		
	}
	
	
	/**
	 * Sets the frame to visible.
	 * @param visible - Boolean that when true 
	 * allows the frame to be visible.
	 */
	public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
	

	
}
