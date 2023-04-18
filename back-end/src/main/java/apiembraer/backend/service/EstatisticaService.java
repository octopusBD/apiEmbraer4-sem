package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.ViewEstatisticaDisponibilidadeEntity;
import apiembraer.backend.entity.ViewEstatisticaQtdBoletimEntity;
import apiembraer.backend.entity.ViewEstatisticaQtdContidoEntity;
import apiembraer.backend.repository.EstatisticaDisponibilidadeRepository;
import apiembraer.backend.repository.EstatisticaQtdBoletimRepository;
import apiembraer.backend.repository.EstatisticaQtdContidoRepository;

@Service
public class EstatisticaService {
	
	@Autowired
    private EstatisticaDisponibilidadeRepository estatisticaDisponibilidadeRepository;
	
	@Autowired
    private EstatisticaQtdBoletimRepository estatisticaQtdBoletimRepository;
	
	@Autowired
    private EstatisticaQtdContidoRepository estatisticaQtdContidoRepository;
	
	// LISTAR DISPONIBILIDADE //
    public List<ViewEstatisticaDisponibilidadeEntity> getAllDisponibilidade() {
        return estatisticaDisponibilidadeRepository.findAll();
    }
    
    // LISTAR DISPONIBILIDADE POR ID //
	public List<ViewEstatisticaDisponibilidadeEntity> findByIdUsuario(Integer idUsuario) {
		return estatisticaDisponibilidadeRepository.findByIdUsuario(idUsuario);
	}
    
	
	// LISTAR BOLETIM //
    public List<ViewEstatisticaQtdBoletimEntity> getAllQtdBoletim() {
        return estatisticaQtdBoletimRepository.findAll();
    }
    
    // LISTAR BOLETIM POR ID CHASSI //
 	public List<ViewEstatisticaQtdBoletimEntity> findByIdChassi(Integer idChassi) {
 		return estatisticaQtdBoletimRepository.findByIdChassi(idChassi);
 	}
    
    
    // LISTAR CONTIDO //
    public List<ViewEstatisticaQtdContidoEntity> getAllQtdContido() {
        return estatisticaQtdContidoRepository.findAll();
    }
    
}
