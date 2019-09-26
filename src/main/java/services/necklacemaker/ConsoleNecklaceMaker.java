package services.necklacemaker;

import objects.withlogic.NeckLace;
import services.UserInputService;

public class ConsoleNecklaceMaker extends NecklaceMaker {
    private NeckLace neckLace = new NeckLace();
    private UserInputService inputService = new UserInputService();

    public NeckLace createNecklace() {
        while (true) {
            int userInputStone = inputService.userInputStoneType();
            if (userInputStone == 4) {
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

