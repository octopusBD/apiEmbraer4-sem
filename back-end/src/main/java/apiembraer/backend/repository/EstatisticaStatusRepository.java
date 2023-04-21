package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewEstatisticaStatusEntity;
import apiembraer.backend.entity.ViewSampleEntity;

@Repository
public interface EstatisticaStatusRepository extends JpaRepository<ViewEstatisticaStatusEntity, Integer>{
	
    // método que retorna uma lista de ViewSampleEntity filtrando pelo id do usuário e pelo chassi
	public List <ViewEstatisticaStatusEntity> findByNomeUsuarioAndStatus(String nomeUsuario, String status);
	
//	public List <ViewEstatisticaStatusEntity> findByIdUsuario(Integer idUsuario);
//	
//	@Query(value = "SELECT * FROM VIEW_ESTATISTICA_DISPONIBILIDADE WHERE ID_USUARIO = ?1",nativeQuery = true)
//	Optional<ViewEstatisticaDisponibilidadeEntity> findAllByidUsuario(Integer idUsuario);

}
	