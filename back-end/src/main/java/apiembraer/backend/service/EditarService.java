package apiembraer.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.DTO.SampleDTO;
import apiembraer.backend.DTO.UsuarioDTO;
import apiembraer.backend.entity.PermissaoEntity;
import apiembraer.backend.entity.SampleEntity;
import apiembraer.backend.entity.UsuarioEntity;
import apiembraer.backend.repository.EditarRepository;

@Service
public class EditarService{

	@Autowired
    private EditarRepository editarRepository;

    // LISTAR //
    public List<SampleEntity> getAllSamples() {
        return editarRepository.findAll();
    }
    
    // ENCONTRAR POR idBoletim / idChassi //
    public Optional<SampleEntity> getSampleById(Integer idBoletim, Integer idChassi) {
        return editarRepository.findBoletimByIdAndChassi(idBoletim, idChassi);
    }

    // SALVAR //
    public SampleEntity saveSample(SampleEntity sample) {
        return editarRepository.save(sample);
    }
    
	public SampleEntity saveSample2(Integer idSample, SampleDTO sampledto) {
	    Optional<SampleEntity> optionalSample = editarRepository.findById(idSample);
	    if (optionalSample.isPresent()) {
	    	SampleEntity sample = optionalSample.get();
	    	sample.setStatusSample(sampledto.getStatusSample());
	        return editarRepository.save(sample);
	    } else {
	        return null;
	    }
	}

    // DELETAR //
    public void deleteSample(Integer id) {
    	editarRepository.deleteById(id);
    }
    
    // ATUALIZAR //
    public SampleEntity updateSample(Integer idBoletim, Integer idChassi, String statusSample) {
	  	Optional<SampleEntity> existingSample = editarRepository.findBoletimByIdAndChassi(idBoletim, idChassi);
       
        if (existingSample.isPresent()) {
            SampleEntity sample = existingSample.get();
            sample.setStatusSample(statusSample);
            return editarRepository.save(sample);
        } else {
            return null;
        }
    }
}
