package fr.unice.soliman;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class FiltreExterne implements FilenameFilter {
	
	private Pattern pattern;
	
	public FiltreExterne(Pattern pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public boolean accept(File dir, String name) {
		File f = new File(dir, name);
		return f.isDirectory() || Pattern.matches(pattern.pattern(), f.getPath());
	}
}
