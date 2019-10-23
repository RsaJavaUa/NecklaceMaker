package dao;

import objects.entities.Diamond;
import objects.enums.StoneType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistance.ConnectionFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DiamondDaoTest {

    private final DiamondDao diamondDao = new DiamondDao();

    private Diamond diamondForTesting;

    @Before
    public void setUp() {
        ConnectionFactory.setDataSource("/db/testdb.properties");
        diamondDao.deleteListOfEntities(diamondDao.getAll());
        diamondForTesting = new Diamond();
        diamondForTesting.setSize(50).setTransparency(50.00).setType(StoneType.PRECIOUS).setPrice(50);
        diamondDao.saveEntity(diamondForTesting);
    }

    @After
    public void clean() {
        diamondDao.deleteListOfEntities(diamondDao.getAll());
    }

    @Test
    public void getAllshouldReturnOneElement() {
        List<Diamond> allEntities = diamondDao.getAll();
        assertEquals(1, allEntities.size());
    }

    @Test
    public void deleteEntityByIdShouldReturnZero() {
        Diamond diamond = diamondDao.getAll().get(0);
        diamondDao.deleteEntity(diamond);
        assertEquals(0, diamondDao.getAll().size());
    }

    @Test
    public void saveEntityShouldReturnTwo() {
        Diamond anotherDimond = new Diamond();
        anotherDimond.setSize(1).setTransparency(1.00).setType(StoneType.PRECIOUS).setPrice(1);
        diamondDao.saveEntity(anotherDimond);
        assertEquals(2, diamondDao.getAll().size());
    }

    @Test
    public void updateEntity() {
        Diamond diamond = diamondDao.getAll().get(0);
        int idEntity = diamond.getId();
        int previousSize = diamond.getSize();
        diamond.setSize(75);
        diamondDao.updateEntity(diamond);
        Diamond updatedDiamond = diamondDao.getAll().get(0);

        assertNotEquals(previousSize, (int) updatedDiamond.getSize());
        assertEquals(idEntity, (int) updatedDiamond.getId());
    }

    @Test
    public void getEntityById() {
        Integer id = diamondDao.getAll().get(0).getId();
        Diamond diamondFromBase = diamondDao.getEntityById(id).get();
        assertEquals(diamondForTesting, diamondFromBase);
    }
}