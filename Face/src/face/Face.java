/****************************************************
 * Author: Carlos Martinez
 * Date: February 27, 2016
 * Assignment: Face
 ***************************************************/
package face;

//ImportSatements
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This Class Creates a window with a face image and control panel that to be
 * able to change many features of the face.
 * 
 * @author Carlos Martinez
 */
public class Face extends JFrame {
	
	/**
	 * This JPanel displays everything
	 */
	private JPanel contentPane;
	
	/**
	 * this is an array that has images of colored circles for the face label to display
	 */
	private ImageIcon[] faceArray = { new ImageIcon(Face.class.getResource("/face/Images/RedCircle.png")),
			new ImageIcon(Face.class.getResource("/face/Images/BlueCircle.png")),
			new ImageIcon(Face.class.getResource("/face/Images/GreenCircle.png")) };

	/**
	 * this is an array that has images of mouth for the mouth label to display
	 */
	private ImageIcon[] mouthArray = { new ImageIcon(Face.class.getResource("/face/Images/mouth1.png")),
			new ImageIcon(Face.class.getResource("/face/Images/mouth2.png")),
			new ImageIcon(Face.class.getResource("/face/Images/mouth3.png")) };
	
	/**
	 * this is an array that has images of nose for the nose label to display
	 */
	private ImageIcon[] noseArray = { new ImageIcon(Face.class.getResource("/face/Images/nose1.png")),
			new ImageIcon(Face.class.getResource("/face/Images/nose2.png")),
			new ImageIcon(Face.class.getResource("/face/Images/nose3.png")) };
	
	/**
	 * this is an array that has images of eyes for the eye label to display
	 */
	private ImageIcon[] eyeArray = { new ImageIcon(Face.class.getResource("/face/Images/eye1.png")),
			new ImageIcon(Face.class.getResource("/face/Images/eye2.gif")),
			new ImageIcon(Face.class.getResource("/face/Images/eye3.png")) };
	
	/**
	 * this is a label for the face that sets an image of a colored circle
	 */
	private JLabel Face;
	
	/**
	 * this checker set the image color of the face from faceArray
	 */
	private int checkerfaceColor = 0;
	
	/**
	 * this label is for the mouth that displays an image of a mouth
	 */
	private JLabel mouth;
	
	/**
	 * this checker sets the image to the mouth from the mouthArray
	 */
	private int checkerMouth = 0;
	
	/**
	 * this label is for the nose that displays an image of a nose
	 */
	private JLabel nose;
	
	/**
	 * this checker sets the image to the nose from the noseArray
	 */
	private int checkerNose = 0;
	
	/**
	 *  this is the label of the left eye that displays an image of an eye
	 */
	private JLabel leftEye;
	
	/**
	 * this is the label of the right eye that displays an image
	 */
	private JLabel rightEye;
	
	/**
	 * This checker sets the image to both eye labels from eyeArray
	 */
	private int checkerEye = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Face frame = new Face();
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
	public Face() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		setTitle("Assignment Face");

		createContentPane();
		setContentPane(contentPane);

		JPanel controlPanel = new JPanel();
		contentPane.add(controlPanel, BorderLayout.WEST);
		controlPanel.setLayout(new GridLayout(15, 1, 0, 0));
		createsControlPanelComponents(controlPanel);

		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(Color.YELLOW);
		contentPane.add(displayPanel, BorderLayout.CENTER);
		displayPanel.setLayout(null);
		createFaceDisplay(displayPanel);
	}
	
	/**
	 * creates the components of the control panel
	 * @param controlPanel
	 */
	private void createsControlPanelComponents(JPanel controlPanel) {
		JLabel blankSpace1 = new JLabel("");
		controlPanel.add(blankSpace1);

		JLabel optionsLabel = createOptionsLabel();
		controlPanel.add(optionsLabel);

		JCheckBox checkBoxEyes = createCheckBoxEyes();
		controlPanel.add(checkBoxEyes);

		JCheckBox checkBoxNose = createCheckBoxNose();
		controlPanel.add(checkBoxNose);

		JCheckBox checkBoxMouth = createCheckBoxMouth();
		controlPanel.add(checkBoxMouth);

		JPanel formatPanel = new JPanel();
		controlPanel.add(formatPanel);

		JButton buttonUpdate = createUpdateButton(checkBoxEyes, checkBoxNose, checkBoxMouth);
		formatPanel.add(buttonUpdate);

		JLabel space1 = new JLabel("     ");
		formatPanel.add(space1);
	}
	
	/**
	 * creates the components of the displayPannel
	 * @param displayPanel the displayPanel that shows the images
	 */
	private void createFaceDisplay(JPanel displayPanel) {

		createFaceLabel();

		createLeftEyeLabel();
		displayPanel.add(leftEye);

		createRightEyeLabel();
		displayPanel.add(rightEye);

		createNoseLabel();
		displayPanel.add(nose);

		createMouthLabel();
		displayPanel.add(mouth);

		displayPanel.add(Face);
	}
	
	/**
	 * creates a face label with a colored circle as image
	 */
	private void createFaceLabel() {
		Face = new JLabel("");
		Face.setBounds(0, 0, 603, 568);
		Face.setHorizontalAlignment(SwingConstants.CENTER);
		Face.setIcon(faceArray[checkerfaceColor]);
	}
	
	/**
	 * creates a mouth label with an image of the mouth
	 */
	private void createMouthLabel() {
		mouth = new JLabel("");
		mouth.setOpaque(true);
		mouth.setBackground(Color.RED);
		mouth.setIcon(mouthArray[0]);
		mouth.setBounds(158, 334, 288, 185);
		mouth.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * creates label of the nose with an image of a nose
	 */
	private void createNoseLabel() {
		nose = new JLabel("");
		nose.setOpaque(true);
		nose.setBackground(Color.RED);
		nose.setBounds(244, 180, 117, 142);
		nose.setIcon(noseArray[2]);
		nose.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	 * Creates Right Eye label with an image of an eye
	 */
	private void createRightEyeLabel() {
		rightEye = new JLabel("");
		rightEye.setOpaque(true);
		rightEye.setBackground(Color.RED);
		rightEye.setBounds(361, 94, 129, 115);
		rightEye.setIcon(eyeArray[0]);
		rightEye.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	 * creates The left eye Label with an image of an eye
	 */
	private void createLeftEyeLabel() {
		leftEye = new JLabel("");
		leftEye.setOpaque(true);
		leftEye.setBackground(Color.RED);
		leftEye.setBounds(115, 88, 129, 121);
		leftEye.setIcon(eyeArray[0]);
		leftEye.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * creates the update Button with the functionality
	 * @param checkBoxEyes check box for the eyes
	 * @param checkBoxNose check box for the nose
	 * @param checkBoxMouth check box for the mouth
	 * @return update button with the functionality
	 */
	private JButton createUpdateButton(JCheckBox checkBoxEyes, JCheckBox checkBoxNose, JCheckBox checkBoxMouth) {
		JButton buttonUpdate = new JButton("update");

		addFunctionalityToUpdateButton(checkBoxEyes, checkBoxNose, checkBoxMouth, buttonUpdate);

		buttonUpdate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		return buttonUpdate;
	}
	
	/**
	 * Adds the functionality to the update button
	 * @param checkBoxEyes check box for the eyes
	 * @param checkBoxNose check box for the nose
	 * @param checkBoxMouth check box for the mouth
	 * @param buttonUpdate button that it is adding functionality to
	 */
	private void addFunctionalityToUpdateButton(JCheckBox checkBoxEyes, JCheckBox checkBoxNose, JCheckBox checkBoxMouth,
			JButton buttonUpdate) {
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// IF check box is selected changes the image of both eyes
				if (checkBoxEyes.isSelected()) {
					++checkerEye;
					if (checkerEye == 3) {
						checkerEye = 0;
					}
					leftEye.setIcon(eyeArray[checkerEye]);
					rightEye.setIcon(eyeArray[checkerEye]);
				}
				
				// If nose check box is selected then changes the image of nose
				if (checkBoxNose.isSelected()) {
					++checkerNose;
					if (checkerNose == 3) {
						checkerNose = 0;
					}
					nose.setIcon(noseArray[checkerNose]);
				}
				
				// If mouth check box is selected changes the image of the mouth
				if (checkBoxMouth.isSelected()) {
					++checkerMouth;
					if (checkerMouth == 3) {
						checkerMouth = 0;
					}
					mouth.setIcon(mouthArray[checkerMouth]);
				}
				
				// If no Check Box Selected then Changes the color of the Face
				if (!checkBoxEyes.isSelected() && !checkBoxMouth.isSelected() && !checkBoxNose.isSelected()) {

					++checkerfaceColor;

					if (checkerfaceColor == 2) {
						mouth.setBackground(new Color(0, 128, 0));
						nose.setBackground(new Color(0, 128, 0));
						rightEye.setBackground(new Color(0, 128, 0));
						leftEye.setBackground(new Color(0, 128, 0));
					} else if (checkerfaceColor == 1) {
						mouth.setBackground(Color.blue);
						nose.setBackground(Color.blue);
						rightEye.setBackground(Color.blue);
						leftEye.setBackground(Color.blue);

					} else {
						mouth.setBackground(Color.RED);
						nose.setBackground(Color.RED);
						rightEye.setBackground(Color.RED);
						leftEye.setBackground(Color.RED);
					}

					if (checkerfaceColor == 3) {
						checkerfaceColor = 0;
					}

					Face.setIcon(faceArray[checkerfaceColor]);
				}
			}
		});
	}

	/**
	 * Creates Check box for the Mouth
	 * 
	 * @return Check box for the Mouth
	 */
	private JCheckBox createCheckBoxMouth() {
		JCheckBox checkBoxMouth = new JCheckBox("Mouth");
		checkBoxMouth.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		checkBoxMouth.setBorder(new EmptyBorder(0, 13, 5, 14));
		return checkBoxMouth;
	}

	/**
	 * Creates a Check Box for the Nose
	 * 
	 * @return Check Box for the Nose
	 */
	private JCheckBox createCheckBoxNose() {
		JCheckBox checkBoxNose = new JCheckBox("Nose");
		checkBoxNose.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		checkBoxNose.setBorder(new EmptyBorder(0, 13, 5, 14));
		return checkBoxNose;
	}

	/**
	 * Creates a Check Box for the Eyes
	 * 
	 * @return Check Box for the Eyes
	 */
	private JCheckBox createCheckBoxEyes() {
		JCheckBox checkBoxEyes = new JCheckBox("Eyes");
		checkBoxEyes.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		checkBoxEyes.setBorder(new EmptyBorder(0, 13, 5, 14));
		return checkBoxEyes;
	}

	/**
	 * This creates he OptionsLabel telling you to choose
	 * 
	 * @return label of you choose...
	 */
	private JLabel createOptionsLabel() {
		JLabel optionsLabel = new JLabel("You Choose ...");
		optionsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		optionsLabel.setBorder(new EmptyBorder(0, 13, 5, 14));
		return optionsLabel;
	}

	/**
	 * This creates The contentPanel
	 */
	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}
}
