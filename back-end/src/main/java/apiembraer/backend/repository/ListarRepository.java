package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import apiembraer.backend.entity.ViewSampleEntity;


@Repository
public interface ListarRepository extends JpaRepository <ViewSampleEntity, Integer> {


	public List<ViewSampleEntity> findByidUsuario(Integer idUsuario);

	
	
}
