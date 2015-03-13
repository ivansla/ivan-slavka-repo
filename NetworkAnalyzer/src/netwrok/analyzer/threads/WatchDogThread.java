package netwrok.analyzer.threads;

import java.util.List;

public class WatchDogThread implements Runnable{

	private List<AbstractWorkerThread> threads;

	public WatchDogThread(List<AbstractWorkerThread> threads){
		this.threads = threads;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(5000);

				for(AbstractWorkerThread t : this.threads){
					if(!t.receivedData){
						t.incrementDeadCounter();
					}
					if((t.deadCounter >= 3) && !t.connectionFailed){
						System.out.println("Whatchdog restarting connection...");
						t.resetConnection();
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
