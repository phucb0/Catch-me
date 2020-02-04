package JavaCatchMe;

import java.awt.Color;

public class Bewegen extends Thread {

	public void run() {
		while (true) {
			
			if (CatchMe.z != null) {
				CatchMe.cp.repaint();

				CatchMe.z.posX += CatchMe.z.geschwindigkeitX();
				CatchMe.z.posY += CatchMe.z.geschwindigkeitY();
				SpielFlaeche.gr.setColor(Color.WHITE);
				SpielFlaeche.gr.fillRect(0, 0, SpielFlaeche.spielBreite, SpielFlaeche.spielHoehe);
				CatchMe.z.draw(CatchMe.z.posX, CatchMe.z.posY);
//				System.out.println(CatchMe.z.posX + "   " + CatchMe.z.posY);

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
