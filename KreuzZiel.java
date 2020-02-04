package JavaCatchMe;

import java.awt.Polygon;

public class KreuzZiel extends Ziel {

	public void draw(int x, int y) {
		posX = x;
		posY = y;
		SpielFlaeche.gr.setColor(farbe);
		Polygon p = new Polygon();
		p.addPoint(posX + groesse, posY);
		p.addPoint(posX + 2 * groesse, posY);
		p.addPoint(posX + 2 * groesse, posY + groesse);
		p.addPoint(posX + 3 * groesse, posY + groesse);
		p.addPoint(posX + 3 * groesse, posY + 2 * groesse);
		p.addPoint(posX + 2 * groesse, posY + 2 * groesse);
		p.addPoint(posX + 2 * groesse, posY + 3 * groesse);
		p.addPoint(posX + groesse, posY + 3 * groesse);
		p.addPoint(posX + groesse, posY + 2 * groesse);
		p.addPoint(posX, posY + 2 * groesse);
		p.addPoint(posX, posY + groesse);
		p.addPoint(posX + groesse, posY + groesse);
		SpielFlaeche.gr.fillPolygon(p);

		flaeche = groesse * groesse * 5;
		super.draw(x, y);
	}

	public boolean getroffen(int x, int y) {
		return ((x >= posX + groesse) && (x <= posX + 2 * groesse) && (y >= posY) && (y <= posY + groesse * 3))
				|| ((x >= posX) && (x <= posX + groesse * 3) && (y >= posY + groesse) && (y <= posY + groesse * 2));
	}

}
