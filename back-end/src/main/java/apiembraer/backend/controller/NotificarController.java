package apiembraer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import apiembraer.backend.entity.UpdateSampleEntity;
import apiembraer.backend.service.NotificarService;

@CrossOrigin
@RestController
@RequestMapping(value = "/notificar")
public class NotificarController {

	@Autowired
    NotificarService NotificarService;

	@GetMapping("/{idChassi}")
    @ResponseBody
    @CrossOrigin
    public List<UpdateSampleEntity> getUpdateSampleEntities(@PathVariable Integer idChassi) {

        List<UpdateSampleEntity> result = NotificarService.getUpdateSampleEntities(idChassi);
        System.err.println(result);

        return result;

    }
}
