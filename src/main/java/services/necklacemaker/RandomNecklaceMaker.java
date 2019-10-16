package services.necklacemaker;

import objects.enums.StoneType;
import objects.withlogic.NeckLaceOperator;

import java.util.Random;

public class RandomNecklaceMaker extends NecklaceMaker {
    private NeckLaceOperator neckLace = new NeckLaceOperator();
    private Random random = new Random();

    public NeckLaceOperator createNecklace() {
        int iterationAndQuantityLimit = random.nextInt(5) + 1;
        for (int i = 0; i < iterationAndQuantityLimit; i++) {
            neckLace.addStonesToNeckclace(random.nextInt(StoneType.values().length),
                    random.nextInt(iterationAndQuantityLimit) + 1);
        }
        return neckLace;
    }
}
