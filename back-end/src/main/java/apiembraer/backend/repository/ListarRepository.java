package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import apiembraer.backend.entity.ViewSampleEntity;


@Repository
public interface ListarRepository extends JpaRepository <ViewSampleEntity, Integer> {

	//@Query(value ="SELECT * FROM VIEW_SAMPLE WHERE ULT_USU_ALT = :ultUsuAlt", nativeQuery = true)
    //public List<ViewSampleEntity> listar(@Param("ultUsuAlt") String ultUsuAlt);

	public List<ViewSampleEntity> findByultUsuAlt(String ultUsuAlt);

	//public List<ViewSampleEntity> listar();
	//List<ViewSampleEntity> findByultUsuAlt(String ultUsuAlt);
	
}
