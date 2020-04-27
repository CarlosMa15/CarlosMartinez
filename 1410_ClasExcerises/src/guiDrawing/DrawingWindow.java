package guiDrawing;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class DrawingWindow extends JFrame{
	
	private JPanel contentPane;
	
	public static void main(String [] args) {
		
		DrawingWindow frame = new DrawingWindow();
		frame.setVisible(true);
		
	}
	
	public DrawingWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		
		contentPane.add(new DrawingPanel());
		
	}
}
