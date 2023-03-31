package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.repository.ListarRepository;

@Service
public class ListarService {

	@Autowired
	ListarRepository listarRepository;

	public List<ViewSampleEntity> getViewSampleEntities(Integer idUsuario) {
		return listarRepository.findByidUsuario(idUsuario);
	}

	public List<ViewSampleEntity> getViewSampleConsulta(Integer idUsuario, String chassi) {
		return listarRepository.findByIdUsuarioAndChassi(idUsuario, chassi);
	}

	public List<ViewSampleEntity> getViewSample(Integer idUsuario, String chassi, String statusSample) {
		return listarRepository.findByIdUsuarioAndChassiAndStatusSample(idUsuario, chassi, statusSample);
	}

}


