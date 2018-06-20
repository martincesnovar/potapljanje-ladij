package ladje;

public class Ladja {
private String smer;
private int dolzina;
private int unicena;
private boolean potopljena;
private int indeks;
private int rob;
public Ladja(String smer, int dolzina, int indeks) {
	this.smer = smer;
	this.dolzina = dolzina;
}
public String GetSmer() {
	return this.smer;
}
public int GetDolzina() {
	return this.dolzina;
}
public void SetSmer(String smer) {
	this.smer =smer;
}
public void SetDolzina(int dolzina) {
	this.dolzina = dolzina;
}
public void SetPotopljena(boolean potopljena) {
	this.potopljena = potopljena;
}
public boolean Potopljena() {
	if (getUnicena()==GetDolzina()) {
		SetPotopljena(true);
		return true;
	}
	else {
		SetPotopljena(false);
		return false;
	}
}
public boolean GetPotopljena() {
	return this.potopljena;
}
/**
 * Preveri ali je dovoljena postavitev
 * @param k velikost igralnega polja - zaèetek ladje
 * @return Ali je dovoljena postavitev
 */
public boolean JeDovoljenaPostavitev(int k) {
	return k >= GetDolzina();
}
public int getUnicena() {
	return unicena;
}
public void setUnicena(int unicena) {
	this.unicena = unicena;
}
public int getIndeks() {
	return indeks;
}
public void setIndeks(int indeks) {
	this.indeks = indeks;
}
public int getRob() {
	return rob;
}
public void setRob(int rob) {
	this.rob = rob;
}
}
