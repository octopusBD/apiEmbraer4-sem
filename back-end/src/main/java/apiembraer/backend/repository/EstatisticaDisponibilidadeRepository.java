package apiembraer.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewEstatisticaDisponibilidadeEntity;

@Repository
public interface EstatisticaDisponibilidadeRepository extends JpaRepository<ViewEstatisticaDisponibilidadeEntity, Integer>{
	
	public List <ViewEstatisticaDisponibilidadeEntity> findByIdUsuario(Integer idUsuario);
	
	@Query(value = "SELECT * FROM VIEW_ESTATISTICA_DISPONIBILIDADE WHERE ID_USUARIO = ?1",nativeQuery = true)
	Optional<ViewEstatisticaDisponibilidadeEntity> findAllByidUsuario(Integer idUsuario);

}
