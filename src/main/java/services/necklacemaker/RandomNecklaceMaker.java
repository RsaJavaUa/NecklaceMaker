package services.necklacemaker;

import objects.enums.StoneType;
import objects.withlogic.NeckLace;

import java.util.Random;

public class RandomNecklaceMaker extends NecklaceMaker{
    private NeckLace neckLace = new NeckLace();
    private Random random = new Random();

    public NeckLace createNecklace(){
        int iterationAndQuantityLimit = random.nextInt(5)+1;
        for (int i = 0; i < iterationAndQuantityLimit; i++) {
            neckLace.addStonesToNeckclace(random.nextInt(StoneType.values().length),
                    random.nextInt(iterationAndQuantityLimit)+1);
        }
        return neckLace;
    }
}
