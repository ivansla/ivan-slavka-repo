package ivan.slavka.test;

import ivan.slavka.controllers.EconomyController;
import ivan.slavka.enums.EventSpriteEnum;
import ivan.slavka.enums.InputControlEnum;
import ivan.slavka.generators.EventGenerator;
import ivan.slavka.interfaces.IEvent;
import ivan.slavka.utils.LoggingUtils;


public class EventSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoggingUtils.setLoggingSystem(LoggingUtils.JAVA);

		EconomyController controller = new EconomyController(false);
		controller.restartGame();
		EventGenerator eventGenerator = new EventGenerator(controller);

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
		System.out.println("Simulation ended after: " + processedTurns + " turns");
	}

}
