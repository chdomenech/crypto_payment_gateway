package ec.com.cryptogateway.service;

import cryptogateway.vo.response.WalletVO;

public interface IWalletService {

    WalletVO findAllWalletsByIdStore(Integer id);
    
}
