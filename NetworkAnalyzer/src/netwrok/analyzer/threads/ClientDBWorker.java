package netwrok.analyzer.threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import netwrok.analyzer.utils.LoggingManager;

public class ClientDBWorker implements Runnable{

	private ResultSet resultset;
	private String host;
	private int port;
	private String dbName;
	private String username;
	private String password;
	private Statement statement = null;
	private LoggingManager logger;
	private int threadID;
	private int pauseTime;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

	public ClientDBWorker(int threadID, String host, int port, String dbName, String username, String password, LoggingManager logger, int pauseTime){
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.password = password;
		this.username = username;
		this.logger = logger;
		this.threadID = threadID;
		this.pauseTime = pauseTime * 1000;
	}

	public void establishConnection(){
		//String url = "jdbc:oracle:thin:@" + this.host + ":" + this.port + ":" + this.dbName;
		String url = "jdbc:db2://" + this.host + ":" + this.port + "/" + this.dbName;
		
		//properties for creating connection to Oracle database
		Properties props = new Properties();
		props.setProperty("user", this.username);
		props.setProperty("password", this.password);

		//creating connection to Oracle database using JDBC
		try{
			Connection conn = DriverManager.getConnection(url,props);
			this.statement = conn.createStatement();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while(true){
			try {
				Thread.sleep(pauseTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String sql = "SELECT * FROM BPMADMIN.LSW_PO_VERSIONS";
			try{
				ResultSet result = this.statement.executeQuery(sql);
				int i = 0;
				long currentTime = System.nanoTime();
				while(result.next()){
					i++;
					//System.out.println("Current Date from Oracle : " +  result.getString("PO_GUID"));
				}
				long elapsedTime = System.nanoTime() - currentTime;
				this.logger.logMessage("Thread_" + this.threadID + ";" + this.sdf.format(new Date()) + ";" + (elapsedTime / 1000000) + ";" + i);
				
				//System.out.println("Time to fetch rows: " + (elapsedTime / 1000000) + " ms");
				//System.out.println("number of rows " + i);
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
