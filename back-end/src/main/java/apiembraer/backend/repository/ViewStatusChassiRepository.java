package apiembraer.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewStatusChassiEntity;

@Repository
public interface ViewStatusChassiRepository extends JpaRepository<ViewStatusChassiEntity, String> {
	
	public List <ViewStatusChassiEntity> findByChassi(String chassi);
	
	@Query(value = "SELECT * FROM VIEW_STATUS_CHASSI WHERE CHASSI = ?1",nativeQuery = true)
	Optional<ViewStatusChassiEntity> findAllByChassi(String chassi);
}
