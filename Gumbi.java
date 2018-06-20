package ladje;
import java.util.ArrayList;

import javax.swing.JButton;

public class Gumbi extends JButton {
	private static final long serialVersionUID = 1L;
	private boolean ovira;
	private int indeks;
	private Ladja ladja;
	public Gumbi(int indeks) {
		setOvira(false);
		setIndeks(indeks);
	}
	public Gumbi(boolean ovira) {
		setOvira(ovira);
	}
	public boolean isOvira() {
		return ovira;
	}
	public void setOvira(boolean ovira) {
		this.ovira = ovira;
	}
	public int lokacija_gumba(ArrayList<Gumbi> sez) {
		int k=0;
		if (sez.contains(this))
			k=sez.indexOf(this);
		return k;
	}
	public int getIndeks() {
		return indeks;
	}
	public void setIndeks(int indeks) {
		this.indeks = indeks;
	}
	public Ladja getLadja() {
		return ladja;
	}
	public void setLadja(Ladja ladja) {
		this.ladja = ladja;
	}

}
