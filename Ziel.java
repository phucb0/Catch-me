package JavaCatchMe;

import java.awt.Color;

public abstract class Ziel {

	int groesse, posX, posY, vX = 1, vY = 1;
	Color farbe = new Color((int) (Math.random() * 230), (int) (Math.random() * 230),
			(int) (Math.random() * 230));
	double flaeche = 0;

	public void draw(int x, int y) {
		// System.out.println(flaeche);
	}

	public boolean getroffen(int x, int y) {
		return true;
	}

	public int geschwindigkeitX() {
		if (posX + 3 * this.groesse >= SpielFlaeche.spielBreite) {
			vX = -1;
		} else if (posX <= 0) {
			vX = 1;
		}
		return vX * CatchMe.anz;
	}

	public int geschwindigkeitY() {
		if (posY + 3 * this.groesse >= SpielFlaeche.spielHoehe) {
			vY = -1;
		} else if (posY <= 0) {
			vY = 1;
		}
		return vY * CatchMe.anz;
	}

}
