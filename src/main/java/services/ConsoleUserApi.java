package services;

import objects.withlogic.NeckLaceOperator;
import org.apache.log4j.Logger;
import services.necklacemaker.ConsoleNecklaceMaker;
import services.necklacemaker.NecklaceMaker;
import services.necklacemaker.RandomNecklaceMaker;

public class ConsoleUserApi {
    private final static Logger LOGGER = Logger.getLogger(ConsoleUserApi.class);

    private NeckLaceOperator neckLace;
    private UserInputService userInputService = new UserInputService();

    public void makeNecklace() {
        int howToCreateNecklace = userInputService.userInputHowToCreateNecklace();
        if (howToCreateNecklace == 3) {
            System.out.println("Ending program...Buy");
            LOGGER.info("Ending program...Buy");
            return;
        } else {
            NecklaceMaker necklaceMaker = setNecklaceMaker(howToCreateNecklace);
            neckLace = necklaceMaker.createNecklace();
        }
        while (true) {
            int action = userInputService.chooseAction();
            if (action == 6) {
                System.out.println("Ending program...Buy");
                LOGGER.info("Ending program...Buy");
                return;
            } else {
                doActionChosenByUser(action);
            }
        }
    }

    private NecklaceMaker setNecklaceMaker(int input) {
        if (input == 1) {
            return new ConsoleNecklaceMaker();

        } else {
            return new RandomNecklaceMaker();
        }
    }

    private void doActionChosenByUser(int numberOfAction) {
        switch (numberOfAction) {
            case 1:
                System.out.println("Necklace price is = " + neckLace.calculatePrice());
                LOGGER.info("Necklace price is = " + neckLace.calculatePrice());
                break;
            case 2:
                System.out.println("Total size of stones in necklace = " + neckLace.calculateSize());
                LOGGER.info("Total size of stones in necklace = " + neckLace.calculateSize());
                break;
            case 3:
                neckLace.sortStonesByPrice();
                System.out.println("Necklace is sorted by price. Here it is after sorting");
                LOGGER.info("Necklace is sorted by price. Here it is after sorting");
                neckLace.showNecklace();
                break;
            case 4:
                double[] range = userInputService.chooseTransparencyRange();
                neckLace.filterByTransparencyRange(range[0], range[1]).forEach(a -> System.out.println(a.toString()));
                break;
            case 5:
                neckLace.showNecklace();
                break;
        }
    }
}
