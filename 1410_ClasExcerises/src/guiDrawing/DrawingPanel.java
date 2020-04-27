package guiDrawing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	
	private final Color[] Colors = {Color.BLUE, Color.GREEN, Color.YELLOW,
			Color.ORANGE};
	
	private final Random rand = new Random();
	
	public DrawingPanel() { 
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(Color.DARK_GRAY);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight() - 50);
		g.setColor(Color.RED);
		g.fillOval(150, 75, 150, 150);
		
		//g.setColor(Colors[rand.nextInt(Colors.length)]);
		//g.fillOval(210, 135, 30, 30);
		//g.setColor(Colors[rand.nextInt(Colors.length) ]);
		//g.fillOval(150, 135, 30, 30);
		//g.setColor(Colors[rand.nextInt(Colors.length)]);
		//g.fillOval(270, 135, 30, 30);
		
		
		for(int i = 0; i < 3; i++) {
			g.setColor(Colors[rand.nextInt(Colors.length)]);
		    g.fillOval(i * 60 + 150, 135, 30, 30);
		}
		
	}

}
