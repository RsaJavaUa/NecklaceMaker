package dao;

import java.util.List;
import java.util.Optional;

public interface DaoInterface<T> {
    abstract List<T> getAll();

    abstract boolean deleteEntity(T Entity);

    abstract boolean saveEntity(T entity);

    abstract boolean updateEntity(T entity);

    abstract Optional<T> getEntityById(Integer id);
}
