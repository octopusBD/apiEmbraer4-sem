package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.CadastroEntity;

public interface CadastroRepository extends JpaRepository <CadastroEntity, Integer> {

}
