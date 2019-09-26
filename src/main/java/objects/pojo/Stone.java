package objects.pojo;

import objects.enums.StoneType;

public abstract class Stone implements Comparable<Stone> {
    private Integer size;
    private Double transparency;
    private StoneType type;
    private Integer price;

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int compareTo(Stone stone) {
        return price.compareTo(stone.getPrice());
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getTransparency() {
        return transparency;
    }

    public void setTransparency(Double transparency) {
        this.transparency = transparency;
    }

    public StoneType getType() {
        return type;
    }

    public void setType(StoneType type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }
}
