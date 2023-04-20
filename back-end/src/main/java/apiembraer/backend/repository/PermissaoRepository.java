package apiembraer.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.PermissaoEntity;

public interface PermissaoRepository extends JpaRepository <PermissaoEntity, Integer> {

	Optional<PermissaoEntity> findByIdPermissao(Integer idPermissao);

}
