package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.ViewEstatisticaStatusEntity;
import apiembraer.backend.entity.ViewListarLogicaEntity;
import apiembraer.backend.entity.ViewSampleEntity;

public interface ViewListarLogicaRepository extends JpaRepository<ViewListarLogicaEntity, Integer>{
	
	public List<ViewListarLogicaEntity> findByIdUsuario(Integer idUsuario);
	
	public List <ViewListarLogicaEntity> findByIdUsuarioAndChassi(Integer idUsuario, String chassi);
	
	public List <ViewListarLogicaEntity> findByNomeUsuario(String nomeUsuario);

}
