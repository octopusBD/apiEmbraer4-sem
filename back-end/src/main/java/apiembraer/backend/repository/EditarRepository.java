package apiembraer.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.SampleEntity;

@Repository
public interface EditarRepository extends JpaRepository<SampleEntity, Integer>{
	
	@Query(value = "SELECT * FROM SAMPLE WHERE ID_BOLETIM = ?1 AND ID_CHASSI = ?2",nativeQuery = true)
	Optional<SampleEntity> findBoletimByIdAndChassi(Integer idBoletim, Integer idChassi);
}