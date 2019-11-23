package ec.com.cryptogateway.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * CryptoCurrency Entity
 * 
 * @author Christian Domenech
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cryptocurrency")
@ToString
public class CryptoCurrencyEntity implements Serializable{

	private static final long serialVersionUID = -4124056497641184600L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
	private Integer id;
    
    @Column(name = "smart_contract")
	private String smartContract;
    
    @Column(name = "coin_id")
	private String coinId;
    
    @Column(name = "api_url")
	private String apiUrl;
	
    @Column(name = "api_url_1")
    private String apiUrl1;
    
    @Column(name = "api_url_2")
	private String apiUrl2;
    
    @Column(name = "is_token_ethereum")
	private Boolean isTokenEthereum;
    
    @Column(name = "blockchain_id")
	private Integer blockchainId;
	
    @Column(name = "status ")
    private Boolean status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blockchain_id", referencedColumnName = "id", insertable = false, updatable = false)
	private BlockchainEntity blockchain;
}
