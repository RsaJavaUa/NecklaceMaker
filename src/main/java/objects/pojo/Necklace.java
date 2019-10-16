package objects.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Necklace extends BaseEntity {
    private List<Stone> stones = new ArrayList<>();
}
