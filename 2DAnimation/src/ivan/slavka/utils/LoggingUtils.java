package ivan.slavka.utils;

import android.util.Log;


public class LoggingUtils {

	public static int JAVA = 0;
	public static int ANDROID = 1;

	private static boolean LOGGING_ENABLED = true;

	private static int SYSTEM = LoggingUtils.ANDROID;

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
}
