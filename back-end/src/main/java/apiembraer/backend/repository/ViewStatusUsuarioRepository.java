package apiembraer.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewStatusUsuarioEntity;

@Repository
public interface ViewStatusUsuarioRepository extends JpaRepository<ViewStatusUsuarioEntity, Integer> {

public List <ViewStatusUsuarioEntity> findByIdUsuario(Integer idUsuario);
	
	@Query(value = "SELECT * FROM VIEW_STATUS_USUARIO WHERE ID_USUARIO = ?1",nativeQuery = true)
	Optional<ViewStatusUsuarioEntity> findAllByidUsuario(Integer idUsuario);
	
}
