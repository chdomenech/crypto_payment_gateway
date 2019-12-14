package ec.com.cryptogateway.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWalletsEntity is a Querydsl query type for WalletsEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWalletsEntity extends EntityPathBase<WalletsEntity> {

    private static final long serialVersionUID = 1631275706L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWalletsEntity walletsEntity = new QWalletsEntity("walletsEntity");

    public final NumberPath<Integer> atmPassword = createNumber("atmPassword", Integer.class);

    public final QBlockchainEntity blockchain;

    public final NumberPath<Integer> blockchainId = createNumber("blockchainId", Integer.class);

    public final DateTimePath<java.util.Date> creationDate = createDateTime("creationDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath privateKey = createString("privateKey");

    public final StringPath publicKey = createString("publicKey");

    public final QStoreEntity store;

    public final NumberPath<Integer> storeId = createNumber("storeId", Integer.class);

    public final StringPath wallet = createString("wallet");

    public final StringPath walletType = createString("walletType");

    public QWalletsEntity(String variable) {
        this(WalletsEntity.class, forVariable(variable), INITS);
    }

    public QWalletsEntity(Path<? extends WalletsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWalletsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWalletsEntity(PathMetadata metadata, PathInits inits) {
        this(WalletsEntity.class, metadata, inits);
    }

    public QWalletsEntity(Class<? extends WalletsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockchain = inits.isInitialized("blockchain") ? new QBlockchainEntity(forProperty("blockchain")) : null;
        this.store = inits.isInitialized("store") ? new QStoreEntity(forProperty("store")) : null;
    }

}

