package ec.com.cryptogateway.repository;

import cryptogateway.vo.request.WalletCredentialsVO;
import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.base.IQueryDslBaseRepository;
import ec.com.cryptogateway.entity.WalletsEntity;

/**
 * WalletsRepository Repository
 * 	
 * @author Christian Domenech
 *
 */
public interface IWalletsRepository extends IQueryDslBaseRepository<WalletsEntity> {

    /**
     * 
     * @param id
     * @return
     */
   WalletVO findAllWalletsByIdStore(Integer id);
   
   /**
    * Find wallet by credentials
    * 
    * @param walletCredentialsVO
    * @return
    */
   WalletVO findWalletByCredentials(WalletCredentialsVO walletCredentialsVO);
}
