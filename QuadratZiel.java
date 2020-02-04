package JavaCatchMe;

public class QuadratZiel extends Ziel {

	public void draw(int x, int y) {
		posX = x;
		posY = y;
		SpielFlaeche.gr.setColor(farbe);
		SpielFlaeche.gr.fillRect(x, y, groesse * 3, groesse * 3);
		flaeche = groesse * 9;
		super.draw(x, y);
	}

	public boolean getroffen(int x, int y) {
		return (x >= posX) && (x < (posX + groesse * 3)) && (y >= posY) && (y < (posY + groesse * 3));
	}
}
