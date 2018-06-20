package ladje;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Okno extends JFrame{
	private static final long serialVersionUID = 1L;
    private Platno platno;
    public Okno() {
    	zacni();
    }
    public void zacni() {
    	platno = new Platno(this);
    	add(platno, BorderLayout.SOUTH);
    	platno.setLayout(new GridLayout(16,16));
    	platno.setIgra(false);
    	platno.setVisible(true);
    }
	public static void main(String[] args) {
		JFrame okno = new Okno();
		okno.setResizable(false);
		okno.setTitle("Potapljanje ladij");
        okno.pack();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
	}

}
