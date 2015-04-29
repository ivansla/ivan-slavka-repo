package ivan.slavka.test;

import ivan.slavka.controllers.EconomyController;
import ivan.slavka.enums.EventSpriteEnum;
import ivan.slavka.enums.InputControlEnum;
import ivan.slavka.generators.EventGenerator;
import ivan.slavka.interfaces.IEvent;
import ivan.slavka.utils.LoggingUtils;

import java.util.Random;


public class EventSimulator {

	private static int ITERATIONS = 100;
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

		long startTime = System.currentTimeMillis();

		for(int i = 0; i < ITERATIONS; i++){
			controller.restartGame();

			int processedTurns = 0;
			while(!controller.isGameOver()){
				processedTurns++;
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

			System.out.println("Simulation ended after: " + processedTurns + " turns; isVictory: " + controller.isVictory());
		}

		System.out.println("Time to process: " + (System.currentTimeMillis() - startTime));
		System.out.println("Win/Loss ratio: " + (wins/losses));
		System.out.println("Maximum/Minimum turns: " + maximumTurns + "/" + minimumTurns);
		System.out.println("Average turns: " + (totalTurns / ITERATIONS));
	}
}
