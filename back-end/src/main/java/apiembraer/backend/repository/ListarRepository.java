package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import apiembraer.backend.entity.ViewSampleEntity;

public interface ListarRepository extends JpaRepository <ViewSampleEntity, Integer> {

	@Query(value ="SELECT * FROM VIEW_SAMPLE WHERE ULT_USU_ALT = :ultUsuAlt", nativeQuery = true)
	public List<ViewSampleEntity> listar(@Param("ultUsuAlt") String ultUsuAlt);

}
