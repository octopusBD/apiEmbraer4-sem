package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.ViewEstatisticaDisponibilidadeEntity;
import apiembraer.backend.entity.ViewEstatisticaQtdBoletimEntity;
import apiembraer.backend.repository.EstatisticaDisponibilidadeRepository;
import apiembraer.backend.repository.EstatisticaQtdBoletimRepository;

@Service
public class EstatisticaService {
	
	@Autowired
    private EstatisticaDisponibilidadeRepository estatisticaDisponibilidadeRepository;
	
	@Autowired
    private EstatisticaQtdBoletimRepository estatisticaQtdBoletimrRepository;
	
	// LISTAR //
    public List<ViewEstatisticaDisponibilidadeEntity> getAllDisponibilidade() {
        return estatisticaDisponibilidadeRepository.findAll();
    }
    
    // LISTAR POR ID //
	public List<ViewEstatisticaDisponibilidadeEntity> findByIdUsuario(Integer idUsuario) {
		return estatisticaDisponibilidadeRepository.findByIdUsuario(idUsuario);
	}
    
	 // LISTAR BOLETIM //
    public List<ViewEstatisticaQtdBoletimEntity> getAllQtdBoletim() {
        return estatisticaQtdBoletimrRepository.findAll();
    }
    
	
}
