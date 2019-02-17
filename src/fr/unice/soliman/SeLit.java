package fr.unice.soliman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class SeLit {

	private String sep;
	public SeLit() {
		this.sep = System.getProperty("file.separator");
	}

	public void setup() {
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

	void lecture(Scanner source) {
		while(source.hasNextLine()) {  
			String s = source.nextLine();
			if(!s.trim().startsWith("//")) {
				System.out.println("LU: "+s);
			}
		}  
	}  

	static public void main(String[] args) {   
		SeLit sl = new SeLit();
		sl.setup();
	}
}