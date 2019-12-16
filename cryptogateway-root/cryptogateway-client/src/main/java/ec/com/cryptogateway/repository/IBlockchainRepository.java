package ec.com.cryptogateway.repository;

import java.util.Collection;

import cryptogateway.vo.request.TransactionCheckVO;
import ec.com.cryptogateway.base.IQueryDslBaseRepository;
import ec.com.cryptogateway.entity.BlockchainEntity;

/**
 * Interface of Blockchain Repository
 * 
 * @author Christian Domenech
 *
 */
public interface IBlockchainRepository extends IQueryDslBaseRepository<BlockchainEntity> {

	/**
	 * Find all Blockchain
	 * 
	 * @return
	 */
	Collection<TransactionCheckVO> findAllBlockchain();
	
}
