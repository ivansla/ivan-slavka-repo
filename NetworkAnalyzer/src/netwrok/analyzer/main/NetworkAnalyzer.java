package netwrok.analyzer.main;

import java.util.ArrayList;
import java.util.List;

import netwrok.analyzer.server.TCPServer;
import netwrok.analyzer.threads.AbstractWorkerThread;
import netwrok.analyzer.threads.ClientPingingThread;
import netwrok.analyzer.threads.ClientWorkerThread;
import netwrok.analyzer.threads.WatchDogThread;

public class NetworkAnalyzer {

	private static char ENVIRONMENT = 'a';

	public static void main(String[] args) {

		int argPosition = 0;
		boolean role = false;
		boolean server = false;
		int numberOfThreads = 0;
		long byteSize = 0;
		int chunkSize = 0;
		int pausePeriodInSeconds = 0;
		int processingTimeInSeconds = 0;
		String filename = null;
		String host = "";
		List<Integer> ports = new ArrayList<Integer>();
		List<AbstractWorkerThread> threads = new ArrayList<AbstractWorkerThread>();

		if(args.length == 0){
			showHelp();
		}

		try {
			while (argPosition < args.length) {
				String size = "";
				char sizeType;
				switch (args[argPosition].charAt(1)) {
				case 's':
					if (!role) {
						argPosition++;
						role = true;
						server = true;
					} else {
						throw new RuntimeException("Invalid argument.");
					}
					break;
				case 'c':
					if (!role) {
						argPosition++;
						role = true;
						host = args[argPosition++];
					} else {
						throw new RuntimeException("Invalid argument.");
					}
					break;
				case 't':
					argPosition++;
					numberOfThreads = Integer.parseInt(args[argPosition++]);
					break;
				case 'p':
					try {
						argPosition++;
						while (true) {
							if(argPosition >= args.length){
								break;
							}
							Integer port = new Integer(args[argPosition++]);
							ports.add(port);
						}
					} catch (NumberFormatException e) {
						argPosition--;
					}
					break;
				case 'i':
					argPosition++;
					size = args[argPosition++];
					sizeType = size.charAt(size.length() - 1);
					byteSize = Long.parseLong(size.substring(0,	size.length() - 1));

					switch (sizeType) {
					case 'B':
						// byteSize is already set in case of bytes
						break;
					case 'K':
						byteSize *= 1024;
						break;
					case 'M':
						byteSize *= (1024 * 1024);
						break;
					case 'G':
						byteSize *= (1024 * 1024 * 1024);
						break;
					default:
						byteSize = 1;
						System.out.println("Incorrect byteSize, setting to default 1B.");
						break;
					}
					break;
				case 'd':
					argPosition++;
					size = args[argPosition++];
					sizeType = size.charAt(size.length() - 1);
					chunkSize = Integer.parseInt(size.substring(0,
							size.length() - 1));

					switch (sizeType) {
					case 'B':
						// byteSize is already set in case of bytes
						break;
					case 'K':
						chunkSize *= 1024;
						break;
					case 'M':
						chunkSize *= (1024 * 1024);
						break;
					case 'G':
						chunkSize *= (1024 * 1024 * 1024);
						break;
					default:
						chunkSize = 1;
						System.out.println("Incorrect chunkSize, setting to default 1B.");
						break;
					}
					break;
				case 'm':
					argPosition++;
					pausePeriodInSeconds = Integer.parseInt(args[argPosition++]);
					break;
				case 'r':
					argPosition++;
					processingTimeInSeconds = Integer.parseInt(args[argPosition++]);
					break;
				case 'f':
					argPosition++;
					filename = args[argPosition++];
					break;
				default:
					throw new RuntimeException("Invalid argument.");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			showHelp();
		}

		if(server){
			new TCPServer(ports);
		} else {

			Thread clientThread = null;
			ClientPingingThread pingingThread = null;
			for (int port : ports) {
				pingingThread = new ClientPingingThread(host, port);
				threads.add(pingingThread);
				clientThread = new Thread(pingingThread);
				clientThread.start();
			}

			ClientWorkerThread clientWorker = null;
			for (int port : ports) {
				for (int i = 0; i < numberOfThreads; i++) {
					clientWorker = new ClientWorkerThread(host, port, byteSize, chunkSize, pausePeriodInSeconds, processingTimeInSeconds, "Thread_" + port + "_" + i, ENVIRONMENT);
					threads.add(clientWorker);
					if(filename != null){
						clientWorker.loadFile(filename);
					}
					clientThread = new Thread(clientWorker);
					clientThread.start();
				}
			}

			if(numberOfThreads == 0){
				System.out.println("WARNING: you didn't put number of threads. The application will only test pinging!");
			}

			Thread watchDog = new Thread(new WatchDogThread(threads));
			watchDog.start();
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
