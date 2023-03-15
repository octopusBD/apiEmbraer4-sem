package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.SampleEntity;

public interface SampleRepository extends JpaRepository <SampleEntity, String> {

}
