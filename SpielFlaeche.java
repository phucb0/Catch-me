package JavaCatchMe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class SpielFlaeche extends JPanel {

	Image screenImage;
	static int spielBreite = CatchMe.breite - CatchMe.menuBreite;
	static int spielHoehe = CatchMe.hoehe - CatchMe.randy;
	static Graphics gr;

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		if (screenImage == null) {
			screenImage = createImage(spielBreite, spielHoehe);
			gr = screenImage.getGraphics();
			gr.setColor(Color.BLUE);
			gr.fillRect(0, 0, spielBreite, spielHoehe);
		}
		g.drawImage(screenImage, 0, 0, spielBreite, spielHoehe, this);
	}

}
