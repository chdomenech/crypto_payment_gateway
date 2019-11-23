package ec.com.cryptogateway.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTransactionEntity is a Querydsl query type for TransactionEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransactionEntity extends EntityPathBase<TransactionEntity> {

    private static final long serialVersionUID = 672782462L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTransactionEntity transactionEntity = new QTransactionEntity("transactionEntity");

    public final QBlockchainEntity blockchain;

    public final NumberPath<Integer> blockchainId = createNumber("blockchainId", Integer.class);

    public final NumberPath<java.math.BigDecimal> coinPrice = createNumber("coinPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> coinsAmount = createNumber("coinsAmount", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> creationTime = createDateTime("creationTime", java.util.Date.class);

    public final QCryptoCurrencyEntity cryptoCurrency;

    public final NumberPath<Integer> cryptoCurrencyId = createNumber("cryptoCurrencyId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QStoreEntity store;

    public final NumberPath<Integer> storeId = createNumber("storeId", Integer.class);

    public final NumberPath<java.math.BigDecimal> totalPayment = createNumber("totalPayment", java.math.BigDecimal.class);

    public final StringPath transactionId = createString("transactionId");

    public final QTransactionStatusEntity transactionStatus;

    public final NumberPath<Integer> transactionStatusId = createNumber("transactionStatusId", Integer.class);

    public final QWalletsEntity wallet;

    public final NumberPath<Integer> walletId = createNumber("walletId", Integer.class);

    public QTransactionEntity(String variable) {
        this(TransactionEntity.class, forVariable(variable), INITS);
    }

    public QTransactionEntity(Path<? extends TransactionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransactionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransactionEntity(PathMetadata metadata, PathInits inits) {
        this(TransactionEntity.class, metadata, inits);
    }

    public QTransactionEntity(Class<? extends TransactionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockchain = inits.isInitialized("blockchain") ? new QBlockchainEntity(forProperty("blockchain")) : null;
        this.cryptoCurrency = inits.isInitialized("cryptoCurrency") ? new QCryptoCurrencyEntity(forProperty("cryptoCurrency"), inits.get("cryptoCurrency")) : null;
        this.store = inits.isInitialized("store") ? new QStoreEntity(forProperty("store")) : null;
        this.transactionStatus = inits.isInitialized("transactionStatus") ? new QTransactionStatusEntity(forProperty("transactionStatus")) : null;
        this.wallet = inits.isInitialized("wallet") ? new QWalletsEntity(forProperty("wallet"), inits.get("wallet")) : null;
    }

}

