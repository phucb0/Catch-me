package JavaCatchMe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import CatchMeBuchham.gFl;

public class CatchMe extends JFrame {

	protected static final int breite = 800, hoehe = 450;
	static int randx = 25;
	static int randy = 20; // Hoehe des Titels!
	static int menuBreite = 250;
	static int anz = -1, maxanz = 10;
	long msec;
	String prot = "";
	double gFlaeche;
	int treffer;
	int fehler = 0;
	int pkt;
	int zgr;

	static SpielFlaeche cp = new SpielFlaeche();

	static Ziel z;

	static Bewegen bewegen = new Bewegen();

	JTextField tgroesse = new JTextField("20", 10);
	JTextField tanzahl = new JTextField("10", 10);
	JTextArea tzeit = new JTextArea("");
	JLabel lgroesse = new JLabel("Groesse des Ziels:");
	JLabel lzeit = new JLabel("Groesse des Ziels:");
	JLabel lanzahl = new JLabel("Ziele pro Durchgang:");
	JLabel ltreffe = new JLabel("Getroffene Ziele: ");
	JLabel lfehler = new JLabel("Fehler: " + fehler);
	JComboBox<String> cziel = new JComboBox<String>();

	JButton buttonGo = new JButton("Go!");
	JButton bhilfehin = new JButton("Hilfe");
	JButton buttonBeenden = new JButton("Beenden");
	JButton bhilfeweg = new JButton("Hilfe weg");
	JTextArea tahilfe = new JTextArea();
	JTextArea tauswertung = new JTextArea();

	// Konstuktor:
	public CatchMe(String title) {
		super(title);

		int frameWidth = breite;
		int frameHeight = hoehe;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // Toolkit ist eine abstrakte Klasse
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		setLayout(null);

		add(tgroesse);
		add(lgroesse);
		add(tzeit);
		add(buttonGo);
		add(buttonBeenden);
		add(tanzahl);
		add(lanzahl);
		add(cziel);
		add(bhilfehin);
		add(cp);
		add(ltreffe);
		add(lfehler);

		cp.setBounds(0, 0, SpielFlaeche.spielBreite, SpielFlaeche.spielHoehe);

		int mx = frameWidth - menuBreite;
		tgroesse.setBounds(mx + 150, 20, 40, 20);
		tanzahl.setBounds(mx + 150, 50, 40, 20);
		lgroesse.setBounds(mx + 10, 20, 180, 20);
		lanzahl.setBounds(mx + 10, 50, 180, 20);

		cziel.setBounds(mx + 50, 80, 110, 20);
		cziel.addItem("Quadrat");
		cziel.addItem("Kreis");
		cziel.addItem("Kreuz");
		cziel.addItem("Zufall");
		cziel.setSelectedItem("Quadrat");

		ltreffe.setBounds(mx + 50, 110, 180, 20);

		tzeit.setBounds(mx + 50, 140, 100, 160);

		lfehler.setBounds(mx + 50, 310, 100, 20);
		lfehler.setForeground(Color.RED);

		buttonGo.setBounds(mx + 50, 340, 100, 20);
		bhilfehin.setBounds(mx + 50, 370, 100, 20);
		bhilfeweg.setBounds(mx + 50, 370, 100, 20);
		buttonBeenden.setBounds(mx + 50, 400, 100, 20);

		tahilfe.setBounds(10, 10, SpielFlaeche.spielBreite - 20, SpielFlaeche.spielHoehe - 20);
		tahilfe.setText("Hier steht bald alles, was man wissen muss, \num mit dem CatchMe-Program zu arbeiten.");
		tahilfe.setEditable(false);
		tauswertung.setBounds(10, 10, SpielFlaeche.spielBreite - 20, SpielFlaeche.spielHoehe - 20);

		setBackground(Color.LIGHT_GRAY);

		// Ereignisse:
		buttonEvent e = new buttonEvent();
		buttonGo.addActionListener(e);
		buttonBeenden.addActionListener(e);
		bhilfehin.addActionListener(e);
		bhilfeweg.addActionListener(e);

		mouseEvent me = new mouseEvent();
		cp.addMouseListener(me);

	}

	public class mouseEvent implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			int mx = e.getX(); // gibt Position zurueck!
			int my = e.getY();

			if (anz > 0) {

				if (z.getroffen(mx, my)) {
					long vzeit = new Date().getTime() - msec;
					prot += anz + ". " + vzeit / 1000. + " sek\n";
					gFlaeche += z.flaeche;
					tzeit.setText(prot);
					Toolkit.getDefaultToolkit().beep(); // der Ton *beep*
					treffer++;
				} else {
					prot += anz + ". Fehler\n"; 
					tzeit.setText(prot);
					fehler++;
					lfehler.setText("Fehler: " + fehler);
					if (fehler == 3) {
						anz = -1;
						auswertung();
					}
					Toolkit.getDefaultToolkit().beep();
				}

				if (anz < maxanz) {
					anz++;
					z.farbe = new Color((int) (Math.random() * 230), (int) (Math.random() * 230),
							(int) (Math.random() * 230));
					einZiel();
				} else {
					anz = -1;
					auswertung();
				}

			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public class buttonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String option = e.getActionCommand();

			if (option.equals("Go!")) {
				go();
			} else if (option.equals("Hilfe")) {
				remove(bhilfehin);
				add(bhilfeweg);
				remove(tauswertung);
				remove(cp);
				add(tahilfe);
				repaint();
			} else if (option.equals("Hilfe weg")) {
				remove(tahilfe);
				remove(bhilfeweg);
				add(bhilfehin);
				add(cp);
				repaint();
			} else if (option.equals("Beenden")) {
				System.exit(0);
			}
		}
	}

	public void go() {
		add(cp);
		remove(tauswertung);
		remove(tahilfe);
		double i;
		String zf = (String) cziel.getSelectedItem();
		pkt = 0;
		treffer = 0;

		if (zf.equals("Kreis")) {
			z = new KreisZiel();
		} else if (zf.equals("Quadrat")) {
			z = new QuadratZiel();
		} else if (zf.equals("Kreuz")) {
			z = new KreuzZiel();
		} else if (zf.equals("Zufall")) {
			i = Math.random() * 3;
			if (i >= 0 && i < 1) {
				z = new KreisZiel();
			} else if (i >= 1 && i < 2) {
				z = new QuadratZiel();
			} else if (i >= 2 && i <= 3) {
				z = new KreuzZiel();
			}
		}

		zgr = Integer.parseInt(tgroesse.getText()); // Integer ist eine Klasse!
		z.groesse = zgr;

		einZiel();
		msec = (new Date()).getTime();
		prot = "";
		tzeit.setText(prot);
		maxanz = Integer.parseInt(tanzahl.getText());
		anz = 1;

		bewegen.start();

	}

	public void einZiel() {
		int x, y;
		SpielFlaeche.gr.setColor(Color.WHITE);
		SpielFlaeche.gr.fillRect(0, 0, SpielFlaeche.spielBreite, SpielFlaeche.spielHoehe);
		x = (int) (Math.random() * (SpielFlaeche.spielBreite - 3 * z.groesse));
		y = (int) (Math.random() * (SpielFlaeche.spielHoehe - 3 * z.groesse));
		z.draw(x, y);
	}

	public void auswertung() {
		add(tauswertung);
		remove(cp);
		remove(tahilfe);

		tauswertung.setFont(new Font("Arial", 0, 30));
		tauswertung.setForeground(Color.blue.darker()); // Vordergrund

		msec = (new Date()).getTime() - msec;

		tauswertung.setBackground(Color.white);

		if (treffer > fehler && fehler <= 2) {
			gFlaeche = Math.round(gFlaeche);
			double pkt = Math.round((((treffer * treffer) - (2 * fehler) - (gFlaeche / 2000.)) / (msec / 1000)) * 100);
			if (pkt <= 0)
				pkt = 0;
			tauswertung.setText("Treffer:\t" + treffer + "\n" + "Fehler:\t" + fehler + "\n" + "Zeit:\t" + msec / 1000.
					+ " sek\n" + "Gesamtflaeche:\t" + gFlaeche + "\n--------------------------------------\n\t" + pkt);

		} else {
			tauswertung.setForeground(Color.red);
			tauswertung.setText("Versuch's nochmal");
		}
		fehler = 0;
		lfehler.setText("Fehler: " + fehler);
	}

	public static void main(String[] args) {
		CatchMe gui = new CatchMe("Catch Me");
		gui.setVisible(true);
	}

}
