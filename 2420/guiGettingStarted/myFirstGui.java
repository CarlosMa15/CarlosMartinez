package guiGettingStarted;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class myFirstGui extends JFrame {

	private JPanel contentPane;
	private JLabel ten;
	private int count = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myFirstGui frame = new myFirstGui();
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
	public myFirstGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		createLabelTitile();
		
		createButtonToggle();
		
		createButtonTen();
		
		createPanel();
		
		creatButtonAdd();
	}

	private void creatButtonAdd() {
		JButton btnToggle = new JButton("Add");
		btnToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count += 2;
				ten.setText(Integer.toString(count));
			}
		});
		btnToggle.setBorderPainted(false);
		btnToggle.setOpaque(true);
		btnToggle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnToggle.setBackground(Color.BLACK);
		btnToggle.setForeground(Color.RED);
		contentPane.add(btnToggle, BorderLayout.EAST);
	}

	private void createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel name = new JLabel("Name: ");
		name.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(name);
		
		JTextField text = new JTextField(15);
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = text.getText();
				ten.setText(input);
			}
		});
		panel.add(text);
	}

	private void createButtonTen() {
		ten = new JLabel("10");
		ten.setOpaque(true);
		ten.setBackground(Color.YELLOW);
		ten.setForeground(Color.RED);
		ten.setFont(new Font("Georgia", Font.PLAIN, 98));
		ten.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ten, BorderLayout.CENTER);
	}

	private void createButtonToggle() {
		JButton btnToggle = new JButton("Toggle");
		btnToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if( btnToggle.getBackground() == Color.RED) {
					 btnToggle.setBackground(Color.BLUE);
				 }
				 else {
					 btnToggle.setBackground(Color.RED);
				 }
				
			}
		});
		btnToggle.setOpaque(true);
		btnToggle.setBorderPainted(false);
		btnToggle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnToggle.setBackground(Color.BLUE);
		btnToggle.setForeground(Color.GREEN);
		contentPane.add(btnToggle, BorderLayout.WEST);
	}

	

	private void createLabelTitile() {
		JLabel myTitle = new JLabel("  First GUI");
		myTitle.setBorder(new EmptyBorder(10, 5, 10, 0));
		myTitle.setIcon(new ImageIcon(myFirstGui.class.getResource("/guiGettingStarted/Images/images-1.jpeg")));
		myTitle.setOpaque(true);
		myTitle.setBackground(new Color(127, 255, 0));
		myTitle.setForeground(Color.BLUE);
		myTitle.setHorizontalAlignment(SwingConstants.CENTER);
		myTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
		contentPane.add(myTitle, BorderLayout.NORTH);
	}
}