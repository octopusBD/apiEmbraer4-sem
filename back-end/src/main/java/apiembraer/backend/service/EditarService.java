package apiembraer.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apiembraer.backend.entity.ChassiEntity;
import apiembraer.backend.entity.SampleEntity;
import apiembraer.backend.repository.EditarRepository;
import apiembraer.backend.repository.SampleRepository;

@Service
public class EditarService{

	@Autowired
    private EditarRepository editarRepository;;

    // LISTAR //
    public List<SampleEntity> getAllSamples() {
        return editarRepository.findAll();
    }

    // ENCONTRAR POR ID //
    public Optional<SampleEntity> getSampleById(Integer id) {
        return editarRepository.findById(id);
    }

    // SALVAR //
    public SampleEntity saveSample(SampleEntity sample) {
        return editarRepository.save(sample);
    }

    // DELETAR //
    public void deleteSample(Integer id) {
    	editarRepository.deleteById(id);
    }
    
    // ATUALIZAR //
    public SampleEntity updateSample(Integer idBoletim, Integer idChassi, String statusSample) {
	  	Optional<SampleEntity> existingSample = editarRepository.findBoletimByIdAndChassi(idBoletim, idChassi);

        //Optional<SampleEntity> existingSample = editarRepository.findByChassiIdChassiAndBoletimIdBoletim(idBoletim, idChassi);
    	//List<SampleEntity> existingSample = editarRepository.findBoletimByIdAndChassi(idBoletim, idChassi);
       
        if (existingSample.isPresent()) {
            SampleEntity sample = existingSample.get();
            sample.setStatusSample(statusSample);
            return editarRepository.save(sample);
        } else {
            return null;
        }
    }
    
//public List<SampleEntity> updateSample(Integer idBoletim, Integer idChassi, String statusSample) {
//	return editarRepository.findBoletimByIdAndChassi(idBoletim, idChassi);
//}
  
//    public SampleEntity buscarPorId(ChassiEntity idChassi) {
//        Optional<SampleEntity> usuarioOp = editaroRepo.findById(idChassi);
//        return usuarioOp.get();
//       
//    }
    
//    public SampleEntity novoSample(SampleEntity sample){
//        return editaroRepo.save(sample);
//    }

//    public void deletarSample(SampleEntity sample){ 
//    	editaroRepo.delete(sample);
//    }

//    public List<Usuario> findAllUsuario(){ 
//        return usuarioRepo.findAll();
//    }
  
}
