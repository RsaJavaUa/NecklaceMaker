package services.necklacemaker;

import objects.withlogic.NeckLaceOperator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RandomNecklaceMakerTest {
    private RandomNecklaceMaker maker = new RandomNecklaceMaker();
    private NeckLaceOperator neckLace;

    @Before
    public void setUp() {
        neckLace = maker.createNecklace();
    }

    @Test
    public void shouldNotReturnNull() {
        assertNotNull(neckLace);
    }

    @Test
    public void sizeShouldBeInRange() {
        boolean condition = neckLace.getNumersOfStones() > 0 && neckLace.getNumersOfStones() < 37;
        assertTrue(condition);
    }
}
