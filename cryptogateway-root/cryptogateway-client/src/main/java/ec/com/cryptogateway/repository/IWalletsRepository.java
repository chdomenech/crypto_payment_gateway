package ec.com.cryptogateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.cryptogateway.entity.WalletsEntity;

/**
 * WalletsRepository Repository
 * 	
 * @author Christian
 *
 */
public interface IWalletsRepository extends JpaRepository<WalletsEntity, Integer> {

}
