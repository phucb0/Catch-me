package JavaCatchMe;

public class KreisZiel extends Ziel {

	public void draw(int x, int y) {
		posX = x;
		posY = y;
		SpielFlaeche.gr.setColor(farbe);
		SpielFlaeche.gr.fillOval(x, y, groesse * 3, groesse * 3);
		flaeche = Math.PI * groesse * groesse * 9 / 4;
		super.draw(x, y);
	}

	public boolean getroffen(int x, int y) {
		double ax = posX + groesse * 3 / 2 - x;
		double ay = posY + groesse * 3 / 2 - y;
		return Math.sqrt(ax * ax + ay * ay) < groesse * 3 / 2.0;
	}

}
