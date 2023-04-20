package apiembraer.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewEstatisticaQtdContidoEntity;

@Repository
public interface EstatisticaQtdContidoRepository extends JpaRepository<ViewEstatisticaQtdContidoEntity, Integer> {
	
public List <ViewEstatisticaQtdContidoEntity> findByChassi(String chassi);
	
	@Query(value = "SELECT * FROM VIEW_ESTATISTICA_QTD_CONTIDO WHERE CHASSI = ?1",nativeQuery = true)
	Optional<ViewEstatisticaQtdContidoEntity> findAllByChassi(String chassi);

	
}
