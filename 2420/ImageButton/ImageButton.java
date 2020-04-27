package ImageButton;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageButton extends JFrame {

	private JPanel contentPane;
	private JButton button;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageButton frame = new ImageButton();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ImageButton() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 660, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton button = createButton();
		contentPane.add(button, BorderLayout.CENTER);
	}
	
	private JButton createButton() {
		button = new JButton();
		
		addFuctionalityToButton();
		
		try {
		    Image img = ImageIO.read(getClass().getResource("Images/Image1.png"));
		    button.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		return button;
	}
	
	private void addFuctionalityToButton() {
		
		Icon rolloverIcon = new ImageIcon(getClass().getResource("Images/Image2.png"));
		button.setRolloverIcon(rolloverIcon);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				    Image img = ImageIO.read(getClass().getResource("Images/Image3.gif"));
				    button.setRolloverEnabled(false);
				    button.setIcon(new ImageIcon(img));
				  } catch (Exception ex) {
				    System.out.println(ex);
				  }
			}
		});
	}
}