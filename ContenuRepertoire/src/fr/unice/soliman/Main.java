package fr.unice.soliman;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.lister(new File("C:\\Users\\Soliman\\Documents\\Scolaire\\Master 1\\Java\\tp1\\ContenuRepertoire"));
    }

    private void lister(File rep) {
        System.out.println (rep.toString());

        if (rep.isDirectory()) {
            File[] listFiles = rep.listFiles();

            for ( int i = 0; i < listFiles.length; i++) {
                // appel récursif
                lister( listFiles[i]);
            }
        }
    }
}
