package ivan.slavka.utils;

import android.util.Log;


public class LoggingUtils {

	public static int JAVA = 0;
	public static int ANDROID = 1;

	private static int SYSTEM = LoggingUtils.ANDROID;

	public static void log(String tag, String message){

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
}
