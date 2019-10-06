package services.necklacemaker;

import objects.withlogic.NeckLaceOperator;
import org.apache.log4j.Logger;
import services.UserInputService;

public class ConsoleNecklaceMaker extends NecklaceMaker {
    private final static Logger LOGGER = Logger.getLogger(ConsoleNecklaceMaker.class);

    private NeckLaceOperator neckLace = new NeckLaceOperator();
    private UserInputService inputService = new UserInputService();

    public NeckLaceOperator createNecklace() {
        while (true) {
            int userInputStone = inputService.userInputStoneType();
            if (userInputStone == 4) {
                LOGGER.info("End of process of creating necklace...Bye");
                System.out.println("End of process of creating necklace...Bye");
                break;
            }
            int stoneQuantity = inputService.userInputStoneQuantity();
            neckLace.addStonesToNeckclace(userInputStone, stoneQuantity);
            if (inputService.userInputContinueLoop() == 2) {
                break;
            }
        }
        return neckLace;
    }
}

