package ec.com.cryptogateway.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreEntity is a Querydsl query type for StoreEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStoreEntity extends EntityPathBase<StoreEntity> {

    private static final long serialVersionUID = -1519990271L;

    public static final QStoreEntity storeEntity = new QStoreEntity("storeEntity");

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final ListPath<CryptoCurrencyStoreEntity, QCryptoCurrencyStoreEntity> cryptoCurrencies = this.<CryptoCurrencyStoreEntity, QCryptoCurrencyStoreEntity>createList("cryptoCurrencies", CryptoCurrencyStoreEntity.class, QCryptoCurrencyStoreEntity.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath logo = createString("logo");

    public final StringPath password = createString("password");

    public final StringPath storeName = createString("storeName");

    public final StringPath storeUI = createString("storeUI");

    public final StringPath user = createString("user");

    public QStoreEntity(String variable) {
        super(StoreEntity.class, forVariable(variable));
    }

    public QStoreEntity(Path<? extends StoreEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStoreEntity(PathMetadata metadata) {
        super(StoreEntity.class, metadata);
    }

}

