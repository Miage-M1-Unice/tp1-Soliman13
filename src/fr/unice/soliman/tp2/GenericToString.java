package fr.unice.soliman.tp2;

import java.awt.Point;
import java.awt.Polygon;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import javax.lang.model.type.PrimitiveType;

public class GenericToString {

	public static void main(String[] args) {
		GenericToString gt = new GenericToString();
		gt.go();
	}

	private void go() {
		try {
			System.out.println(new GenericToString().toString(new Point(12,24), 2) + "\n");
		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20,30, 40}, 3);  
		pol.getBounds();

		try {
			System.out.println(new GenericToString().toString(pol,2));
		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString(Object object, int profondeur) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if(profondeur <= 0) {
			return "";
		}

		Class cl = Class.forName(object.getClass().getName());
		Field[] fields = cl.getDeclaredFields();
		String contenu = "";
		for (int i = 0; i< fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			contenu += field.getName();
			contenu += "=";
			if(field.getType().isPrimitive()) {
				contenu += field.get(object);
			}
			else if(field.getType().isArray()) {
				contenu += "{";
				for(int j = 0; j < Array.getLength(fields[i].get(object)); j ++) {
					contenu += Array.get(fields[i].get(object), j);

					if(j+1<Array.getLength(fields[i].get(object))) {
						contenu += ", ";
					}
				}
				contenu += "}";
			}
			else {
				contenu += toString(field.get(object), profondeur-1);
			}
			if(i+1<fields.length) {
				contenu += "; \n";
			}
		}
		String finalString = cl.getName() + "[" + contenu + "]";
		return finalString;
	}
}
