package fr.unice.soliman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SeLit {

	private File repertoire_de_depart;
	private Pattern pattern;

	public SeLit() {
		this.repertoire_de_depart = new File(System.getProperty("user.dir") + "\\src\\fr\\unice\\soliman"); // répertoire des fichiers sources java
		this.pattern = Pattern.compile(".*\\.java");
	}

	public void getSingleFile() {
		InputStream is;
		try {
			is = new FileInputStream(System.getProperty("user.dir") + "\\src\\fr\\unice\\soliman\\SeLit.java");
			Scanner scanner = new Scanner(is);
			lecture(scanner);
		} catch (FileNotFoundException e) {
			System.err.println("Fichier non trouvé");
			e.printStackTrace();
		}
	}

	public void getMultipleFiles() {
		InputStream is;
		File[] files = repertoire_de_depart.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return Pattern.matches(pattern.pattern(), name);
			}
		});
		for (File file : files) {
			System.out.println("\n -- Nouveau fichier -- \n");
			if(file.isFile()) {
				try {
					is = new FileInputStream(file);
					Scanner scanner = new Scanner(is);
					lecture(scanner);
				} catch (FileNotFoundException e) {
					System.err.println("Fichier non trouvé");
					e.printStackTrace();
				}
			}
		}
	}

	void lecture(Scanner source) {
		while(source.hasNextLine()) {
			String s = source.nextLine();
			if(!s.trim().startsWith("//")) {
				System.out.println("LU: "+s);
			}
		}
	}

	private void go() {
		//		getSingleFile();
		getMultipleFiles();
	}

	static public void main(String[] args) {   
		SeLit sl = new SeLit();
		sl.go();
	}
}