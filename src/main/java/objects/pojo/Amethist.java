package objects.pojo;

import objects.enums.StoneType;

public class Amethist extends Stone {
    public Amethist() {
        setType(StoneType.SEMIPRECIOUS);
        setPrice(150);

    }

    @Override
    public String toString() {
        return "Amethist{" +
                "size=" + getSize() +
                ", transparency=" + getTransparency() +
                ", price=" + getPrice() +
                '}';
    }
}
