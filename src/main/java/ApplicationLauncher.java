import dao.DiamondDao;
import objects.entities.Diamond;

import java.util.Optional;

public class ApplicationLauncher {

    public static void main(String[] args) {
        DiamondDao diamondDao = new DiamondDao();
        Optional<Diamond> entityById = diamondDao.getEntityById(1);
        System.out.println(entityById.get());
    }
}
