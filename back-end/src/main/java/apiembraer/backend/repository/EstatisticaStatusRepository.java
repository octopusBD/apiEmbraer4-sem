package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewEstatisticaStatusEntity;

@Repository
public interface EstatisticaStatusRepository extends JpaRepository<ViewEstatisticaStatusEntity, Integer>{
	
//	public List <ViewEstatisticaStatusEntity> findByIdUsuario(Integer idUsuario);
//	
//	@Query(value = "SELECT * FROM VIEW_ESTATISTICA_DISPONIBILIDADE WHERE ID_USUARIO = ?1",nativeQuery = true)
//	Optional<ViewEstatisticaDisponibilidadeEntity> findAllByidUsuario(Integer idUsuario);

}
	