package fr.unice.soliman;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private FiltreInterne filtreInterne;
	private FiltreExterne filtreExterne;
	private FilenameFilter filtreAnonyme;
	
	private Pattern pattern;
	private Matcher matcher;

	public static void main(String[] args) {
		Main m = new Main();
		m.go();
	}

	private void go() {
		File file = new File("C:\\Users\\Soliman\\Documents\\Scolaire\\Master 1\\Java\\tp1\\ContenuRepertoire");
		pattern = Pattern.compile(".*\\.class");
		instanciationFiltres();
		lister(file);
	}

	private void instanciationFiltres() {
		filtreInterne = new FiltreInterne();
		filtreExterne = new FiltreExterne(pattern);
		filtreAnonyme = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				File f = new File(dir, name);
				matcher = pattern.matcher(f.getPath());
				return f.isDirectory() || matcher.matches();
			}
		};
	}

	private void lister(File rep) {
		File[] files = rep.listFiles(filtreExterne);
		if (files != null) {
			for (File file : files) {
				if(file.isFile()) {
					System.out.println(file.getName());
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
			matcher = pattern.matcher(f.getPath());
			return f.isDirectory() || matcher.matches();
		}
		
	}
}
