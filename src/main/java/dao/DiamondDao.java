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
    private static final String SELECT_ALL = "SELECT * FROM diamond";
    private static final String SQL_INSERT = "INSERT INTO diamond " +
            "(id, size, transparency, stone_type, price) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE diamond " +
            "SET  size =?, transparency =?, stone_type=?, price=?" +
            "WHERE id=?";
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
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = ConnectionFactory.getPreparedStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return setIntoList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    private List<Diamond> setIntoList(ResultSet resultSet) throws SQLException {
        List<Diamond> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(mapToDiamond.map(resultSet));
        }
        return result;
    }

    @Override
    public boolean deleteEntityById(Diamond entity) {
        return createUpdate(DELETE_BY_ID, prepStatement -> prepStatement.setInt(1, entity.getId()));
    }

    @Override
    public boolean saveEntity(Diamond entity) {
        return createUpdate(SQL_INSERT, ps -> {
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getSize());
            ps.setFloat(3, (float) (entity.getTransparency().doubleValue()));
            ps.setString(4, entity.getType().name());
            ps.setInt(5, entity.getPrice());
        });
    }

    @Override
    public boolean updateEntity(Diamond entity) {
        return createUpdate(UPDATE, ps -> {
            ps.setInt(1, entity.getSize());
            ps.setFloat(2, (float) (entity.getTransparency().doubleValue()));
            ps.setString(3, entity.getType().name());
            ps.setInt(4, entity.getPrice());
            ps.setInt(5, entity.getId());
        });
    }

    @Override
    public Optional<Diamond> getEntityById(Integer id) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = ConnectionFactory.getPreparedStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return Optional.of(mapToDiamond.map(resultSet));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.empty();
    }
}
