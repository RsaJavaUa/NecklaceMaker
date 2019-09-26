package objects.withlogic;

import objects.pojo.Stone;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NeckLaceTest {

    private NeckLace neckLace;
    private List<Stone> stoneList;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        neckLace = new NeckLace();
        neckLace.addStonesToNeckclace(0, 1);
        neckLace.addStonesToNeckclace(2, 1);
        stoneList = neckLace.getStones();
    }

    @Test
    public void shouldReturnMoreThanInitialSize() {
        neckLace.removeAllStones();
        int initialSize = neckLace.getNumersOfStones();
        neckLace.addStonesToNeckclace(1, 1);
        assertTrue(initialSize < neckLace.getNumersOfStones());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException() {
        neckLace.addStonesToNeckclace(10, 1);
    }

    @Test
    public void shouldReturnDiamodAfterSorting() {
        neckLace.sortStonesByPrice();
        assertEquals("Diamond", stoneList.get(stoneList.size() - 1).getClass().getSimpleName());
    }

    @Test
    public void calculatePrice() {
        int result = stoneList.stream().mapToInt(Stone::getPrice).sum();
        assertEquals(result, neckLace.calculatePrice());
    }

    @Test
    public void calculateSize() {
        int size = stoneList.stream().mapToInt(Stone::getSize).sum();
        assertEquals(size, neckLace.calculateSize());
    }

    @Test
    public void filterByTransparencyRange() {
        Double transeFirstStone = stoneList.get(0).getTransparency();
        Double transeSecondStone = stoneList.get(1).getTransparency();
        double upperlimit = (transeFirstStone > transeSecondStone ?  transeSecondStone : transeFirstStone)+0.01;
        assertEquals(1, neckLace.filterByTransparencyRange(0, upperlimit).size());
    }
}