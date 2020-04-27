package GuiLayout;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DemoLayout extends JFrame {

	private JPanel contentPane;
	private JButton[] buttons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoLayout frame = new DemoLayout();
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
	public DemoLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		initContentPane();
		
		JLabel labelTitle = createLabelTitle();
		contentPane.add(labelTitle, BorderLayout.NORTH);
		
		JPanel panel = createWestPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JPanel displayPanel = createDisplayPanel();
		contentPane.add(displayPanel, BorderLayout.CENTER);
	}

	private JPanel createDisplayPanel() {
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new EmptyBorder(0, 0, 5, 5));
		
		displayPanel.setLayout(new GridLayout(0, 4,5, 0));
		
		JButton buttonOne = new JButton("1");
		initNumberButton(buttonOne, Color.RED);
		displayPanel.add(buttonOne);
		
		JButton buttonTwo = new JButton("2");
		initNumberButton(buttonTwo, Color.BLUE);
		displayPanel.add(buttonTwo);
		
		JButton buttonThree = new JButton("3");
		initNumberButton(buttonThree, Color.ORANGE);
		displayPanel.add(buttonThree);
		
		JButton buttonFour = new JButton("4");
		initNumberButton(buttonFour, Color.GREEN);
		displayPanel.add(buttonFour);
		
		buttons = new JButton[] {buttonOne, buttonTwo, buttonThree, buttonFour};
		
		return displayPanel;
	}

	private void initNumberButton(JButton button, Color color) {
		button.setBorderPainted(false);
		button.setOpaque(true);
		button.setBackground(color);
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
	}

	private void initContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private JPanel createWestPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(0, 5, 0, 10));
		buttonPanel.setLayout(new GridLayout(6, 1, 5, 0));
		
		JButton buttonLeft = new JButton("<--");
		buttonLeft.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		buttonPanel.add(buttonLeft);
		
		JButton buttonRight = new JButton("-->");
		
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = buttons.length -1; i > 0; i--) {
					swapColor(buttons[i], buttons[i -1]);
				}
			}

			private void swapColor(JButton button1, JButton button2) {
				Color temp = button1.getBackground();
				button1.setBackground(button2.getBackground());
				button2.setBackground(temp);
				
			}
		});
		
		buttonRight.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		buttonPanel.add(buttonRight);
		return buttonPanel;
	}

	private JLabel createLabelTitle() {
		JLabel labelTitle = new JLabel("Demo Layout");
		labelTitle.setBorder(new EmptyBorder(5, 0, 5, 0));
		labelTitle.setForeground(Color.BLUE);
		labelTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		return labelTitle;
	}
}