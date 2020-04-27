package building;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class Building extends JPanel {

	private final Color[] Colors = { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};

	private final Random rand = new Random();

	public Building() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBackground(Color.GREEN);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight() - 50);

		g.setColor(Color.GRAY);
		g.fillRect(100, 100, 500, getHeight() - 150);

		int y = 150;
		for (int j = 0; j < 5; j++) {
			int x = 170;
			for (int i = 0; i < 4; i++) {
				g.setColor(Colors[rand.nextInt(Colors.length)]);
				g.fillRect(x, y, 50, 50);
				x += 100;
			}
			y += 100; 
		}
	}
}