package apiembraer.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import apiembraer.backend.entity.ChassiEntity;
import apiembraer.backend.entity.SampleEntity;
import apiembraer.backend.service.EditarService;
//import apiembraer.backend.service.IEditarService;
import net.bytebuddy.asm.Advice.Return;

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

    // ENCONTRAR POR ID //
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<SampleEntity> getSampleById(@PathVariable Integer id) {
        Optional<SampleEntity> sample = editarService.getSampleById(id);
        return sample.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSample(@PathVariable Integer id) {
        Optional<SampleEntity> existingSample = editarService.getSampleById(id);
        if (existingSample.isPresent()) {
        	editarService.deleteSample(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };
	
//  @GetMapping("/update/{idBoletim}/{idChassi}/{statusSample}")
//  public ResponseEntity<SampleEntity> updateSample(@PathVariable Integer idBoletim, @PathVariable Integer idChassi, @PathVariable String statusSample) {
//  	//System.err.println(idBoletim + "/" + idChassi + "/" + statusSample);
//      List<SampleEntity> updatedSample = editarService.updateSample(idBoletim, idChassi, statusSample);
//      return updatedSample != null ? new ResponseEntity<>(updatedSample, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//  }
      		
//    @Autowired
//    private IEditarService service;
//    // REQUISIÇÃO PARA CRIAR UM NOVO USUARIO //
//    @GetMapping(value = "/inserir/{sample}/{boletim}/{status}")
//    public SampleEntity buscarPorId(@PathVariable("sample") Integer idChassi, @PathVariable("boletim") Integer idBoletim, @PathVariable("status") String statusSample) {
//        return service.updateSample(idChassi, idBoletim, statusSample);
//    }
//    // REQUISIÇÃO PARA CRIAR UM NOVO USUARIO //
//    @PostMapping(value =  "/inserir")
//    public SampleEntity novoSample(@RequestBody SampleEntity sample){
//        return service.novoSample(sample);
//    }

//    // REQUISIÇÃO PARA DELETAR UM NOVO USUARIO //
//    @PostMapping(value = "/deletar")
//    public void deletarSample(@RequestBody SampleEntity sample){
//        service.deletarSample(sample);
//    }
//
//    // REQUISIÇÃO PARA LISTAR TODOS//
//    @GetMapping(value = "/listarTodos")
//    public List<SampleEntity> findAllSample() {
//        return service.findAllSample();
//    }
}
