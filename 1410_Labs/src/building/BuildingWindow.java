package building;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class BuildingWindow extends JFrame{
	
	private JPanel contentPane;
	
	public static void main(String [] args) {
		
		BuildingWindow frame = new BuildingWindow();
		frame.setVisible(true);
		
	}
	
	public BuildingWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		
		contentPane.add(new Building());
		
	}
}