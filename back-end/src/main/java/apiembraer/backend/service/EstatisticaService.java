package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.ViewEstatisticaDisponibilidadeEntity;
import apiembraer.backend.repository.EstatisticaRepository;

@Service
public class EstatisticaService {
	
	@Autowired
    private EstatisticaRepository estatisticarepository;
	
	 // LISTAR //
    public List<ViewEstatisticaDisponibilidadeEntity> getAll() {
        return estatisticarepository.findAll();
    }
    
    // LISTAR POR ID //
	public List<ViewEstatisticaDisponibilidadeEntity> findByIdUsuario(Integer idUsuario) {
		return estatisticarepository.findByIdUsuario(idUsuario);
	}
    
}
