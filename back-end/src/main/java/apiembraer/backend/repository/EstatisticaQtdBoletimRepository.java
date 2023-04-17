package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewEstatisticaQtdBoletimEntity;

@Repository
public interface EstatisticaQtdBoletimRepository extends JpaRepository<ViewEstatisticaQtdBoletimEntity, Integer> {
	
}
