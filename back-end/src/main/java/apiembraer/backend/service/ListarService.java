package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.ViewEstatisticaStatusEntity;
import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.repository.EstatisticaStatusRepository;
import apiembraer.backend.repository.ListarRepository;

@Service
public class ListarService {

	@Autowired
	ListarRepository listarRepository;
	
	@Autowired
	EstatisticaStatusRepository estatisticastatusrepository;

	// Método que retorna todos os registros de amostras de um determinado usuário
	public List<ViewSampleEntity> getViewSampleEntities(Integer idUsuario) {
		return listarRepository.findByidUsuario(idUsuario);
	}
	
	// Método que retorna os registros de amostras de um determinado usuário e chassi
	public List<ViewSampleEntity> getViewSampleConsulta(Integer idUsuario, String chassi) {
		return listarRepository.findByIdUsuarioAndChassi(idUsuario, chassi);
	}
	
	public List<ViewEstatisticaStatusEntity> getViewSampleConsultaa(String nomeUsuario) {
		return estatisticastatusrepository.findByNomeUsuario(nomeUsuario);
	}
	
	// Método que retorna os registros de amostras de um determinado usuário, chassi e status
	public List<ViewSampleEntity> getViewSample(Integer idUsuario, String chassi, String statusSample) {
		return listarRepository.findByIdUsuarioAndChassiAndStatusSample(idUsuario, chassi, statusSample);
	}

}


