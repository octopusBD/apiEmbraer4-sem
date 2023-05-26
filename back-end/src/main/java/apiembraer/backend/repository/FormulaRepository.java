package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.FormulaEntity;

public interface FormulaRepository extends JpaRepository <FormulaEntity, Integer> {

}
