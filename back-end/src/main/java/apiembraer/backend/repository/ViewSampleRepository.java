package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import apiembraer.backend.entity.ViewSampleEntity;

public interface ViewSampleRepository extends JpaRepository <ViewSampleEntity, Integer> {

    List<ViewSampleEntity> findAllByIdUsuario(Integer idUsuario);

}
