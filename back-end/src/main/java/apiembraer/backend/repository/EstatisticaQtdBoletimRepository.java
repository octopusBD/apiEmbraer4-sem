package apiembraer.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewEstatisticaQtdBoletimEntity;

@Repository
public interface EstatisticaQtdBoletimRepository extends JpaRepository<ViewEstatisticaQtdBoletimEntity, Integer> {
	
public List <ViewEstatisticaQtdBoletimEntity> findByIdChassi(Integer IdChassi);
	
	@Query(value = "SELECT * FROM VIEW_ESTATISTICA_QTD_BOLETIM WHERE ID_CHASSI = ?1",nativeQuery = true)
	Optional<ViewEstatisticaQtdBoletimEntity> findAllByidChassi(Integer IdChassi);

	
}
