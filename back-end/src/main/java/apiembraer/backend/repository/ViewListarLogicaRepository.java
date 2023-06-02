package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.ViewListarLogicaEntity;

public interface ViewListarLogicaRepository extends JpaRepository<ViewListarLogicaEntity, Integer>{
	
	public List<ViewListarLogicaEntity> findByIdUsuario(Integer idUsuario);

}
