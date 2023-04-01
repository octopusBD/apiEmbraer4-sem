package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.UpdateSampleEntity;

@Repository
public interface NotificarRepository extends JpaRepository <UpdateSampleEntity, Integer>{

	public List<UpdateSampleEntity> findByidChassi(Integer idChassi);

}




