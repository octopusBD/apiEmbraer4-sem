package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewEstatisticaQtdContidoEntity;

@Repository
public interface EstatisticaQtdContidoRepository extends JpaRepository<ViewEstatisticaQtdContidoEntity, Integer> {
	
}
