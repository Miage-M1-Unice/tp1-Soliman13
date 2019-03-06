package fr.unice.soliman;

import java.awt.Point;
import java.awt.Polygon;
import java.lang.reflect.Field;

public class GenericToString {

	public static void main(String[] args) {
//		try {
//			System.out.println(new GenericToString().toString(new Point(12,24), 2));
//		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20,30, 40}, 3);  
		pol.getBounds();
		try {
			System.out.println(new GenericToString().toString(pol,2));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
		String contenuBis = "";
		for (int i = 0; i< fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			contenu += field.getName();
			contenu += "=";
			if(field.getType().isPrimitive()) {
				contenu += field.get(object);
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
