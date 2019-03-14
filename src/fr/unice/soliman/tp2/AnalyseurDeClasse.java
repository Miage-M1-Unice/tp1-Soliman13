package fr.unice.soliman.tp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AnalyseurDeClasse {

	private void analyseClasse(String nomClasse) throws ClassNotFoundException {
		// Récupération d'un objet de type Class correspondant au nom passé en paramètres
		Class cl = Class.forName(nomClasse);

		afficheEnTeteClasse(cl);

		System.out.println();
		afficheAttributs(cl);

		System.out.println();
		afficheConstructeurs(cl);

		System.out.println();
		afficheMethodes(cl);

		// L'accolade fermante de fin de classe !
		System.out.println("}");
	}


	/** Retourne la classe dont le nom est passé en paramètre */
	private Class getClasse(String nomClasse) throws ClassNotFoundException {
		return null;
		// CODE A ECRIRE
	}

	/** Cette méthode affiche par ex "public class Toto extends Tata implements Titi, Tutu {" */
	private void afficheEnTeteClasse(Class cl) {
		//  Affichage du modifier et du nom de la classe
		String modifier = Modifier.toString(cl.getModifiers());

		System.out.print(modifier + " ");
		System.out.print(cl.getName());

		// Récupération de la superclasse si elle existe (null si cl est le type Object)
		Class supercl = cl.getSuperclass();

		// On ecrit le "extends " que si la superclasse est non nulle et
		// différente de Object
		if(supercl != null && !supercl.equals(Object.class)) {
			System.out.println("extends" + supercl);
		}

		// Affichage des interfaces que la classe implemente
		Class[] interf = cl.getInterfaces();
		if(interf != null && interf.length > 1) {
			//formatage des string interfaces
			System.out.print(" implements");
			String interfaceNames = " ";
			for (Class i : interf) {
				interfaceNames+= i + ", ";
			}
			interfaceNames = interfaceNames.replace("interface ", "");
			int index = interfaceNames.length();
			interfaceNames = interfaceNames.substring(0, index-2); // supprime les 2 derniers caractères
			
			System.out.println(interfaceNames);
		}

		// Enfin, l'accolade ouvrante !
		System.out.print(" {\n");
	}

	private void afficheAttributs(Class cl) {
		System.out.println("// Champs");
		Field[] fields = cl.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}
	}

	private void afficheConstructeurs(Class cl) {
		System.out.println("// Constructeurs");
		Constructor[] constructors = cl.getConstructors();
		for (Constructor constructor : constructors) {
			System.out.println(constructor);
		}
	}

	private void afficheMethodes(Class cl) {
		System.out.println("// Méthodes");
		Method[] methods = cl.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
	}

	private String litChaineAuClavier() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}

	public static void main(String[] args) {
		boolean ok = false;
		AnalyseurDeClasse adc = new AnalyseurDeClasse();

		while(!ok) {
			try {
				System.out.println("Entrez le nom d'une classe (ex : java.util.Date): ");
				String nomClasse = adc.litChaineAuClavier();

				adc.analyseClasse(nomClasse);

				ok = true;
			} catch(ClassNotFoundException e) {
				System.out.println("Classe non trouvée.");
			}catch(IOException e) {
				System.out.println("Erreur d'E/S!");
			}
		}
	}

}
