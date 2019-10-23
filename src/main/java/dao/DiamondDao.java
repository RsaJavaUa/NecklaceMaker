package dao;

import objects.entities.Diamond;
import objects.enums.StoneType;
import org.apache.log4j.Logger;
import persistance.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DiamondDao extends AbstractDao<Diamond> {
    private static final Logger LOGGER = Logger.getLogger(DiamondDao.class);

    private static final String SELECT_BY_ID = "SELECT * FROM diamond WHERE id=?";
    private static final String SELECT_BY_FIELDS = "SELECT * FROM diamond WHERE size=? and price=? and stone_type=?" +
            " and transparency=?";
    private static final String SELECT_ALL = "SELECT * FROM diamond";
    private static final String SQL_INSERT = "INSERT INTO diamond " +
            "(size, transparency, stone_type, price) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE diamond " +
            "SET  size =?, transparency =?, stone_type=?, price=?" +
            " WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM diamond WHERE id=?";

    private ResultSetMapper<Diamond> mapToDiamond = rs -> new Diamond(rs.getInt("id"), rs.getInt("size"),
            rs.getDouble("transparency"), setStoneTypeByString(rs.getString("stone_type")), rs.getInt("price"));

    private StoneType setStoneTypeByString(String type) {
        switch (type) {
            case "PRECIOUS":
                return StoneType.PRECIOUS;
            case "SEMIPRECIOUS":
                return StoneType.SEMIPRECIOUS;
            case "NOTPRECIOUS":
                return StoneType.NOTPRECIOUS;
        }
        return null;
    }

    @Override
    public List<Diamond> getAll() {
        return getAll(SELECT_ALL, mapToDiamond);
    }

    private List<Diamond> setIntoList(ResultSet resultSet) throws SQLException {
        List<Diamond> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(mapToDiamond.map(resultSet));
        }
        return result;
    }

    public boolean deleteEntitiesByValue(Diamond entity) {
        getDiamondsFromBaseByValues(entity)
                .forEach(element -> createUpdate(DELETE_BY_ID, ps -> ps.setInt(1, element.getId())));
        return true;
    }

    @Override
    public boolean deleteEntity(Diamond entity) {
        return createUpdate(DELETE_BY_ID,
                preparedStatement -> preparedStatement.setInt(1, getDiamondsFromBaseByValues(entity).get(0).getId()));
    }

    public void deleteListOfEntities(List<Diamond> list) {
        list.forEach(element -> createUpdate(DELETE_BY_ID,
                preparedStatement -> preparedStatement.setInt(1, element.getId())));
    }

    @Override
    public boolean saveEntity(Diamond entity) {
        return createUpdate(SQL_INSERT, ps -> {
            ps.setInt(1, entity.getSize());
            ps.setFloat(2, (float) (entity.getTransparency().doubleValue()));
            ps.setString(3, entity.getType().name());
            ps.setInt(4, entity.getPrice());
        });
    }

    @Override
    public boolean updateEntity(Diamond entity) {
        return createUpdate(UPDATE, ps -> {
            ps.setInt(5, entity.getId());
            ps.setInt(1, entity.getSize());
            ps.setFloat(2, (float) (entity.getTransparency().doubleValue()));
            ps.setString(3, entity.getType().name());
            ps.setInt(4, entity.getPrice());
        });
    }

    @Override
    public Optional<Diamond> getEntityById(Integer id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = ConnectionFactory.getPreparedStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return Optional.of(mapToDiamond.map(resultSet));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    private List<Diamond> getDiamondsFromBaseByValues(Diamond diamond) {
        StatementMapper<Diamond> byFieldsMapper = preparedStatement -> {
            preparedStatement.setInt(1, diamond.getSize());
            preparedStatement.setInt(2, diamond.getPrice());
            preparedStatement.setString(3, diamond.getType().name());
            preparedStatement.setFloat(4, diamond.getTransparency().floatValue());

        };
        List<Diamond> all = getAll(SELECT_BY_FIELDS, byFieldsMapper, mapToDiamond);
        if (all.size() > 0) {
            return all;
        }
        return Collections.emptyList();
    }
}
