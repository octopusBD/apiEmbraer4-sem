package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import apiembraer.backend.entity.ViewSampleEntity;

public interface ViewSampleRepository extends JpaRepository <ViewSampleEntity, Integer> {

    public List <ViewSampleEntity> findByIdUsuarioAndChassi(Integer idUsuario, String Chassi);
    
    
}
