package ivan.slavka.test;

import ivan.slavka.controllers.EconomyController;
import ivan.slavka.enums.EventSpriteEnum;
import ivan.slavka.enums.InputControlEnum;
import ivan.slavka.generators.EventGenerator;
import ivan.slavka.interfaces.IEvent;
import ivan.slavka.utils.LoggingUtils;

import java.util.Random;


public class EventSimulator {

	private static int ITERATIONS = 1000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoggingUtils.setLoggingSystem(LoggingUtils.JAVA);
		LoggingUtils.setLoggingEnabled(false);

		EconomyController controller = new EconomyController(false);
		EventGenerator eventGenerator = new EventGenerator(controller);

		Random roll = new Random();

		double wins = 0d;
		double losses = 0d;
		int minimumTurns = 0;
		int maximumTurns = 0;
		long totalTurns = 0L;
		long maximumGlobalTime = 0;
		long minimumGlobalTime = 0;

		long startTime = System.currentTimeMillis();

		for(int i = 0; i < ITERATIONS; i++){
			controller.restartGame();

			int processedTurns = 0;
			int intervalProcessedTurns = 0;
			long minimumTimeToProcess = 0;
			long maximumTimeToProcess = 0;
			long processedTurnStartTime = System.currentTimeMillis();

			while(!controller.isGameOver()){
				processedTurns++;
				intervalProcessedTurns++;
				if(intervalProcessedTurns >= 1000){
					long timeToProcess = (System.currentTimeMillis() - processedTurnStartTime);
					if(minimumTimeToProcess == 0 || minimumTimeToProcess > timeToProcess){
						minimumTimeToProcess = timeToProcess;
					}

					if(maximumTimeToProcess == 0 || maximumTimeToProcess < timeToProcess){
						maximumTimeToProcess = timeToProcess;
					}

					processedTurnStartTime = System.currentTimeMillis();
					intervalProcessedTurns = 0;
				}

				IEvent event = eventGenerator.generateEvent(controller.getLevel());

				if(controller.getFoodIncome() < 2){
					if(event.getSecondaryEffect().getEventResources()[0].getResource().equals(EventSpriteEnum.FOOD_WORKER)){
						controller.processEvent(event, InputControlEnum.RIGHT);
						continue;
					}
				}

				controller.processEvent(event, InputControlEnum.LEFT);
			}

			if(controller.isVictory()){
				wins++;
			} else {
				losses++;
			}

			if(minimumTurns == 0 || minimumTurns > processedTurns){
				minimumTurns = processedTurns;
			}

			if(maximumTurns == 0 || maximumTurns < processedTurns){
				maximumTurns = processedTurns;
			}

			totalTurns += processedTurns;

			System.out.println("Maximum time to process 1000 turns: " + maximumTimeToProcess);
			System.out.println("Minimum time to process 1000 turns: " + minimumTimeToProcess);
			System.out.println("Simulation ended after: " + processedTurns + " turns; isVictory: " + controller.isVictory());

			if((minimumGlobalTime == 0 || minimumGlobalTime > minimumTimeToProcess) && minimumTimeToProcess != 0){
				minimumGlobalTime = minimumTimeToProcess;
			}

			if((maximumGlobalTime == 0 || maximumGlobalTime < maximumTimeToProcess) && maximumTimeToProcess != 0){
				maximumGlobalTime = maximumTimeToProcess;
			}
		}

		System.out.println("Maximum Global time to process 1000 turns: " + maximumGlobalTime);
		System.out.println("Minimum Global time to process 1000 turns: " + minimumGlobalTime);
		System.out.println("Time to process: " + (System.currentTimeMillis() - startTime));
		System.out.println("Win/Loss ratio: " + (wins/losses));
		System.out.println("Maximum/Minimum turns: " + maximumTurns + "/" + minimumTurns);
		System.out.println("Average turns: " + (totalTurns / ITERATIONS));
	}
}
