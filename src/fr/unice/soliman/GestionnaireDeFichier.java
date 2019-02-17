package fr.unice.soliman;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionnaireDeFichier {

	private FiltreInterne filtreInterne;
	private FiltreExterne filtreExterne;
	private FilenameFilter filtreAnonyme;

	private Pattern pattern;
	private Matcher matcher;

	private File repertoire_de_depart;

	public void lister(String path) {
		repertoire_de_depart = new File(path);
		pattern = Pattern.compile(".*\\.class");
		instanciationFiltres();

		//		System.out.println(" -- Simple listing du répertoire courant -- Exo 1 --");
		//		ex1();

		//		System.out.println(" -- Listing récursif de tous les répertoires -- Exo 2 --");
		//		ex2(repertoire_de_depart);

//		System.out.println(" -- Listing récursif de tous les répertoires avec filtre -- Exo 3 --");
//		ex3(repertoire_de_depart);

		System.out.println(" -- Listing récursif de tous les répertoires avec SimpleFileVisitor -- Exo 4 --");
		try {
			ex4(repertoire_de_depart);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ex1() {
		String[] filesName = repertoire_de_depart.list();
		for (String fileName : filesName) {
			System.out.println(fileName);
		}
		System.out.println();
	}

	private void ex2(File dir) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if(file.isDirectory()) {
				//				System.out.println("répertoire : " + file);
				ex2(file);
				continue;
			}
			System.out.println(file.toString());
		}
	}

	private void ex3(File dir) {
//		File[] files = dir.listFiles(filtreInterne);
//		File[] files = dir.listFiles(filtreExterne);
		File[] files = dir.listFiles(filtreAnonyme);
		for (File file : files) {
			if(file.isDirectory()) {
				ex3(file);
				continue;
			}
			System.out.println(file.toString());
		}
	}

	public static void ex4(File dir) throws IOException {
		Files.walkFileTree(dir.toPath(), new VisiteurDeFichier());
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

	class FiltreInterne implements FilenameFilter{
		@Override
		public boolean accept(File dir, String name) {
			File f = new File(dir, name);
			matcher = pattern.matcher(f.getPath());
			return f.isDirectory() || matcher.matches();
		}
	}
}
