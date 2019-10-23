package objects.entities;

import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import objects.enums.StoneType;

@EqualsAndHashCode
@Accessors(chain = true)
public class Diamond extends Stone {
    public Diamond() {
        setType(StoneType.PRECIOUS);
        setPrice(200);
    }

    public Diamond(Integer id, Integer size, Double transparency, StoneType type, Integer price) {
        super(id, size, transparency, type, price);
    }

    @Override
    public String toString() {
        return "Diamond{" +
                "size=" + getSize() +
                ", transparency=" + getTransparency() +
                ", price=" + getPrice() + '}';
    }
}
