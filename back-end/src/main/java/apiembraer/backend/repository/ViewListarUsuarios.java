package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewListarUsuario;

@Repository
public interface ViewListarUsuarios extends JpaRepository<ViewListarUsuario, Integer> {

}
