package listVsSet;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.JLabel;

/**
 * Gui application that describes the two main difference between the
 * interfaces List and Set and that demonstrates the different
 * behaviors of a list and a set.
 * 
 * @authors Carlos Humberto  and Margret Posch 
 *
 */
public class ListVsSetGui extends JFrame {
	/**
	 * Not sure what this is for
	 */
	private static final long serialVersionUID = -942561673068858105L;
	
	/**
	 * The main Panel of the Gui
	 */
	private JPanel contentPane;
	
	/**
	 * This is one of the layouts that the GUI uses
	 */
	private CardLayout cardLayout = new CardLayout();
	
	/**
	 * The test field used in the demo panel class
	 */
	private JTextArea textField;
	
	/**
	 * The ListVsSetDemo that the program uses to demonstrate the differences between list and sets
	 */
	private ListVsSetDemo listVsSetDemo = new ListVsSetDemo( new ColoredSquare(14, Color.BLUE),
			new ColoredSquare(18, Color.RED), new ColoredSquare(12, Color.YELLOW), 
			new ColoredSquare(16, Color.GREEN));
	
	/**
	 * This is the coloredSquare that will be added to the list and set with the add element radio button
	 */
	private ColoredSquare newSquare = new ColoredSquare(10, Color.ORANGE);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListVsSetGui frame = new ListVsSetGui();
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
	public ListVsSetGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		setTitle("List vs Set Demo");
		
		JMenuBar menuBar = createMenuBar();
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout);
		
		// main difference panel
		JPanel mainDifferencePanel = createMainDifferencePanel();
		contentPane.add(mainDifferencePanel, "main difference");
		
		// demo panel
		JPanel demoPanel = createDemoPanel();
		contentPane.add(demoPanel, "demo");
	}

	/**
	 * Creates a panel that demonstrates the two main differences
	 * between lists and sets. 
	 * It does so by adding the same elements to the list and the set
	 * and then displaying the contents of both the list and the set.
	 * 
	 * @return the panel that demonstrates the main differences 
	 * 		between lists and sets.
	 */
	private JPanel createDemoPanel() {
		JPanel demoPanel = new JPanel();
		demoPanel.setLayout(new BorderLayout());
		
		// control panel
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new EmptyBorder(15, 12, 0, 12));
		controlPanel.setLayout(new GridLayout(9, 0, 0, 0));
		
		JLabel lblYourChoice = new JLabel("Your Choice:");
		lblYourChoice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		controlPanel.add(lblYourChoice);
		
		JRadioButton rdbtnListElements = new JRadioButton("List Elements");
		rdbtnListElements.setSelected(true);
		controlPanel.add(rdbtnListElements);	
		
		JRadioButton rdbtnSetElements = new JRadioButton("Set Elements");
		controlPanel.add(rdbtnSetElements);	
		demoPanel.add(controlPanel, BorderLayout.WEST);
		
		JRadioButton rdbtnAddElement = new JRadioButton("Add Element");
		controlPanel.add(rdbtnAddElement);	
		demoPanel.add(controlPanel, BorderLayout.WEST);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnListElements);
		buttonGroup.add(rdbtnSetElements);
		buttonGroup.add(rdbtnAddElement);
		
		rdbtnAddElement.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	listVsSetDemo.addElement(newSquare);
	            textField.setText("Add " +newSquare);
	        }
	    });
		
		rdbtnListElements.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            textField.setText("List:\n" + listVsSetDemo.getListElements());
	        }
	    });
		
		rdbtnSetElements.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	            textField.setText("Set:\n" + listVsSetDemo.getSetElements());
	        }
	    });
		
		// text field
		textField = new JTextArea("List:\n" + listVsSetDemo.getListElements());
		textField.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textField.setBorder(new EmptyBorder(20, 20, 20, 15));
		textField.setColumns(10);
		demoPanel.add(textField, BorderLayout.CENTER);

		return demoPanel;
	}

	/**
	 * Creates a panel that describes the two main differences
	 * between lists and sets. 
	 */
	private JPanel createMainDifferencePanel() {
		JPanel mainDifferencePanel = new JPanel();
		mainDifferencePanel.setLayout(new BorderLayout(0, 0));
		
		JTextArea txtrTheMain = new JTextArea();
		txtrTheMain.setText("The 2 main differences between interface List and Set are:\n\n" 
		+ "List allows duplicates while Set doesn’t allow duplicate "
		+ "\nelements. All the elements of a Set should be unique if you try to insert the "
		+ "\nduplicate element in Set it would replace the existing value.\n\n"
		+ "Set is an unordered collection, it doesn’t maintain any order.\n"
		+ "List have an order");
		txtrTheMain.setBorder(new EmptyBorder(15, 15, 15, 15));
		txtrTheMain.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrTheMain.setBackground(new Color(141, 141, 131));
		txtrTheMain.setForeground(Color.WHITE);
		mainDifferencePanel.add(txtrTheMain);
		
		return mainDifferencePanel;
	}

	/**
	 * Create a menu bar with the following choices:
	 * Demo the behavior of lists and sets
	 * Describe the two main differences between lists and sets
	 * Exit the application
	 * 
	 * @return the menu bar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		// menu item "Demo"
		JMenuItem mntmDemo = new JMenuItem("Demo");
		mntmDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "demo");
			}
		});
		mntmDemo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mntmDemo);
		
		// menu item "Compare List and Set"
		JMenuItem mntmCompareListAndSet = new JMenuItem("Compare List and Set");
		mntmCompareListAndSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "main difference");
			}
		});
		mntmCompareListAndSet.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mntmCompareListAndSet);
		
		// menu item "Exit"
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mntmExit);
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return menuBar;
	}

}