package dao;

import org.apache.log4j.Logger;
import persistance.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class AbstractDao<T> implements DaoInterface<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    public boolean createUpdate(String query, StatementMapper<T> statementMapper){
        try(PreparedStatement preparedStatement = ConnectionFactory.getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("Error while creating or updation entity", e);
        }
        return false;
    }
}
