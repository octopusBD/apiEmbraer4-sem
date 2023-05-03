package apiembraer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewItemChassiEntity;

@Repository
public interface ViewItemChassiRepository extends JpaRepository <ViewItemChassiEntity, String> {

}
