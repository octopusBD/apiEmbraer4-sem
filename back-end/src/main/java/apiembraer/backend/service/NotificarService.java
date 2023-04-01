package apiembraer.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.UpdateSampleEntity;
import apiembraer.backend.repository.NotificarRepository;

@Service
public class NotificarService {

	@Autowired
	NotificarRepository notificarRepository;

	// Método que retorna as atualizações de determinado chassi
	public List<UpdateSampleEntity> getUpdateSampleEntities(Integer idChassi) {
        return notificarRepository.findByidChassi(idChassi);
    }

}
