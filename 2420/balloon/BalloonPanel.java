package balloon;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class BalloonPanel extends JPanel {
	private Balloon balloon;
	private JLabel labelType;
	private JButton buttonSize;
	/**
	 * Create the panel.
	 */
	public BalloonPanel(Balloon balloon) {
		this.balloon = balloon;
		
		setLayout(new BorderLayout(0, 0));
		
		buttonSize = new JButton("");
		buttonSize.setIcon(getIcon());
		add(buttonSize);
		
		labelType = createLabelType();
		add(labelType, BorderLayout.SOUTH);
		
	}

	private Icon getIcon() {
		
		ImageIcon image;
		
		switch(balloon.getSize()) {
			case XS : 
				image = new ImageIcon(BalloonPanel.class.getResource("/balloon/Images/balloon20.jpg"));
				return image;
			case S: 
				image = new ImageIcon(BalloonPanel.class.getResource("/balloon/Images/balloon40.jpg"));
				return image;
			case M: 
				image = new ImageIcon(BalloonPanel.class.getResource("/balloon/Images/balloon60.jpg"));
				return image;
			case L:
				image = new ImageIcon(BalloonPanel.class.getResource("/balloon/Images/balloon80.jpg"));
				return image;
			case XL: 
				image = new ImageIcon(BalloonPanel.class.getResource("/balloon/Images/balloon100.jpg"));
				return image;
				
		}
		
		return null;
	}

	private JLabel createLabelType() {
		JLabel labelType = new JLabel(balloon.getType());
		labelType.setBorder(new EmptyBorder(0, 0, 10, 0));
		labelType.setHorizontalAlignment(SwingConstants.CENTER);
		return labelType;
	}
	
	public void update(Balloon balloon){
		this.balloon = balloon;
		labelType.setText(balloon.getType());
		buttonSize.setIcon(getIcon());
		
	}
}