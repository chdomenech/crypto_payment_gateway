package ec.com.cryptogateway.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCryptoCurrencyEntity is a Querydsl query type for CryptoCurrencyEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCryptoCurrencyEntity extends EntityPathBase<CryptoCurrencyEntity> {

    private static final long serialVersionUID = 1135206712L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCryptoCurrencyEntity cryptoCurrencyEntity = new QCryptoCurrencyEntity("cryptoCurrencyEntity");

    public final StringPath apiUrl = createString("apiUrl");

    public final StringPath apiUrl1 = createString("apiUrl1");

    public final StringPath apiUrl2 = createString("apiUrl2");

    public final QBlockchainEntity blockchain;

    public final NumberPath<Integer> blockchainId = createNumber("blockchainId", Integer.class);

    public final StringPath coinId = createString("coinId");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isTokenEthereum = createBoolean("isTokenEthereum");

    public final StringPath smartContract = createString("smartContract");

    public final BooleanPath status = createBoolean("status");

    public QCryptoCurrencyEntity(String variable) {
        this(CryptoCurrencyEntity.class, forVariable(variable), INITS);
    }

    public QCryptoCurrencyEntity(Path<? extends CryptoCurrencyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCryptoCurrencyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCryptoCurrencyEntity(PathMetadata metadata, PathInits inits) {
        this(CryptoCurrencyEntity.class, metadata, inits);
    }

    public QCryptoCurrencyEntity(Class<? extends CryptoCurrencyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockchain = inits.isInitialized("blockchain") ? new QBlockchainEntity(forProperty("blockchain")) : null;
    }

}

