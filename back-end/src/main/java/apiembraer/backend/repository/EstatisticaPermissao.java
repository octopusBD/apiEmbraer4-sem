package apiembraer.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewEstatisticaDisponibilidadeEntity;
import apiembraer.backend.entity.ViewEstatisticaUsuario;

@Repository
public interface EstatisticaPermissao extends JpaRepository<ViewEstatisticaUsuario, Integer>{
	
	public List <ViewEstatisticaUsuario> findByPermissao(String Permissao);
	
	@Query(value = "SELECT * FROM VIEW_ESTATISTICA_USUARIO WHERE PERMISSAO = ?1",nativeQuery = true)
	Optional<ViewEstatisticaUsuario> findAllByPermissao(String Permissao);

}
