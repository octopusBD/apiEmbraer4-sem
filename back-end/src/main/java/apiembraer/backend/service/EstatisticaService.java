package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.ViewEstatisticaDisponibilidadeEntity;
import apiembraer.backend.entity.ViewEstatisticaQtdBoletimEntity;
import apiembraer.backend.entity.ViewEstatisticaQtdContidoEntity;
import apiembraer.backend.entity.ViewEstatisticaStatusEntity;
import apiembraer.backend.entity.ViewStatusChassiEntity;
import apiembraer.backend.entity.ViewStatusUsuarioEntity;
import apiembraer.backend.repository.EstatisticaDisponibilidadeRepository;
import apiembraer.backend.repository.EstatisticaQtdBoletimRepository;
import apiembraer.backend.repository.EstatisticaQtdContidoRepository;
import apiembraer.backend.repository.EstatisticaStatusRepository;
import apiembraer.backend.repository.ViewStatusChassiRepository;
import apiembraer.backend.repository.ViewStatusUsuarioRepository;

@Service
public class EstatisticaService {
	
	@Autowired
    private EstatisticaDisponibilidadeRepository estatisticaDisponibilidadeRepository;
	
	@Autowired
    private EstatisticaQtdBoletimRepository estatisticaQtdBoletimRepository;
	
	@Autowired
    private EstatisticaQtdContidoRepository estatisticaQtdContidoRepository;
	
	@Autowired
    private EstatisticaStatusRepository estatisticastatusrepository;
	
	@Autowired
	private ViewStatusUsuarioRepository statususuariorepository;
	
	@Autowired
	private ViewStatusChassiRepository statuschassirepository;
	
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
    
    // LISTAR BOLETIM POR CHASSI //
 	public List<ViewEstatisticaQtdBoletimEntity> findByChassiBol(String chassi) {
 		return estatisticaQtdBoletimRepository.findByChassi(chassi);
 	}
    
    
    // LISTAR CONTIDO //
    public List<ViewEstatisticaQtdContidoEntity> getAllQtdContido() {
        return estatisticaQtdContidoRepository.findAll();
    }
    
    // LISTAR CONTIDO POR CHASSI //
  	public List<ViewEstatisticaQtdContidoEntity> findByChassiCon(String chassi) {
  		return estatisticaQtdContidoRepository.findByChassi(chassi);
  	}
  	
    
	 // LISTAR //
    public List<ViewEstatisticaStatusEntity> getAllStatus() {
        return estatisticastatusrepository.findAll();
    }
    
    
    
    // LISTAR VIEW STATUS USUARIO //
    public List<ViewStatusUsuarioEntity> getAllStatusUsuario() {
        return statususuariorepository.findAll();
    }    
    
    // LISTAR VIEW STATUS USUARIO POR ID //
    public List<ViewStatusUsuarioEntity> findByStatusUsuario(String nomeUsuario) {
        return statususuariorepository.findByNomeUsuario(nomeUsuario);
    } 
    
    
    // LISTAR VIEW STATUS CHASSI //
    public List<ViewStatusChassiEntity> getAllStatusChassi() {
        return statuschassirepository.findAll();
    }    
    
    // LISTAR VIEW STATUS CHASSI POR CHASSI //
    public List<ViewStatusChassiEntity> findByStatusChassi(String chassi) {
        return statuschassirepository.findByChassi(chassi);
    } 
}
