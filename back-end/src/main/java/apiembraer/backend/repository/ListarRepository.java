package apiembraer.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiembraer.backend.entity.ViewSampleEntity;


@Repository
public interface ListarRepository extends JpaRepository <ViewSampleEntity, Integer> {

    // método que retorna uma lista de ViewSampleEntity filtrando pelo id do usuário
	public List<ViewSampleEntity> findByidUsuario(Integer idUsuario);

    // método que retorna uma lista de ViewSampleEntity filtrando pelo id do usuário e pelo chassi
	public List <ViewSampleEntity> findByIdUsuarioAndChassi(Integer idUsuario, String chassi);

    // método que retorna uma lista de ViewSampleEntity filtrando pelo id do usuário, pelo chassi e pelo status da amostra
	public List <ViewSampleEntity> findByIdUsuarioAndChassiAndStatusSample(Integer idUsuario, String Chassi, String statusSample);

}
