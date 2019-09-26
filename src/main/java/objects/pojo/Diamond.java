package objects.pojo;

import objects.enums.StoneType;


public class Diamond extends Stone {
    public Diamond() {
        setType(StoneType.PRECIOUS);
        setPrice(200);
    }

    @Override
    public String toString() {
        return "Diamond{" +
                "size=" + getSize() +
                ", transparency=" + getTransparency() +
                ", price=" + getPrice() + '}';
    }
}
