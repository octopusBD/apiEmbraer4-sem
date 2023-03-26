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
	}


