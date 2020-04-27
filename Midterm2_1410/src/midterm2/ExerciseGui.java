/*******************************************
 * Author: Carlos Martinez
 * Date: March 21, 2017
 * Assignment: Midterm 2
 ******************************************/
package midterm2;

//Import Statements
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class creates a GUI with two button, sad and happy.
 * The GUI displays the emotion of the button pressed.
 * The GUI has also other components, labels, that make up the GUI
 * @author Carlos Martinez
 *
 */
public class ExerciseGui extends JFrame {
	
	/**
	 * The contentPanel that holds everything
	 */
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExerciseGui frame = new ExerciseGui();
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
	public ExerciseGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		createContentPane();
		setContentPane(contentPane);
		
		JLabel labelFeelings = createLabelFeeling();
		contentPane.add(labelFeelings, BorderLayout.NORTH);
		
		JLabel labelEmotion = createLabelEmotion();
		contentPane.add(labelEmotion, BorderLayout.SOUTH);
		
		JPanel displayPanel = createDisplayPanel();
		createsDisplayPanelContent(labelEmotion, displayPanel);
		contentPane.add(displayPanel, BorderLayout.CENTER);
		
	}

	/**
	 * This method creates the content that is in the displayPanel.
	 * The method adds two buttons to the displayPanel and also adds
	 * the functionality of both the buttons
	 * @param labelEmotion The JLabel that displays the emotion
	 * @param displayPanel The displayPanel that holds all these button
	 */
	private void createsDisplayPanelContent(JLabel labelEmotion, JPanel displayPanel) {
		JButton btnHappy = createButtonHappy();
		displayPanel.add(btnHappy);
		
		JButton btnSad = createButtonSad();
		displayPanel.add(btnSad);
		
		addHappyFunctionality(labelEmotion, btnHappy, btnSad);
		
		addSadFunctionality(labelEmotion, btnHappy, btnSad);
	}
	
	/**
	 * This method creates the content Pane of the GUI
	 */
	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}
	
	/**
	 * This method adds the functionality to the HappyButton. It changes the HappyButton background
	 * to a bright yellow and the SadButton background to a mute blue. The Method also changes the
	 * emotionlabel's text to Happy
	 * @param labelEmotion The EmotionLabel that displays the emotion being felt
	 * @param btnHappy The HappyButton from the GUI
	 * @param btnSad The SadButton from the GUI
	 */
	private void addHappyFunctionality(JLabel labelEmotion, JButton btnHappy, JButton btnSad) {
		btnHappy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHappy.setBackground(new Color(244, 214, 86));
				btnSad.setBackground(new Color(159, 175, 224));
				labelEmotion.setText("Happy");
				
			}
		});
	}
	
	/**
	 * This method add the functionality of the Sad Button to the Program. I makes the HappyButton
	 * background mute Yellow, and the SadButton background a bright Blue. The method also changes
	 * the EmotionLabel text to Sad.
	 * @param labelEmotion The label that displays what emotion is being felt
	 * @param btnHappy The HappyButton from the GUI
	 * @param btnSad The SadButton from the GUI
	 */
	private void addSadFunctionality(JLabel labelEmotion, JButton btnHappy, JButton btnSad) {
		btnSad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHappy.setBackground(new Color(242, 228, 171));
				btnSad.setBackground(new Color(85, 125, 225));
				labelEmotion.setText("Sad");
			}
		});
	}
	
	/**
	 * This method creates a SadButton with a image of a frowning face
	 * @return a JButton with the image of a frowning face
	 */
	private JButton createButtonSad() {
		JButton btnSad = new JButton("");
		btnSad.setBorderPainted(false);
		btnSad.setBackground(new Color(159, 175, 224));
		btnSad.setOpaque(true);
		btnSad.setIcon(new ImageIcon(ExerciseGui.class.getResource("/midterm2/Images/Sad.png")));
		return btnSad;
	}
	
	/**
	 * This method creates a HappyButton with an image of a smiling face
	 * @return a JButton with a smiling face
	 */
	private JButton createButtonHappy() {
		JButton btnHappy = new JButton("");
		btnHappy.setBorderPainted(false);
		btnHappy.setOpaque(true);
		btnHappy.setBackground(new Color(244, 214, 86));
		btnHappy.setIcon(new ImageIcon(ExerciseGui.class.getResource("/midterm2/Images/Happy.png")));
		return btnHappy;
	}
	
	/**
	 * This Method creates a displayPanel to hold two buttons in the future of the same size, The
	 * displayPanel will be for the Center of the GUI
	 * @return a Panel to hold items
	 */
	private JPanel createDisplayPanel() {
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		displayPanel.setBackground(new Color(128, 128, 128));
		displayPanel.setLayout(new GridLayout(0, 2, 20, 0));
		return displayPanel;
	}

	/**
	 * This Method creates a label displaying the emotion felt at the bottom of the GUI
	 * @return a JLabel saying happy.
	 */
	private JLabel createLabelEmotion() {
		JLabel labelEmotion = new JLabel("Happy");
		labelEmotion.setOpaque(true);
		labelEmotion.setBorder(new EmptyBorder(10, 0, 10, 0));
		labelEmotion.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		labelEmotion.setBackground(new Color(200, 200, 200));
		labelEmotion.setForeground(new Color(55, 55, 55));
		labelEmotion.setHorizontalAlignment(SwingConstants.CENTER);
		return labelEmotion;
	}

	/**
	 * This method creates a title label call feeling for the top of the GUI
	 * @return a JLabel saying Feeling
	 */
	private JLabel createLabelFeeling() {
		JLabel labelFeelings = new JLabel("Feelings");
		labelFeelings.setOpaque(true);
		labelFeelings.setBorder(new EmptyBorder(10, 0, 10, 0));
		labelFeelings.setBackground(new Color(200, 200, 200));
		labelFeelings.setForeground(new Color(55, 55, 55));
		labelFeelings.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		labelFeelings.setHorizontalAlignment(SwingConstants.CENTER);
		return labelFeelings;
	}
}