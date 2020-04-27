package balloon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class GUIBalloon extends JFrame {

	private JPanel contentPane;
	private BalloonPanel balloonPanel1;
	private BalloonPanel balloonPanel2;
	private JTextField btnEquals;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBalloon frame = new GUIBalloon();
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
	public GUIBalloon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel btnBalloonDemo = new JLabel("Balloon Demo");
		btnBalloonDemo.setBorder(new EmptyBorder(5, 0, 5, 0));
		btnBalloonDemo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnBalloonDemo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(btnBalloonDemo, BorderLayout.NORTH);
		
		JPanel controlPanel = createControlPanel();
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
		contentPane.add(displayPanel, BorderLayout.CENTER);
		displayPanel.setLayout(new GridLayout(1, 2, 5, 0));
		
		balloonPanel1 = new BalloonPanel(new Balloon("Helium",Size.M));
		displayPanel.add(balloonPanel1);
		
		balloonPanel2 = new BalloonPanel(new Balloon("air",Size.L));
		displayPanel.add(balloonPanel2);
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
		
		JButton btnCompare = new JButton("Compare");
		
		btnCompare.addActionListener(new ActionListener() {
			final private Random rand = new Random();
			
			public void actionPerformed(ActionEvent e) {
				Balloon balloon1 = getRandomBalloon();
				Balloon balloon2 = getRandomBalloon();
				
				balloonPanel1.update(balloon1);
				balloonPanel2.update(balloon2);
				
				if(balloon1.equals(balloon2)) {
					btnEquals.setText("EQUAL");
					btnEquals.setBackground(Color.YELLOW);
				}
				else {
					btnEquals.setText("not equals");
					btnEquals.setBackground(Color.WHITE);
				}
			}

			private Balloon getRandomBalloon() {
				Balloon balloon;
				Size[] sizes =Size.values();
				String[] types = {"Air", "Helium"};
				Random rand = new Random();
				String randomType = types[rand.nextInt(types.length)];
				Size randomSize = sizes[rand.nextInt(sizes.length)];
				return new Balloon(randomType,randomSize);
			}
		});
		controlPanel.add(btnCompare);
		
		JTextField btnEquals = createTextField();
		controlPanel.add(btnEquals);
		return controlPanel;
	}

	private JTextField createTextField() {
		btnEquals = new JTextField(10);
		btnEquals.setEditable(false);
		btnEquals.setBackground(Color.WHITE);
		btnEquals.setText("not equals");
		btnEquals.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		return btnEquals;
	}

}
