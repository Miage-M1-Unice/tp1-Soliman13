package fr.unice.soliman;

import java.io.File;
import java.io.FilenameFilter;

public class Main {

	private FiltreInterne filtreInterne;
	private FiltreExterne filtreExterne;
	private FilenameFilter filtreAnonyme;

	public static void main(String[] args) {
		Main m = new Main();
		m.go();
	}

	private void go() {
		File file = new File("C:\\Users\\Soliman\\Documents\\Scolaire\\Master 1\\Java\\tp1\\ContenuRepertoire");
		instanciationFiltres();
		lister(file);
	}

	private void instanciationFiltres() {
		filtreInterne = new FiltreInterne();
		filtreExterne = new FiltreExterne();
		filtreAnonyme = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				File f = new File(dir, name);
				return f.isDirectory() || f.toString().endsWith(".class");
			}
		};
		
	}

	private void lister(File rep) {
		File[] files = rep.listFiles(filtreExterne);
		if (files != null) {
			for (File file : files) {
				if(file.isFile()) {
					System.out.println(file.toString());
					continue;
				}
				lister(file);
			}
		}
	}
	
	class FiltreInterne implements FilenameFilter{
		
		@Override
		public boolean accept(File dir, String name) {
			File f = new File(dir, name);
			return f.isDirectory() || f.toString().endsWith(".class");
		}
		
	}
}
