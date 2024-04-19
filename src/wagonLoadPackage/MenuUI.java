package wagonLoadPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

/**
 * MenuUI.java 
 * Created on 4/8/2024
 * By David Flores
 * 
 * Creates a new UI Frame for a location/rest. 
 * Allows user to read a description of 
 * the current location, talk to passerbys,
 * and view the map.
 */
public class MenuUI {

	// Initialize variables 
	private JFrame frmLocationName;
	private JTextField promptField;
	Location location;
	private JTabbedPane descPane;
	private JTabbedPane tabbedPane_2;
	private JTabbedPane mapPane;
	
	
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
		frmLocationName = new JFrame();
		frmLocationName.setTitle(location.getName());
		frmLocationName.setBounds(100, 100, 450, 300);
		frmLocationName.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLocationName.getContentPane().setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 418, 212);
		frmLocationName.getContentPane().add(tabbedPane);
				
		// Description tab
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        JTextArea descriptionTextArea = new JTextArea(location.getDesc());
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setEditable(false);
        descriptionPanel.add(new JScrollPane(descriptionTextArea), BorderLayout.CENTER);
        tabbedPane.addTab("Description", descriptionPanel);
		
        // Talk to Locals Tab
        JPanel localsTalkTextPanel = new JPanel(new BorderLayout());
        JTextArea localsTalkTextArea = new JTextArea("...");
        localsTalkTextArea.setLineWrap(true);
        localsTalkTextArea.setWrapStyleWord(true);
        JButton talkButton = new JButton("Talk to Passerbys");
        talkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Select a random chat
                String[] chats = 
                	{"Sometimes it feels like every step forward is "
                			+ "met with two steps back, but we press on, "
                			+ "determined to reach our destination.",
                	"It's a constant struggle balancing the needs of my family "
                	+ "with the challenges of the unforgiving terrain, but its worth it.", 
                	"From sickness to dwindling supplies... it can miserable at times.", 
                	"You try to yield some people, but they ignore you and pass by.", 
                	"You look around and off into the distance, but no one is nearby to talk to."};
                Random random = new Random();
                int choice = random.nextInt(chats.length);
                localsTalkTextArea.setText(chats[choice]);
            }
        });
        localsTalkTextPanel.add(localsTalkTextArea, BorderLayout.CENTER);
        localsTalkTextPanel.add(talkButton, BorderLayout.SOUTH);
        tabbedPane.addTab("Talk to Passerbys", localsTalkTextPanel);
		
        // Map Tab
        // TODO: Fix png not showing on load
        // png is being sourced from bin (but is located in src??? check if is issue)
        JPanel mapPanel = new JPanel(new BorderLayout());
        JLabel artLabel = new JLabel();
        URL imageUrl = WagonLoad.class.getResource("/images/OreganTrailMap.png");
        if (imageUrl != null) {
            artLabel.setIcon(new ImageIcon(imageUrl));
            System.out.println("imageURL: " + imageUrl);
        } else {
            System.out.println("Image not found");
        }
        mapPanel.add(artLabel);
        mapPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("View Map", mapPane);
		
        JPanel riverPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("River Options", riverPanel);
        JTextArea riverText = new JTextArea("...");
        riverText.setLineWrap(true);
        riverText.setWrapStyleWord(true);
        riverText.setEditable(false);
        riverText.setText(location.getName() + "\n" + location.getDesc());
        
        riverPanel.add(riverText);
        
        if (Wagon.travel.getCurLocation().getIsRiver() == false)
        {
        	riverPanel.setVisible(false);
        	tabbedPane.removeTabAt(3);
        }
        
        JButton btnNewButton = new JButton("See options");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Location current = Wagon.travel.getCurLocation();
        		River newRiver = current.toRiver();
        		newRiver.setConditions(current);
        		
        		JFrame riverOptionsFrame = new JFrame();
        		riverOptionsFrame.setBounds(100, 200, 400, 300);
        		riverOptionsFrame.setVisible(true);
        		riverOptionsFrame.setTitle(location.getName());
        		
        		JPanel riverOptionsPanel = new JPanel();
        		
        		JTextArea riverOptionsText = new JTextArea();
        		riverOptionsText.setLineWrap(true);
                riverOptionsText.setWrapStyleWord(true);
                riverOptionsText.setEditable(false);
               
                String swiftness = String.format("%.2f", newRiver.getSwiftness());
                String depth = String.format("%.2f", newRiver.getDepth());
                String width = String.format("%.2f", newRiver.getWidth());
                String bottomType = newRiver.getBottomType();
                
        		riverOptionsText.setText("Swiftness: " + swiftness + "\n" + "Depth: " + depth + 
        				"\n" + "Width: " + width + "\n" + "Bottom type: " + bottomType);
         		riverOptionsPanel.add(riverOptionsText);
         		
        		JButton fordButton = new JButton("Ford");
        		JButton floatButton = new JButton("Float");
        		JButton ferryButton = new JButton("Ferry");
        		JButton guideButton = new JButton("Hire a Guide?");
        		riverOptionsPanel.add(fordButton);
        		riverOptionsPanel.add(floatButton);
        		riverOptionsPanel.add(ferryButton);
        		riverOptionsPanel.add(guideButton);
        		
        		JFrame riverResultsFrame = new JFrame();
        		riverResultsFrame.setVisible(false);
        		riverResultsFrame.setBounds(100, 200, 400, 300);
        		
        		JPanel riverResultsPanel = new JPanel();
        		
        		JTextArea riverResultsText = new JTextArea();
        		riverResultsText.setLineWrap(true);
        		riverResultsText.setWrapStyleWord(true);
        		riverResultsText.setEditable(false);
        		
        		riverResultsPanel.add(riverResultsText);
        		riverResultsFrame.add(riverResultsPanel);
        		
        		guideButton.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				newRiver.crossWithGuide(true);
        			}
        		});
        	
        		fordButton.addActionListener(new ActionListener(){
        			public void actionPerformed(ActionEvent e) {
        				int[] conditions = newRiver.fordRiver();
        				String results = newRiver.getPrompt(0);
        				String supplyResults = "", daysLost = "";
        				if (conditions[2] != 0)
        				{
        					supplyResults = "You lost " + conditions[2] + "% of your supplies!";
        				}
        				if (conditions[3] != 0)
        				{
        					daysLost = "You lost " + conditions[3] + " days!";
        				}
        				riverResultsText.setText( results + "\n" + supplyResults + "\n" + daysLost);
        				riverResultsFrame.setVisible(true);
        			}
        		});
        		
        		floatButton.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				int[] conditions = newRiver.floatRiver();
        				String results = newRiver.getPrompt(1);
        				String supplyResults = "", daysLost = "";
        				if (conditions [1] != 0)
        				{
        					supplyResults = "You lost " + conditions[1] + "% of your supplies";
        				}
        				if (conditions[3] != 0)
        				{
        					daysLost = "You lost " + conditions[3] + " days!";
        				}
        				riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
        				riverResultsFrame.setVisible(true);
        			}
        		});
        		
        		ferryButton.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				int[] conditions = newRiver.ferryRiver();
        				String results = newRiver.getPrompt(2);
        				String supplyResults = "", daysLost = "";
        				if (conditions [1] != 0)
        				{
        					supplyResults = "You lost " + conditions[1] + "% of your supplies";
        				}
        				if (conditions[3] != 0)
        				{
        					daysLost = "You lost " + conditions[3] + " days!";
        				}
        				riverResultsText.setText(results + "\n" + supplyResults + "\n" + daysLost);
        				riverResultsFrame.setVisible(true);
        				
        			}
        		});
        		
        		riverOptionsFrame.add(riverOptionsPanel);
        	}
        });
        riverPanel.add(btnNewButton, BorderLayout.SOUTH);
		
        
        
        
        
		// Prompt Field
		promptField = new JTextField(location.getPrompt());
		promptField.setBounds(213, 234, 215, 20);
		frmLocationName.getContentPane().add(promptField);
		promptField.setColumns(10);
		
		// Prompt Label
		JLabel promptLabel = new JLabel(location.getPrompt());
		promptLabel.setBounds(10, 234, 193, 14);
		promptLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		frmLocationName.getContentPane().add(promptLabel);
	}
	
	/**
	 * Sets the frame to visible.
	 * @param visible - Boolean that when true 
	 * allows the frame to be visible.
	 */
	public void setVisible(boolean visible) {
        frmLocationName.setVisible(visible);
    }
}
