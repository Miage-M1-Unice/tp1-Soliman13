package fr.unice.soliman.tp1;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private FiltreInterne filtreInterne;
	private FiltreExterne filtreExterne;
	private FilenameFilter filtreAnonyme;

	private Pattern pattern;
	private Matcher matcher;

	private File repertoire_de_depart;

	public Main() {
		this.repertoire_de_depart = new File(System.getProperty("user.dir")); // r�pertoire courant selon l'OS
	}
	public static void main(String[] args) {
		Main m = new Main();
		m.go();
	}

	private void go() {
		pattern = Pattern.compile(".*\\.class");
		instanciationFiltres();

		//		System.out.println(" -- Simple listing du r�pertoire courant -- Exo 1.1 --");
		//		ex1_1();

		//		System.out.println(" -- Listing r�cursif de tous les r�pertoires -- Exo 1.2 --");
		//		ex1_2(repertoire_de_depart);

		//		System.out.println(" -- Listing r�cursif de tous les r�pertoires avec filtre -- Exo 1.3 --");
		//		ex1_3(repertoire_de_depart);

		System.out.println(" -- Listing r�cursif de tous les r�pertoires avec SimpleFileVisitor -- Exo 1.4 --");
		try {
			ex1_4(repertoire_de_depart);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ex1_1() {
		String[] filesName = repertoire_de_depart.list();
		for (String fileName : filesName) {
			System.out.println(fileName);
		}
		System.out.println();
	}

	private void ex1_2(File dir) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if(file.isDirectory()) {
				//				System.out.println("r�pertoire : " + file);
				ex1_2(file);
				continue;
			}
			System.out.println(file.toString());
		}
	}

	private void ex1_3(File dir) {
		//		File[] files = dir.listFiles(filtreInterne);
		//		File[] files = dir.listFiles(filtreExterne);
		File[] files = dir.listFiles(filtreAnonyme);
		for (File file : files) {
			if(file.isDirectory()) {
				ex1_3(file);
				continue;
			}
			System.out.println(file.toString());
		}
	}

	public void ex1_4(File dir) throws IOException {
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
