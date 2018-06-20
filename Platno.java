package ladje;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Platno extends JPanel implements MouseMotionListener, MouseListener, ActionListener, MouseWheelListener{
	private static final long serialVersionUID = 1L;
	private ArrayList<Gumbi> sez;
	private Okno okno;
	private ArrayList<Ladja> Ladje;
	private ArrayList<Ladja> Potopljene;
	private ArrayList<Ladja> Napolju;
	private int koliko_ladij;
	private boolean igra;
	public Platno(Okno okno) {
		super();
		sez = new ArrayList<Gumbi>();
		Ladje = new ArrayList<Ladja>();
		Napolju = new ArrayList<Ladja>();
		Potopljene = new ArrayList<Ladja>();
		this.setOkno(okno);
		this.setBackground(Color.white);
	    addMouseMotionListener(this);
	    addMouseListener(this);
	    Napolni_Ladje();
	    Zacni();
	}
/**
 * 1 	Carrier 	5
 * 2 	Battleship 	4
 * 3 	Cruiser 	3
 * 4 	Submarine 	3
 * 5 	Destroyer 	2
 */
	public void Napolni_Ladje() {
		Napolju.clear();
		Ladje.clear();
		Potopljene.clear();
		koliko_ladij=1;
		Ladje.add(new Ladja("D", 5, 0));
		for (int i=0;i<3;i++) {
			Ladje.add(new Ladja("D", 4, 0));
			setKoliko_ladij(getKoliko_ladij()+1);
		}
		for (int i=0;i<4;i++) {
			Ladje.add(new Ladja("D", 3,0));
			setKoliko_ladij(getKoliko_ladij()+1);
		}
		for (int i=0;i<5;i++) {
			Ladje.add(new Ladja("D", 2,0));
			setKoliko_ladij(getKoliko_ladij()+1);
		}
	}
	/**
	 * Ustvari gumbe
	 * */
	public void Zacni() {
		int vrs = 16;
	    int stol = 16;
	    Gumbi gumbi;
		for (int i=0;i<vrs;i++) {
			for (int j=0;j<stol;j++) {
				gumbi = new Gumbi(i*16+j);
				gumbi.addActionListener(this);
				gumbi.addMouseListener(this);
				gumbi.addMouseWheelListener(this);
				gumbi.setPreferredSize(new Dimension(20,20));
				gumbi.setBackground(Color.BLUE);
				gumbi.setEnabled(true);
				gumbi.setLadja(null);
				gumbi.setOvira(false);
				gumbi.setBackground(Color.BLUE);
				this.add(gumbi);
				sez.add(gumbi);
				}	
		}
		repaint();
	}
	/*private void reset() {
		for (Gumbi gumb:sez) {
			this.remove(gumb);
		}
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Gumbi gumb = (Gumbi)e.getSource();
		if(isIgra()) {
			if(gumb.isOvira()&&gumb.isEnabled()) {
				gumb.setEnabled(false);
				gumb.setBackground(Color.red);
				Ladja l = gumb.getLadja();
				l.setUnicena(l.getUnicena()+1);
				if (l.Potopljena()) {
					Potopljene.add(l);
					Napolju.remove(l);
				}	
			}
			else if (Potopljene.size()==koliko_ladij) {
				setIgra(false);
				//reset();
				//Napolni_Ladje();
			    //Zacni();
			}
			else if(gumb.isEnabled())
				gumb.setBackground(Color.DARK_GRAY);
		}
		else if (Ladje.isEmpty()&&!isIgra()) {
			for (int i=0;i<sez.size();i++) {
				gumb = sez.get(i);
				gumb.setBackground(Color.BLUE);
			}
			setIgra(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!isIgra()) {
		Gumbi gumb = (Gumbi)e.getSource();
		if(!gumb.isOvira())
			pobarvaj_polje(e, Color.GREEN, false);
		else
			pobarvaj_polje(e, Color.BLUE, false);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if (!isIgra()) {
		Gumbi gumb = (Gumbi)e.getSource();
		if(!gumb.isOvira()) pobarvaj_polje(e, Color.BLUE, false);
	}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!isIgra()) pobarvaj_polje(e, Color.ORANGE, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(!isIgra()) {
		Gumbi gumb = (Gumbi)e.getSource();
		if (!Ladje.isEmpty()) {
			Ladja ladja = Ladje.get(Ladje.size()-1);
			Ladje.remove(ladja);
			if (ladja.GetSmer()=="G") ladja.SetSmer("D");
			else if(ladja.GetSmer()=="D") ladja.SetSmer("G");	
			repaint();
		Ladje.add(ladja);
		int ind = sez.indexOf(gumb);
		int[] s = new int[ladja.GetDolzina()];
		
		for (int i=ind;i<ladja.GetDolzina()+ind;i++) {
			gumb = sez.get(i);
			if(!gumb.isOvira()) {
				gumb.setBackground(Color.blue);
			}
		}
		int ix = ind;
		int t=0;
		while (t<ladja.GetDolzina()) {
			s[t] = ix;
			if (ix < 0)
				break;
			ix-=16;
			t++;
		}
		for (int i:s) {
			if(i>=0&&i<sez.size()) {
				gumb = sez.get(i);
				if(!gumb.isOvira())
					gumb.setBackground(Color.BLUE);
				}
			}
		}
		}
	}
	public Okno getOkno() {
		return okno;
	}

	public void setOkno(Okno okno) {
		this.okno = okno;
	}
	private void pobarvaj_polje(MouseEvent e, Color c, boolean stanje) {
		boolean dovoljen=true;
		if (!Ladje.isEmpty()) {
			Gumbi gumb = (Gumbi)e.getSource();
			if (!gumb.isOvira()) {
			int ind = sez.indexOf(gumb);
			Ladja ladja = Ladje.get(Ladje.size()-1);
			if(ladja.GetSmer()=="D") {
			for (int i=ind;i<ladja.GetDolzina()+ind;i++) {
				if(i<sez.size()) {
				gumb = sez.get(i);
				if (gumb.isOvira())
					dovoljen=false;
				}
			}
			for (int i=ind;i<ladja.GetDolzina()+ind;i++) {
				if(i<sez.size()) {
					gumb = sez.get(i);
					if (dovoljen&&!gumb.isOvira()&&ladja.JeDovoljenaPostavitev(16-ind%16)) {
						gumb.setBackground(c);
						if (stanje && !Ladje.isEmpty()) {
							gumb.setLadja(ladja);
							gumb.setOvira(stanje);
							Napolju.add(ladja);
							Ladje.remove(ladja);
							}
						}		
					}
				if (stanje) {
					gumb.setLadja(ladja);
					gumb.setOvira(stanje);	
				}
				}
			}
			if (ladja.GetSmer()=="G") {
				int ix = ind;
				int t=0;
				int[] s = new int[ladja.GetDolzina()];
				while (t<ladja.GetDolzina()) {
					s[t] = ix;
					if (ix < 0)
					{
						dovoljen=false;
						break;
					}
					if (gumb.isOvira()) {
						dovoljen=false;
						break;
					}	
					ix-=16;
					t++;
				}
				for (int i:s) {
					if(i<sez.size()&&i>=0) {
					gumb = sez.get(i);
					if (gumb.isOvira()) {
						dovoljen=false;
						break;
					}
					}
				}
				for(int i:s) {
					if(dovoljen&&i>=0&&i<sez.size()) {
						gumb = sez.get(i);
						gumb.setBackground(c);
						if (stanje && !Ladje.isEmpty()) {
							gumb.setLadja(ladja);
							gumb.setOvira(stanje);
							Napolju.add(ladja);
							Ladje.remove(ladja);
							}
						}
					if (stanje) {
						gumb.setLadja(ladja);
						gumb.setOvira(stanje);	
					}
					}
				}		
			}
		}
	}
	public boolean isIgra() {
		return igra;
	}
	public void setIgra(boolean igra) {
		this.igra = igra;
	}
	public ArrayList<Ladja> getPotopljene() {
		return Potopljene;
	}
	public void setPotopljene(ArrayList<Ladja> potopljene) {
		Potopljene = potopljene;
	}
	public int getKoliko_ladij() {
		return koliko_ladij;
	}
	public void setKoliko_ladij(int koliko_ladij) {
		this.koliko_ladij = koliko_ladij;
	}
}
