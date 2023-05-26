package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewListarItemEntity;
import apiembraer.backend.entity.ViewListarUsuario;

@Repository
public interface ViewListarItem extends JpaRepository<ViewListarItemEntity, Integer> {


}
