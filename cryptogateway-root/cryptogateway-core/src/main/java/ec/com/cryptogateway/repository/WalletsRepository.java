package ec.com.cryptogateway.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.base.JPAQueryDslBaseRepository;
import ec.com.cryptogateway.entity.QWalletsEntity;
import ec.com.cryptogateway.entity.WalletsEntity;

@Lazy
@Repository
public class WalletsRepository  extends JPAQueryDslBaseRepository<WalletsEntity> implements IWalletsRepository {

    public WalletsRepository() {
        super(WalletsEntity.class);
    }

    @Override
    public WalletVO findAllWalletsByIdStore(Integer id) {
        QWalletsEntity qWalletsEntity = QWalletsEntity.walletsEntity;
        
        JPQLQuery<WalletVO> query = from(qWalletsEntity).select(Projections.bean(WalletVO.class, qWalletsEntity.privateKey, qWalletsEntity.publicKey, qWalletsEntity.blockchain, qWalletsEntity.wallet.as("walletAddress")));
        BooleanBuilder where = new BooleanBuilder();
        where.and(qWalletsEntity.storeId.eq(id));
        query.where(where);
        return query.fetchFirst();
    }

}
