package ticTacToe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame {
	private int playersTurn;
	private JButton[] buttons;
	private JLabel resultLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToe frame = new TicTacToe();
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
	public TicTacToe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 600, 600);
		JPanel contentPane = createContentPanel();
		setContentPane(contentPane);
		
		this.playersTurn = 1;
	}
	
	/**
	 * This creates the Content Panel that holds everything
	 * @return the content panel
	 */
	private JPanel createContentPanel() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		resultLabel = new JLabel("Lets Play");
		resultLabel.setOpaque(true);
		resultLabel.setBackground(Color.DARK_GRAY);
		resultLabel.setForeground(Color.YELLOW);
		resultLabel.setFont(new Font("Georgia", Font.PLAIN, 35));
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(resultLabel, BorderLayout.SOUTH);
		
		JPanel gridTiles = new JPanel();
		gridTiles.setBorder(new EmptyBorder(5, 5, 5, 5));
		gridTiles.setBackground(Color.DARK_GRAY);
		contentPane.add(gridTiles, BorderLayout.CENTER);
		gridTiles.setLayout(new GridLayout(3, 3, 5, 5));
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 9;i++) {
					buttons[i].setText("");
				}
				playersTurn = 1;
				resultLabel.setText("Lets Play");
			}
		});
		btnNewGame.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		contentPane.add(btnNewGame, BorderLayout.NORTH);
		
		this.buttons = new JButton[9];
		for(int i = 0; i < 9;i++) {
			JButton newButton = createButton();
			buttons[i] = newButton;
			gridTiles.add(newButton);
		}
		
		return contentPane;
	}
	
	/**
	 * Creates a button for the game
	 * @return
	 */
	private JButton createButton() {
		JButton button = new JButton("");
		button.setOpaque(true);
		button.setBackground(new Color(51, 204, 255));
		button.setBorderPainted(false);
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 99));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playersTurn % 2 != 0 && button.getText() == "" && playersTurn > 0) {
					button.setText("X");
					resultLabel.setText("It's player O's turn");
					playersTurn++;
				} else if (playersTurn % 2 == 0 && button.getText() == "" && playersTurn > 0) {
					button.setText("O");
					resultLabel.setText("It's player X's turn");
					playersTurn++;
				}
				
				Check();
				
				if (playersTurn > 9 && resultLabel.getText().charAt(0) != 'P') {
					resultLabel.setText("No One Won");
				}
			}

			private void Check() {
				if (buttons[0].getText() != "" && buttons[0].getText() == buttons[1].getText() && buttons[1].getText() == buttons[2].getText()) {
					resultLabel.setText("Player " + buttons[0].getText() + " Won");
					playersTurn = 0;
				} else if (buttons[3].getText() != "" && buttons[3].getText() == buttons[4].getText() && buttons[4].getText() == buttons[5].getText()) {
					resultLabel.setText("Player " + buttons[3].getText() + " Won");
					playersTurn = 0;
				} else if (buttons[6].getText() != "" && buttons[6].getText() == buttons[7].getText() && buttons[7].getText() == buttons[8].getText()) {
					resultLabel.setText("Player " + buttons[6].getText() + " Won");
					playersTurn = 0;
				} else if (buttons[0].getText() != "" && buttons[0].getText() == buttons[3].getText() && buttons[3].getText() == buttons[6].getText()) {
					resultLabel.setText("Player " + buttons[0].getText() + " Won");
					playersTurn = 0;
				} else if (buttons[1].getText() != "" && buttons[1].getText() == buttons[4].getText() && buttons[4].getText() == buttons[7].getText()) {
					resultLabel.setText("Player " + buttons[1].getText() + " Won");
					playersTurn = 0;
				} else if (buttons[2].getText() != "" && buttons[2].getText() == buttons[5].getText() && buttons[5].getText() == buttons[8].getText()) {
					resultLabel.setText("Player " + buttons[2].getText() + " Won");
					playersTurn = 0;
				} else if (buttons[0].getText() != "" && buttons[0].getText() == buttons[4].getText() && buttons[4].getText() == buttons[8].getText()) {
					resultLabel.setText("Player " + buttons[0].getText() + " Won");
					playersTurn = 0;
				} else if (buttons[6].getText() != "" && buttons[6].getText() == buttons[4].getText() && buttons[4].getText() == buttons[2].getText()) {
					resultLabel.setText("Player " + buttons[6].getText() + " Won");
					playersTurn = 0;
				}
			}
		});		
		return button;
	}
}