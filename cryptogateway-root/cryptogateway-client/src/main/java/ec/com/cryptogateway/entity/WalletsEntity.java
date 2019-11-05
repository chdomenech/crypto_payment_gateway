package ec.com.cryptogateway.entity;

import java.io.Serializable;
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
 * Wallets Entity
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
@Table(name = "wallets")
@ToString
public class WalletsEntity implements Serializable{
	
	private static final long serialVersionUID = 482985440037322613L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   
	private Integer id;
    
    @Column(name = "wallet")
	private String wallet;
	
    @Column(name = "private_key")
	private String privateKey;
	
    @Column(name = "creation_date")
	private Date creationDate;
    
    @Column(name = "blockchain")
	private String blockchain;
	
    @Column(name = "store_id")
	private Integer storeId;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id", insertable = false, updatable = false)
	private StoreEntity store;

}
