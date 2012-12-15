package harness;

import java.lang.reflect.Field;

public class Opener {

	Class c;
	Object o;

	public static Opener open(Object o) {
		return new Opener(o);
	}
	
	public Opener (Object o) {
		this.o = o;
		this.c = o.getClass();
	}

	public Object get(String string)  {
		Field f = null;
		try {
			f = this.c.getDeclaredField(string);
			if(!f.isAccessible()) {
			      f.setAccessible(true);
			 }

		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			return f.get(o);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void runPrivate() {
		/*
		Method m = iw.getClass().getDeclaredMethod("incrementI");
		27
		            m.setAccessible(true);
		28
		            m.invoke(iw);
*/
	}

	
}

