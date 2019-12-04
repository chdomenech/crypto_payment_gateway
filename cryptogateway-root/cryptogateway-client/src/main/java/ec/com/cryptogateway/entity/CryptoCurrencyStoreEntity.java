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
 * CryptoCurrencyStore Entity
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
@Table(name = "cryptocurrency_store")
@ToString
public class CryptoCurrencyStoreEntity implements Serializable{
	
	private static final long serialVersionUID = 7317476658285364785L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
	private Integer id;
	
    @Column(name = "cryptocurrency_id")
	private Integer cryptoCurrencyId;
    
    @Column(name = "store_id")
	private Integer storeId;
    
    @Column(name = "status")
    private Boolean status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cryptocurrency_id", referencedColumnName = "id", insertable = false, updatable = false)
	private CryptoCurrencyEntity cryptocurrencyEntity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id", insertable = false, updatable = false)
	private StoreEntity storeEntity;
}
