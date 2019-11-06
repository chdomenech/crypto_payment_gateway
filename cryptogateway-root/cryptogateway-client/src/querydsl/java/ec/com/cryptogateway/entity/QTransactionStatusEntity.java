package ec.com.cryptogateway.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTransactionStatusEntity is a Querydsl query type for TransactionStatusEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransactionStatusEntity extends EntityPathBase<TransactionStatusEntity> {

    private static final long serialVersionUID = 163067600L;

    public static final QTransactionStatusEntity transactionStatusEntity = new QTransactionStatusEntity("transactionStatusEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final BooleanPath status = createBoolean("status");

    public QTransactionStatusEntity(String variable) {
        super(TransactionStatusEntity.class, forVariable(variable));
    }

    public QTransactionStatusEntity(Path<? extends TransactionStatusEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransactionStatusEntity(PathMetadata metadata) {
        super(TransactionStatusEntity.class, metadata);
    }

}

