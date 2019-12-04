package ec.com.cryptogateway.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Store Entity
 * 
 * @author Christian Domenech
 *
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store")
@ToString
public class StoreEntity implements Serializable{

	private static final long serialVersionUID = 8237919227137676270L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   
	private Integer id;
    
    @Column(name = "store_ui")
	private String storeUI;
    
    @Column(name = "store_name")
	private String storeName;
    
    @Column(name = "user")
	private String user;
    
    @Column(name = "password")
	private String password;
    
    @Column(name = "email")
	private String email;
    
    @Column(name = "creation_date")
	private Date creationDate;
    
    @Column(name = "logo")
	private String logo;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeEntity")
    private List<CryptoCurrencyStoreEntity> cryptoCurrencies;

}
