package ec.com.cryptogateway.base;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author cdomenech
 *
 */
public interface IQueryDslBaseRepository<T> {

    void save(T obj);
    void saveAll(Collection<T> objs);
    void update(T obj);
    T findById(Serializable id);
}
