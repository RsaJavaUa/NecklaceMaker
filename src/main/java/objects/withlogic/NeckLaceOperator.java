package objects.withlogic;

import lombok.Getter;
import lombok.Setter;
import objects.entities.Necklace;
import objects.entities.Stone;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class NeckLaceOperator {
    private final static Logger LOGGER = Logger.getLogger(NeckLaceOperator.class);

    @Getter
    @Setter
    private Necklace necklace;
    private StoneCreator stoneCreator = new StoneCreator();
    private Comparator<Stone> comparator = Comparator.comparing(Stone::getPrice);

    public NeckLaceOperator(Necklace necklace) {
        this.necklace = necklace;
    }

    public NeckLaceOperator() {
        necklace = new Necklace();
    }

    public void addStonesToNeckclace(int type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            necklace.getStones().add(stoneCreator.createStone(type));
        }
        System.out.println("You have added " + quantity + " "
                + getStonesList().get(getStonesList().size() - 1).getClass().getSimpleName() + " to you necklace");
        LOGGER.info("You have added " + quantity + " "
                + getStonesList().get(getStonesList().size() - 1).getClass().getSimpleName() + " to you necklace");
    }

    public void showNecklace() {
        System.out.println("You have next stones in you necklace:");
        getStonesList().forEach(System.out::println);
        LOGGER.info("You have next stones in you necklace:");
        getStonesList().forEach(LOGGER::info);
    }

    public void sortStonesByPrice() {
        getStonesList().sort(comparator);
    }

    public int calculatePrice() {
        return getStonesList().stream().mapToInt(Stone::getPrice).sum();
    }

    public int calculateSize() {
        return getStonesList().stream().mapToInt(Stone::getSize).sum();
    }

    public List<Stone> filterByTransparencyRange(double bottomLimit, double upperLimit) {
        return getStonesList().stream()
                .filter(stone -> stone.getTransparency() > bottomLimit)
                .filter(stone -> stone.getTransparency() < upperLimit)
                .collect(Collectors.toList());
    }

    public int getNumersOfStones(){
        return getStonesList().size();
    }

    public void removeAllStones(){
        getStonesList().removeAll(getStonesList());
    }

    private List<Stone> getStonesList(){
        return necklace.getStones();

    }
}
