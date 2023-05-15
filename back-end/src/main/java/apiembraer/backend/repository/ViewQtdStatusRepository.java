package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apiembraer.backend.entity.ViewPermissaoUsuarioEntity;
import apiembraer.backend.entity.ViewQtdStatus;

public interface ViewQtdStatusRepository extends JpaRepository <ViewQtdStatus, Integer>{
    
}