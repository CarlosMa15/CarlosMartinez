package brickBreak;

//Import Statement
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BrickBreakerApp extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrickBreakerApp frame = new BrickBreakerApp();
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
	public BrickBreakerApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(30, 50, 1200, 700);
		JPanel contentPane = createContentPanel();
		setContentPane(contentPane);
		contentPane.add(new BrickBreaker());
	}
	
	/**
	 * Creates the Content Panel that holds everything
	 * @return the Content panel
	 */
	private JPanel createContentPanel() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		return contentPane;
	}
}