package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.ChassiEntity;

public interface ChassiRepository extends JpaRepository <ChassiEntity, Integer> {

}
