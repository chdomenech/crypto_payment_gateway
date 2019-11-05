package ec.com.cryptogateway.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
 * Transaction Entity
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
@Table(name = "transaction")
@ToString
public class TransactionEntity implements Serializable{

    private static final long serialVersionUID = -7964316296306954593L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  
	private Integer id;	
    
    @Column(name = "wallet_id")
	private Integer walletId;
    
    @Column(name = "creation_time")
	private Date creationTime;

    @Column(name = "cryptocurrency_id")
	private Integer cryptoCurrencyId;
    
    @Column(name = "store_id")
	private Integer storeId;
    
    @Column(name = "transaction_status_id ")
	private Integer transactionStatusId;
    
    @Column(name = "coins_amount")
	private BigDecimal coinsAmount;
    
    @Column(name = "coin_price")
	private BigDecimal coinPrice;
    
    @Column(name = "total_payment")
	private BigDecimal totalPayment;
    
    @Column(name = "blockchain")
   	private String blockchain;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id", insertable = false, updatable = false)
	private WalletsEntity wallet;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cryptocurrency_id", referencedColumnName = "id", insertable = false, updatable = false)
	private CryptoCurrencyEntity cryptoCurrency;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id", insertable = false, updatable = false)
	private StoreEntity store;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_status_id", referencedColumnName = "id", insertable = false, updatable = false)
	private TransactionStatusEntity transactionStatus;	

}
