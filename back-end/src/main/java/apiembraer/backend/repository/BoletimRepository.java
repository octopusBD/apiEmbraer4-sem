package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.BoletimEntity;

public interface BoletimRepository extends JpaRepository <BoletimEntity, Integer> {

}
