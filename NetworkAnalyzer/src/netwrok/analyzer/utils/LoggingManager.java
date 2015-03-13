package netwrok.analyzer.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoggingManager {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	private SimpleDateFormat directoryFormat = new SimpleDateFormat("yyyyMMddHHmm");
	private PrintWriter out;
	private Lock logingLock;
	private PrintWriter err = null;
	private Lock errorLock;
	private String filename;
	private String directory;
	private long rowsLogged = 0;

	public LoggingManager(String filename){

		this.logingLock = new ReentrantLock();
		this.errorLock = new ReentrantLock();
		this.filename = filename;

		try {
			//this.directory = directoryFormat.format(new Date());
			//File file = new File(".\\Logs\\" + directory + "\\" + filename + ".csv");
			File file = new File(filename + ".csv");
			//file.mkdirs();
			//file.getParentFile().mkdirs();
			this.out = new PrintWriter(new FileOutputStream(file, false));
		} catch (FileNotFoundException e) {
			System.out.println("Failed to initialize logging file");
			e.printStackTrace();
		}
	}

	public void logMessage(String message){

		this.logingLock.lock();
		if(this.rowsLogged < 1500000){
		this.rowsLogged++;
		this.out.println(message);
		this.out.flush();
		}
		this.logingLock.unlock();
	}

	public void logError(String error){
		if(this.err == null){
			try {
				//File file = new File(".\\Logs\\" + this.directory + "\\" + this.filename + ".err");
				File file = new File(this.filename + ".err");
				//file.mkdirs();
				//file.getParentFile().mkdirs();
				this.err = new PrintWriter(new FileOutputStream(file, false));
			} catch (FileNotFoundException e) {
				System.out.println("Failed to initialize error file");
				e.printStackTrace();
			}
		}
		this.err.println("Error occured at: " + this.sdf.format(new Date()) + "; Error:  " + error);
		this.err.flush();
	}
}
