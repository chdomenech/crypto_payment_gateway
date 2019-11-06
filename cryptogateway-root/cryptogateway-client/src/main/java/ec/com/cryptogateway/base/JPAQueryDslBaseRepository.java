package ec.com.cryptogateway.base;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

public abstract class JPAQueryDslBaseRepository <T> extends QuerydslRepositorySupport implements IQueryDslBaseRepository<T> {

    private final Class<T> domainClass;
    
    public JPAQueryDslBaseRepository(Class<T> domainClass) {
        super(domainClass);
        this.domainClass = domainClass;
    }
    
    @Override
    public void save(T obj) {
        getEntityManager().persist(obj);
    }

    @Override
    public void saveAll(Collection<T> objs) {
        objs.forEach(obj -> getEntityManager().persist(obj));
    }

    @Override
    public void update(T obj) {
        getEntityManager().merge(obj);
    }

    @Override
    public T findById(Serializable id) {
        return getEntityManager().find(domainClass, id);
    }

    protected <P> JPQLQuery<P> cloneQuery(JPAQuery<P> query) {
        return query.clone(this.getEntityManager());
    }

    protected <D> EntityPath<D> getEntityPath(Class<D> clazz) {
        return SimpleEntityPathResolver.INSTANCE.createPath(clazz);
    }

    protected EntityPath<T> getEntityPath() {
        return SimpleEntityPathResolver.INSTANCE.createPath(domainClass);
    }
}
