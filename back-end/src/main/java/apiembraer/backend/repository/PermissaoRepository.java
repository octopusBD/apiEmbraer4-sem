package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.PermissaoEntity;


public interface PermissaoRepository extends JpaRepository <PermissaoEntity, Integer> {

}
