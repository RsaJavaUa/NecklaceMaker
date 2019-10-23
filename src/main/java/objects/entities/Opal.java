package objects.entities;

import objects.enums.StoneType;

public class Opal extends Stone {
    public Opal() {
        setType(StoneType.SEMIPRECIOUS);
        setPrice(100);

    }

    @Override
    public String toString() {
        return "Opal{" +
                "size=" + getSize() +
                ", transparency=" + getTransparency() +
                ", price=" + getPrice() + '}';
    }
}
