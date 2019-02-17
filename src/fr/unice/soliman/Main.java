package fr.unice.soliman;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		m.go();
	}

	private void go() {
		String dirStart = System.getProperty("user.dir"); // répertoire courant selon l'OS
		GestionnaireDeFichier gf = new GestionnaireDeFichier();
		gf.lister(dirStart);
	}
}
