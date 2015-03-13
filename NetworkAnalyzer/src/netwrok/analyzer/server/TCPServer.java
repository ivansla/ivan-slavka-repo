package netwrok.analyzer.server;

import java.util.List;

import netwrok.analyzer.threads.ListeningThread;

public class TCPServer {

	public TCPServer(List<Integer> ports){
		for(int port : ports){
			Thread listeningThread = new Thread(new ListeningThread(port));
			listeningThread.start();
		}
	}
}
