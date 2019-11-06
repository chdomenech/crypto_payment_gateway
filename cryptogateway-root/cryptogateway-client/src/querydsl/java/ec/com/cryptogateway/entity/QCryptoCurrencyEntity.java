package ec.com.cryptogateway.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCryptoCurrencyEntity is a Querydsl query type for CryptoCurrencyEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCryptoCurrencyEntity extends EntityPathBase<CryptoCurrencyEntity> {

    private static final long serialVersionUID = 1135206712L;

    public static final QCryptoCurrencyEntity cryptoCurrencyEntity = new QCryptoCurrencyEntity("cryptoCurrencyEntity");

    public final StringPath apiUrl = createString("apiUrl");

    public final StringPath apiUrl1 = createString("apiUrl1");

    public final StringPath apiUrl2 = createString("apiUrl2");

    public final StringPath blockchain = createString("blockchain");

    public final StringPath coinId = createString("coinId");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isTokenEthereum = createBoolean("isTokenEthereum");

    public final StringPath smartContract = createString("smartContract");

    public final BooleanPath status = createBoolean("status");

    public QCryptoCurrencyEntity(String variable) {
        super(CryptoCurrencyEntity.class, forVariable(variable));
    }

    public QCryptoCurrencyEntity(Path<? extends CryptoCurrencyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCryptoCurrencyEntity(PathMetadata metadata) {
        super(CryptoCurrencyEntity.class, metadata);
    }

}

