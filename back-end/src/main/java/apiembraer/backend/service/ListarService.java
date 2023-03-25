package apiembraer.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import apiembraer.backend.DTO.ViewSampleDTO;
import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.repository.ListarRepository;

@Service
public class ListarService {

	@Autowired
	ListarRepository listarRepository;
	
	/*public ModelAndView execute() {
		ModelAndView modelAndView = new ModelAndView();
		ViewSampleDTO ViewDTO = new ViewSampleDTO();
		List<ViewSampleEntity> listarSample = listarRepository.listar();
		List<ViewSampleDTO> ListarViewSampleDTO = new ArrayList<>();
		
		for (ViewSampleEntity listar : listarSample) {
			ViewDTO.setBoletim(listar.getBoletim());
			ViewDTO.setChassi(listar.getChassi());
			ViewDTO.setDtUltEdicao(listar.getDtUltEdicao());
			ViewDTO.setIdBoletim(listar.getIdBoletim());
			ViewDTO.setIdChassi(listar.getIdChassi());
			ViewDTO.setItem(listar.getItem());
			ViewDTO.setNomeUsuario(listar.getNomeUsuario());
			ViewDTO.setStatusSample(listar.getStatusSample());
			ViewDTO.setUltUsuAlt(listar.getUltUsuAlt());

			

			
			ListarViewSampleDTO.add(ViewDTO);
			
			System.err.println(ListarViewSampleDTO.toString());
		}

		modelAndView.addObject("escrever algo aqui", ViewDTO);
		
		modelAndView.setViewName("aqui tambem");
		
		return modelAndView;	}*/
	
	  public List<ViewSampleEntity> getViewSampleEntities(String ultUsuAlt) {
	        return listarRepository.findByultUsuAlt(ultUsuAlt);
	    }
	}


