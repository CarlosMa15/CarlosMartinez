package LabCoin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CoinGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoinGUI frame = new CoinGUI();
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
	public CoinGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		createContentPane();
		setContentPane(contentPane);
		
		JTextField txtrCoininfo = createTextField();
		contentPane.add(txtrCoininfo, BorderLayout.CENTER);
		
		JPanel panel = createDisplayPanel(txtrCoininfo);
		contentPane.add(panel, BorderLayout.NORTH);
	}

	private void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}

	private JPanel createDisplayPanel(JTextField txtrCoininfo) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnCent = createAndFunctionalityToButtonCent(txtrCoininfo);
		panel.add(btnCent);
		
		JButton btnNickle = createAndFunctionalityToButtonNickel(txtrCoininfo);
		panel.add(btnNickle);
		
		JButton btnDime = createAndFunctionalityToButtonDime(txtrCoininfo);
		panel.add(btnDime);
		
		JButton btnQuarter = createAndFunctionalityToButtonQuarter(txtrCoininfo);
		panel.add(btnQuarter);
		return panel;
	}

	private JButton createAndFunctionalityToButtonQuarter(JTextField txtrCoininfo) {
		JButton btnQuarter = new JButton("");
		btnQuarter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrCoininfo.setText(Coin.QUARTER.toString());
			}
		});
		btnQuarter.setBorderPainted(false);
		btnQuarter.setIcon(new ImageIcon(CoinGUI.class.getResource("/LabCoin/Images/UsQuarter.png")));
		return btnQuarter;
	}

	private JButton createAndFunctionalityToButtonDime(JTextField txtrCoininfo) {
		JButton btnDime = new JButton("");
		btnDime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrCoininfo.setText(Coin.DIME.toString());
			}
		});
		btnDime.setBorderPainted(false);
		btnDime.setIcon(new ImageIcon(CoinGUI.class.getResource("/LabCoin/Images/UsDime.png")));
		return btnDime;
	}

	private JButton createAndFunctionalityToButtonNickel(JTextField txtrCoininfo) {
		JButton btnNickle = new JButton("");
		btnNickle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrCoininfo.setText(Coin.NICKEL.toString());
			}
		});
		btnNickle.setBorderPainted(false);
		btnNickle.setIcon(new ImageIcon(CoinGUI.class.getResource("/LabCoin/Images/UsNickel.png")));
		return btnNickle;
	}

	private JButton createAndFunctionalityToButtonCent(JTextField txtrCoininfo) {
		JButton btnCent = new JButton("");
		btnCent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrCoininfo.setText(Coin.CENT.toString());
			}
		});
		btnCent.setBorderPainted(false);
		btnCent.setIcon(new ImageIcon(CoinGUI.class.getResource("/LabCoin/Images/UsCent.png")));
		return btnCent;
	}

	private JTextField createTextField() {
		JTextField txtrCoininfo = new JTextField();
		txtrCoininfo.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		txtrCoininfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtrCoininfo.setText(Coin.CENT.toString());
		return txtrCoininfo;
	}

}
