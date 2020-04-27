package brickBreak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class BrickBreaker extends JPanel implements ActionListener,KeyListener{
	
	private int playerXposition;

	/**
	 * Create the panel.
	 */
	public BrickBreaker() {
		this.playerXposition = 500;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//Drawing BackGround
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Drawing Lower Brick
		g.setColor(Color.YELLOW);
		g.fillRect(playerXposition, 630, 200, 20);
		
		//Disposes everything when you repaint
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.playerXposition += 200;
			repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.playerXposition -= 200;
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Not Implemented In This Program
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Not Implemented In This Program
	}
}