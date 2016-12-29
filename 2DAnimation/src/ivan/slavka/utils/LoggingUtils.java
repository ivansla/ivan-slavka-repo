package ivan.slavka.utils;

import java.util.HashSet;
import java.util.Set;

import android.util.Log;


public class LoggingUtils {

	public static int JAVA = 0;
	public static int ANDROID = 1;

	private static boolean LOGGING_ENABLED = true;
	private static Set<String> IGNORE_CLASSES_LIST = new HashSet<String>();

	private static int SYSTEM = LoggingUtils.ANDROID;

	public static void log(Class clazz, String tag, String message){
		if(!IGNORE_CLASSES_LIST.contains(clazz.getSimpleName())){
			log(clazz.getSimpleName() + "." + tag, message);
		}
	}

	public static void log(String tag, String message){
		if(!LOGGING_ENABLED){
			return;
		}

		switch(SYSTEM){
		case 0:
			System.out.println(tag + " : " + message);
			break;
		default:
			Log.v(tag, message);
			break;
		}
	}

	public static void setLoggingSystem(int system){
		SYSTEM = system;
	}

	public static void setLoggingEnabled(boolean loggingEnabled){
		LOGGING_ENABLED = loggingEnabled;
	}

	public static void addIgnoreClass(Class clazz){
		IGNORE_CLASSES_LIST.add(clazz.getSimpleName());
	}
}
