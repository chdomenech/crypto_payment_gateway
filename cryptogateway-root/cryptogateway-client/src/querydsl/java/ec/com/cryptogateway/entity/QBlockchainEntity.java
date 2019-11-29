package ec.com.cryptogateway.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlockchainEntity is a Querydsl query type for BlockchainEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBlockchainEntity extends EntityPathBase<BlockchainEntity> {

    private static final long serialVersionUID = 303093658L;

    public static final QBlockchainEntity blockchainEntity = new QBlockchainEntity("blockchainEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath javaClass = createString("javaClass");

    public final StringPath name = createString("name");

    public final BooleanPath status = createBoolean("status");

    public final NumberPath<Integer> timeoutMinuts = createNumber("timeoutMinuts", Integer.class);

    public QBlockchainEntity(String variable) {
        super(BlockchainEntity.class, forVariable(variable));
    }

    public QBlockchainEntity(Path<? extends BlockchainEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlockchainEntity(PathMetadata metadata) {
        super(BlockchainEntity.class, metadata);
    }

}

