package ec.com.cryptogateway.service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.CoingeckoApiVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.StoreVO;
import ec.com.cryptogateway.repository.ICryptoCurrencyStoreRepository;
import ec.com.cryptogateway.repository.IStoreRepository;
import ec.com.cryptogateway.repository.vo.CryptoCurrencyVO;
import ec.com.cryptogateway.utils.ApisUtil;

/**
 * Store Service
 * 
 * @author Christian
 *
 */
@Service
public class StoreService implements IStoreService{
    
    @Autowired
    private IStoreRepository storeRepository;
    
    @Autowired
    private ICryptoCurrencyStoreRepository cryptoCurrencyStoreRepository;

   
    /**
     * Find all information of the cryptoCurrency
     * 
     */
	@Override
	public List<StoreCryptoCurrenciesVO> findCoinsForUIstore(StoreQueryVO storeQueryVO) {

		Collection<CryptoCurrencyVO> apis = null;
		
		if(StringUtils.isEmpty(storeQueryVO.getCoinId())) {
			apis = cryptoCurrencyStoreRepository.getCryptoCurrencysApis(storeQueryVO.getStoreUI());	
		}else {
			apis = cryptoCurrencyStoreRepository.getCryptoCurrencysApisByCoinID(storeQueryVO.getStoreUI(),storeQueryVO.getCoinId());			
		}
		 	
		if(!CollectionUtils.isEmpty(apis)){
			ArrayList<StoreCryptoCurrenciesVO> cryptos = new ArrayList<>();
			apis.forEach(data -> {
				CoingeckoApiVO coingeckoApiVO= ApisUtil.getCryptoCurrencyInfo(data.getApi1()); 
				if(coingeckoApiVO!=null) {
					coinGeckoApiInformation(coingeckoApiVO, data, storeQueryVO, cryptos);
				}
			});	
			
			return cryptos;
		}
		
		return null;
	}
	
	/**
	 * Method for return the coingecko api information
	 * 
	 * @param coingeckoApiVO
	 * @param data
	 * @param storeQueryVO
	 * @param cryptos
	 */
	public void coinGeckoApiInformation(CoingeckoApiVO coingeckoApiVO, CryptoCurrencyVO data, StoreQueryVO storeQueryVO, 
			ArrayList<StoreCryptoCurrenciesVO> cryptos ) {
		
		StoreCryptoCurrenciesVO storeCryptoCurrencysVO = new StoreCryptoCurrenciesVO();
		storeCryptoCurrencysVO.setCryptoCurrencyName(coingeckoApiVO.getName());
		storeCryptoCurrencysVO.setCoinId(data.getCoinId());
		storeCryptoCurrencysVO.setIdCoin(data.getIdCoin());
		storeCryptoCurrencysVO.setCryptoCurrencyLogo(coingeckoApiVO.getImage().get("thumb"));
		storeCryptoCurrencysVO.setTotalPayment(storeQueryVO.getTotalPayment());
		storeCryptoCurrencysVO.setBlockchain(data.getBlockchain());
		storeCryptoCurrencysVO.setIdStore(data.getIdStore());
		
		if(coingeckoApiVO.getMarketData()!=null) {			
			
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, Double> marketPrice = 
			(LinkedHashMap<String, Double>) coingeckoApiVO.getMarketData().get("current_price");
			
			Double priceUSD = marketPrice.get("usd");
			BigDecimal priceCoin = new BigDecimal(priceUSD).setScale(2, RoundingMode.HALF_DOWN);						
			storeCryptoCurrencysVO.setCryptoCurrencyPrice(priceCoin);
			
			BigDecimal total = storeQueryVO.getTotalPayment().divide(priceCoin, 4, RoundingMode.HALF_DOWN);
			storeCryptoCurrencysVO.setCryptoCurrencyConversion(total);
		}
		cryptos.add(storeCryptoCurrencysVO);
		
	}

	/**
	 * 
	 */
	@Override
	public StoreVO findUserByCredentials(CredentialsVO credentialsVO) {	
		return storeRepository.findStoreByCredentials(credentialsVO);
	}

	@Override
	public ResponseVO saveStore(StoreVO storeVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseVO resendPassword(CredentialsVO credentialsVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseVO savePassword(CredentialsVO credentialsVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseVO saveCoinsConfiguration(Collection<cryptogateway.vo.request.CryptoCurrencyVO> coins) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseVO updateStore(StoreVO storeVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
