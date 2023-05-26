package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.SampleEntity;
import apiembraer.backend.entity.ViewEstatisticaStatusEntity;
import apiembraer.backend.entity.ViewListarBoletimEntity;
import apiembraer.backend.entity.ViewListarFormulaEntity;
import apiembraer.backend.entity.ViewListarItemEntity;
import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.repository.EstatisticaStatusRepository;
import apiembraer.backend.repository.ListarRepository;
import apiembraer.backend.repository.ViewListarBoletim;
import apiembraer.backend.repository.ViewListarFormula;
import apiembraer.backend.repository.ViewListarItem;

@Service
public class ListarService {

	@Autowired
	ListarRepository listarRepository;
	
	@Autowired
	EstatisticaStatusRepository estatisticastatusrepository;
	
	@Autowired
	ViewListarBoletim viewlistarboletim;
	
	@Autowired
	ViewListarItem vielistaritem;

	@Autowired
	ViewListarFormula viewlistarformula;

	
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
	
	// Método que retorna os registros de amostras de um determinado usuário e status
		public List<ViewSampleEntity> getViewSample(Integer idUsuario, String statusSample) {
			return listarRepository.findByIdUsuarioAndStatusSample(idUsuario, statusSample);
		}
		
	    public List<ViewListarBoletimEntity> getAllBoletim() {
	        return viewlistarboletim.findAll();
	    }
	
	    public List<ViewListarItemEntity> getAllItem() {
	        return vielistaritem.findAll();
	    }
	    
	    public List<ViewListarFormulaEntity> getAllFormulas() {
	        return viewlistarformula.findAll();
	    }



}


