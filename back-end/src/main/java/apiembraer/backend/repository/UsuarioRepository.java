package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Integer>{

}
