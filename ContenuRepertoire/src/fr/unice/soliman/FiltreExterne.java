package fr.unice.soliman;

import java.io.File;
import java.io.FilenameFilter;

public class FiltreExterne implements FilenameFilter {
	
	@Override
	public boolean accept(File dir, String name) {
		File f = new File(dir, name);
		return f.isDirectory() || f.toString().endsWith(".class");
	}

}
