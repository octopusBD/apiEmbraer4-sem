package apiembraer.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apiembraer.backend.entity.SampleEntity;
import apiembraer.backend.service.EditarService;

@RestController
@CrossOrigin
@RequestMapping(value = "/editar")
public class EditarController {
	
    @Autowired
    private EditarService editarService;
    
    // LISTAR //
    @GetMapping("/listar")
    public ResponseEntity<List<SampleEntity>> getAllSamples() {
        List<SampleEntity> samples = editarService.getAllSamples();
        return new ResponseEntity<>(samples, HttpStatus.OK);
    }

    // SALVAR //
    @PostMapping(value = "/save")
    public ResponseEntity<SampleEntity> saveSample(@RequestBody SampleEntity sample) {
        SampleEntity savedSample = editarService.saveSample(sample);
        return new ResponseEntity<>(savedSample, HttpStatus.CREATED);
    }

    // ATUALIZAR //
    @GetMapping("/update/{idBoletim}/{idChassi}/{statusSample}")
    public ResponseEntity<SampleEntity> updateSample(@PathVariable Integer idBoletim, @PathVariable Integer idChassi, @PathVariable String statusSample) {

        SampleEntity samples = editarService.updateSample(idBoletim, idChassi, statusSample);

        if (samples != null) {
            return ResponseEntity.ok(samples);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETAR //
    @GetMapping("/delete/{idBoletim}/{idChassi}")
    public ResponseEntity<Void> deleteSample(@PathVariable Integer idBoletim, @PathVariable Integer idChassi) {
        Optional<SampleEntity> existingSample = editarService.getSampleById(idBoletim,idChassi);

        if (existingSample.isPresent()) {
            SampleEntity sample = existingSample.get();
            Integer id = sample.getIdSample();
        	editarService.deleteSample(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };

}
