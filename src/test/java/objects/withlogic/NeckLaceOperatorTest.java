package withlogic;

import objects.pojo.Stone;
import objects.withlogic.NeckLaceOperator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NeckLaceOperatorTest {

    private NeckLaceOperator neckLaceOperator
            ;
    private List<Stone> stoneList;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        neckLaceOperator = new NeckLaceOperator();
        neckLaceOperator.addStonesToNeckclace(0, 1);
        neckLaceOperator.addStonesToNeckclace(2, 1);
        stoneList = neckLaceOperator.getNecklace().getStones();
    }

    @Test
    public void shouldReturnMoreThanInitialSize() {
        neckLaceOperator.removeAllStones();
        int initialSize = neckLaceOperator.getNumersOfStones();
        neckLaceOperator.addStonesToNeckclace(1, 1);
        assertTrue(initialSize < neckLaceOperator.getNumersOfStones());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException() {
        neckLaceOperator.addStonesToNeckclace(10, 1);
    }

    @Test
    public void shouldReturnDiamodAfterSorting() {
        neckLaceOperator.sortStonesByPrice();
        assertEquals("Diamond", stoneList.get(stoneList.size() - 1).getClass().getSimpleName());
    }

    @Test
    public void calculatePrice() {
        int result = stoneList.stream().mapToInt(Stone::getPrice).sum();
        assertEquals(result, neckLaceOperator.calculatePrice());
    }

    @Test
    public void calculateSize() {
        int size = stoneList.stream().mapToInt(Stone::getSize).sum();
        assertEquals(size, neckLaceOperator.calculateSize());
        
    }

    @Test
    public void filterByTransparencyRange() {
        Double transeFirstStone = stoneList.get(0).getTransparency();
        Double transeSecondStone = stoneList.get(1).getTransparency();
        double upperlimit = (transeFirstStone > transeSecondStone ?  transeSecondStone : transeFirstStone)+0.01;
        assertEquals(1, neckLaceOperator.filterByTransparencyRange(0, upperlimit).size());
    }
}
