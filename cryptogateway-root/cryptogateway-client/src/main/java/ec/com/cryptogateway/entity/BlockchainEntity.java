package ec.com.cryptogateway.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BlockChain Entity
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
@Table(name = "blockchain")
@ToString
public class BlockchainEntity implements Serializable {
	
	private static final long serialVersionUID = -6631629550829510971L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
	private Integer id;
	
    @Column(name = "name")
	private String name;
    
    @Column(name = "java_class")
	private String javaClass;
    
    @Column(name = "status")
	private Boolean status;
    
    @Column(name = "timeout_minuts")
    private Integer timeoutMinuts;
}
