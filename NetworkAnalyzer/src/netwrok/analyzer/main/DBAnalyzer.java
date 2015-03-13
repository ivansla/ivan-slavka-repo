package netwrok.analyzer.main;

import java.util.ArrayList;
import java.util.List;

import netwrok.analyzer.threads.AbstractWorkerThread;
import netwrok.analyzer.threads.ClientDBWorker;
import netwrok.analyzer.utils.LoggingManager;

public class DBAnalyzer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int argPosition = 0;
		int numberOfThreads = 0;
		int pausePeriodInSeconds = 0;
		int processingTimeInSeconds = 0;
		int port = 0;
		String username = "";
		String password = "";
		String dbName = "";
		List<AbstractWorkerThread> threads = new ArrayList<AbstractWorkerThread>();
		String host = "";

		if(args.length == 0){
			showHelp();
		}

		try {
			while (argPosition < args.length) {
				switch (args[argPosition].charAt(1)) {
				case 'c':
					argPosition++;
					host = args[argPosition++];
					break;
				case 't':
					argPosition++;
					numberOfThreads = Integer.parseInt(args[argPosition++]);
					break;
				case 'p':
					argPosition++;
					port = new Integer(args[argPosition++]);
					break;
				case 'u':
					argPosition++;
					username = args[argPosition++];
					break;
				case 'w':
					argPosition++;
					password = args[argPosition++];
					break;
				case 'd':
					argPosition++;
					dbName = args[argPosition++];
					break;
				case 'm':
					argPosition++;
					pausePeriodInSeconds = Integer.parseInt(args[argPosition++]);
					break;
				case 'r':
					argPosition++;
					processingTimeInSeconds = Integer.parseInt(args[argPosition++]);
					break;
				default:
					throw new RuntimeException("Invalid argument.");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			showHelp();
		}

		LoggingManager logger = new LoggingManager("db");
		logger.logMessage("ThreadID, time, processing time, rows processed");
		
		for(int i = 0; i < numberOfThreads; i++){
			ClientDBWorker dbWorker = new ClientDBWorker(i, host, port, dbName, username, password, logger, pausePeriodInSeconds);
			dbWorker.establishConnection();
			Thread t = new Thread(dbWorker);
			t.start();
		}
	}

	private static void showHelp() {
		System.out.println("-s Server");
		System.out.println("-c Client <hostname> ex.(localhost|192.152.123.11)");
		System.out.println("-t number of threads <numberOfThreads> ex.(5|10|1)");
		System.out.println("-p ports <arrayOfPorts> ex.(1235|7777 8854 1223|5686 5432)");
		System.out.println("-i byte size <#K|#M|#G> ex.(10M|5G|1K|8B) in case of incorrect size, the byteSize is 1B");
		System.out.println("-d chunk size <#K|#M|#G> ex.(10M|5G|1K|8B) in case of incorrect size, the chunkSize is 1B");
		System.out.println("-m pause period in seconds <number> ex.(1|20|60)");
		System.out.println("-r processing period per run in seconds <number> ex.(1|20|60)");
		System.out.println("-f filename");
	}
}
