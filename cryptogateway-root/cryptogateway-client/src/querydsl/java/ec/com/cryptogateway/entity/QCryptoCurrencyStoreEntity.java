package ec.com.cryptogateway.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCryptoCurrencyStoreEntity is a Querydsl query type for CryptoCurrencyStoreEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCryptoCurrencyStoreEntity extends EntityPathBase<CryptoCurrencyStoreEntity> {

    private static final long serialVersionUID = 600042479L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCryptoCurrencyStoreEntity cryptoCurrencyStoreEntity = new QCryptoCurrencyStoreEntity("cryptoCurrencyStoreEntity");

    public final QCryptoCurrencyEntity cryptocurrencyEntity;

    public final NumberPath<Integer> cryptoCurrencyId = createNumber("cryptoCurrencyId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath status = createBoolean("status");

    public final QStoreEntity storeEntity;

    public final NumberPath<Integer> storeId = createNumber("storeId", Integer.class);

    public QCryptoCurrencyStoreEntity(String variable) {
        this(CryptoCurrencyStoreEntity.class, forVariable(variable), INITS);
    }

    public QCryptoCurrencyStoreEntity(Path<? extends CryptoCurrencyStoreEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCryptoCurrencyStoreEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCryptoCurrencyStoreEntity(PathMetadata metadata, PathInits inits) {
        this(CryptoCurrencyStoreEntity.class, metadata, inits);
    }

    public QCryptoCurrencyStoreEntity(Class<? extends CryptoCurrencyStoreEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cryptocurrencyEntity = inits.isInitialized("cryptocurrencyEntity") ? new QCryptoCurrencyEntity(forProperty("cryptocurrencyEntity"), inits.get("cryptocurrencyEntity")) : null;
        this.storeEntity = inits.isInitialized("storeEntity") ? new QStoreEntity(forProperty("storeEntity")) : null;
    }

}

