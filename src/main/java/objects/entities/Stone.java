package objects.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import objects.enums.StoneType;


@Getter
@Setter

@NoArgsConstructor
public abstract class Stone extends BaseEntity implements Comparable<Stone> {
    private Integer size;
    private Double transparency;
    private StoneType type;
    private Integer price;

    public Stone(Integer id, Integer size, Double transparency, StoneType type, Integer price) {
        super(id);
        this.size = size;
        this.transparency = transparency;
        this.type = type;
        this.price = price;
    }

    public Stone(Integer size, Double transparency, StoneType type, Integer price) {
        this.size = size;
        this.transparency = transparency;
        this.type = type;
        this.price = price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int compareTo(Stone stone) {
        return price.compareTo(stone.getPrice());
    }

}
