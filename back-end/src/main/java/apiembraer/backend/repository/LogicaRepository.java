package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.LogicaEntity;

@Repository
public interface LogicaRepository extends JpaRepository <LogicaEntity, Integer> {

}
