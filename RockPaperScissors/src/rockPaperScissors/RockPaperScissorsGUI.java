package rockPaperScissors;

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
import java.util.Random;
import java.awt.event.ActionEvent;

/**
 * This class creates a Gui to play Rock Paper Scissors
 * with a computer that will play with you
 * @author Carlos Martinez
 */
public class RockPaperScissorsGUI extends JFrame {

	/**
	 * this Panel displays everything
	 */
	private JPanel contentPane;
	
	/**
	 * This is the out put label show who won
	 */
	private JLabel labelOutPut;
	
	/**
	 * Random to create a random number to choose
	 * a random choice for the computer
	 */
	private Random rand = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RockPaperScissorsGUI frame = new RockPaperScissorsGUI();
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
	public RockPaperScissorsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		createContentPane();
		setContentPane(contentPane);

		JLabel labelTitle = createTitleLabel();
		contentPane.add(labelTitle, BorderLayout.NORTH);

		labelOutPut = createOutPutLabel();
		contentPane.add(labelOutPut, BorderLayout.SOUTH);

		JPanel panelMiddle = createMiddlePanel();
		contentPane.add(panelMiddle, BorderLayout.CENTER);
	}
	
	/**
	 * this method creates the middle panel displaying the images and both players
	 * @return a panel
	 */
	private JPanel createMiddlePanel() {
		JPanel panelMiddle = new JPanel();
		panelMiddle.setLayout(new BorderLayout(0, 0));

		JLabel btnComputerImage = new JLabel("");
		JLabel btnYouimage = new JLabel("");
		JPanel panelImages = createImagePanel(btnComputerImage, btnYouimage);
		panelMiddle.add(panelImages, BorderLayout.CENTER);

		JPanel controlPanel = creatingControlPanel(btnYouimage, btnComputerImage);
		panelMiddle.add(controlPanel, BorderLayout.NORTH);

		JPanel playerPanel = createPlayerPanel();
		panelMiddle.add(playerPanel, BorderLayout.SOUTH);
		return panelMiddle;
	}
	
	/**
	 * this method the image panel
	 * @param btnComputerImage
	 * @param btnYouimage
	 * @return
	 */
	private JPanel createImagePanel(JLabel btnComputerImage, JLabel btnYouimage) {
		JPanel panelImages = new JPanel();
		panelImages.setBackground(Color.CYAN);
		panelImages.setBorder(new EmptyBorder(0, 20, 0, 20));
		panelImages.setLayout(new GridLayout(0, 2, 0, 0));

		createsYouImage(btnYouimage);
		panelImages.add(btnYouimage);

		createsComputerImage(btnComputerImage);
		panelImages.add(btnComputerImage);
		return panelImages;
	}
	
	/**
	 * this method creates label image of your choice
	 * @param btnYouimage
	 */
	private void createsYouImage(JLabel btnYouimage) {
		btnYouimage.setBackground(Color.BLUE);
		btnYouimage.setHorizontalAlignment(SwingConstants.CENTER);
		btnYouimage.setIcon(
				new ImageIcon(RockPaperScissorsGUI.class.getResource("/rockPaperScissors/Images/QuestionMark.png")));
	}
	
	/**
	 * this method creates computer image label
	 * @param btnComputerImage
	 */
	private void createsComputerImage(JLabel btnComputerImage) {
		btnComputerImage.setBackground(Color.RED);
		btnComputerImage.setHorizontalAlignment(SwingConstants.CENTER);
		btnComputerImage.setIcon(
				new ImageIcon(RockPaperScissorsGUI.class.getResource("/rockPaperScissors/Images/QuestionMark.png")));
	}
	
	/**
	 * this method creates the control panel
	 * @param btnYouimage
	 * @param btnComputerImage
	 * @return
	 */
	private JPanel creatingControlPanel(JLabel btnYouimage, JLabel btnComputerImage) {
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.CYAN);
		controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		controlPanel.setLayout(new GridLayout(0, 3, 10, 0));

		JButton btnRock = createsButtonRock(btnYouimage, btnComputerImage);
		controlPanel.add(btnRock);

		JButton btnPaper = createsButtonPaper(btnYouimage, btnComputerImage);
		controlPanel.add(btnPaper);

		JButton btnScissors = createsScissorsButton(btnYouimage, btnComputerImage);
		controlPanel.add(btnScissors);
		return controlPanel;
	}
	
	/**
	 * this method creates button Rock
	 * @param btnYouimage
	 * @param btnComputerImage
	 * @return
	 */
	private JButton createsButtonRock(JLabel btnYouimage, JLabel btnComputerImage) {
		JButton btnRock = new JButton("Rock");
		btnRock.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		addsRockButtonFunctionality(btnYouimage, btnComputerImage, btnRock);
		return btnRock;
	}
	
	/**
	 * this methos adds functionality to button Rock
	 * @param btnYouimage
	 * @param btnComputerImage
	 * @param btnRock
	 */
	private void addsRockButtonFunctionality(JLabel btnYouimage, JLabel btnComputerImage, JButton btnRock) {
		btnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnYouimage.setIcon(Choice.ROCK.getImageIcon());
				Choice computerChoice = randomChoice();
				btnComputerImage.setIcon(computerChoice.getImageIcon());
				labelOutPut.setText(Choice.ROCK.evaluates(computerChoice));
			}
		});
	}
	
	/**
	 * This method creates button Paper
	 * @param btnYouimage
	 * @param btnComputerImage
	 * @return
	 */
	private JButton createsButtonPaper(JLabel btnYouimage, JLabel btnComputerImage) {
		JButton btnPaper = new JButton("Paper");
		btnPaper.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		addsPaperButtonFunctionality(btnYouimage, btnComputerImage, btnPaper);
		return btnPaper;
	}
	
	/**
	 * This method adds functionality to button paper
	 * @param btnYouimage
	 * @param btnComputerImage
	 * @param btnPaper
	 */
	private void addsPaperButtonFunctionality(JLabel btnYouimage, JLabel btnComputerImage, JButton btnPaper) {
		btnPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnYouimage.setIcon(Choice.PAPER.getImageIcon());
				Choice computerChoice = randomChoice();
				btnComputerImage.setIcon(computerChoice.getImageIcon());
				labelOutPut.setText(Choice.PAPER.evaluates(computerChoice));
			}
		});
	}
	
	/**
	 * This method creates scissor button
	 * @param btnYouimage
	 * @param btnComputerImage
	 * @return
	 */
	private JButton createsScissorsButton(JLabel btnYouimage, JLabel btnComputerImage) {
		JButton btnScissors = new JButton("Scissors");
		btnScissors.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		addsScissorsFunctionality(btnYouimage, btnComputerImage, btnScissors);
		return btnScissors;
	}
	
	/**
	 * this method adds the functionality to Scissor button
	 * @param btnYouimage 
	 * @param btnComputerImage
	 * @param btnScissors
	 */
	private void addsScissorsFunctionality(JLabel btnYouimage, JLabel btnComputerImage, JButton btnScissors) {
		btnScissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnYouimage.setIcon(Choice.SCISSORS.getImageIcon());
				Choice computerChoice = randomChoice();
				btnComputerImage.setIcon(computerChoice.getImageIcon());
				labelOutPut.setText(Choice.SCISSORS.evaluates(computerChoice));
			}
		});
	}
	
	/**
	 * this method creates a player panel
	 * @return a player panel
	 */
	private JPanel createPlayerPanel() {
		JPanel playerPanel = new JPanel();
		playerPanel.setBackground(Color.CYAN);
		playerPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel youLabel = createYouLabel();
		playerPanel.add(youLabel);

		JLabel computerLabel = createComputerLabel();
		playerPanel.add(computerLabel);
		return playerPanel;
	}
	
	/**
	 * this method creates the a you label
	 * @return a you label
	 */
	private JLabel createYouLabel() {
		JLabel youLabel = new JLabel("You");
		youLabel.setBackground(Color.BLUE);
		youLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		youLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return youLabel;
	}

	/**
	 * this method creates the computer label
	 * @return a computer label
	 */
	private JLabel createComputerLabel() {
		JLabel computerLabel = new JLabel("Computer");
		computerLabel.setBackground(Color.RED);
		computerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		computerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return computerLabel;
	}
	
	/**
	 * this method crates the an output label
	 * @return an output label
	 */
	private JLabel createOutPutLabel() {
		JLabel labelOutPut = new JLabel("Who Will Win?");
		labelOutPut.setBorder(new EmptyBorder(10, 0, 10, 0));
		labelOutPut.setOpaque(true);
		labelOutPut.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		labelOutPut.setBackground(Color.CYAN);
		labelOutPut.setHorizontalAlignment(SwingConstants.CENTER);
		return labelOutPut;
	}
	
	/**
	 * This method creates the Title Label
	 * @return a title label
	 */
	private JLabel createTitleLabel() {
		JLabel labelTitle = new JLabel("Make Your Choice");
		labelTitle.setOpaque(true);
		labelTitle.setBackground(Color.CYAN);
		labelTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
		labelTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		return labelTitle;
	}
	
	/**
	 * This creates the content Pane
	 */
	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}
	
	/**
	 * This method chooses a random choice of rock,
	 * paper, or scissors to get an image
	 * @return a random choice
	 */
	private Choice randomChoice() {
		int randomNumber = rand.nextInt(3);
		Choice computerChoice = null;
		if (randomNumber == 0) {
			computerChoice = Choice.PAPER;
		}
		if (randomNumber == 1) {
			computerChoice = Choice.ROCK;
		}
		if (randomNumber == 2) {
			computerChoice = Choice.SCISSORS;
		}
		return computerChoice;
	}
}