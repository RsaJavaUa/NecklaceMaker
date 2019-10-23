package objects.entities;

import objects.enums.StoneType;

public class Amethyst extends Stone {
    public Amethyst() {
        setType(StoneType.PRECIOUS);
        setPrice(150);
    }

    @Override
    public String toString() {
        return "Amethyst{" +
                "size=" + getSize() +
                ", transparency=" + getTransparency() +
                ", price=" + getPrice() +
                '}';
    }
}
