package apiembraer.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.repository.ViewSampleRepository;
import apiembraer.backend.service.ListarService;
//import apiembraer.backend.report.PDFGenerator;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
@Controller
@RequestMapping(value = "/consultor")
public class ListarController {

    @Autowired
    ListarService ListarService;

    @Autowired
    ViewSampleRepository viewSampleRepository;

    @GetMapping("/{idUsuario}")
    @ResponseBody
    public List<ViewSampleEntity> getViewSampleEntities(@PathVariable Integer idUsuario) {

        List<ViewSampleEntity> result = ListarService.getViewSampleEntities(idUsuario);
        System.err.println(result);
        return result;

    }

    @GetMapping("/{idUsuario}/{chassi}/{statusSample}")
    @ResponseBody
    public List<ViewSampleEntity> getViewSample(
        @PathVariable Integer idUsuario, @PathVariable String chassi, @PathVariable String statusSample
    ) {

        List<ViewSampleEntity> result = ListarService.getViewSample(idUsuario, chassi, statusSample);
        System.err.println(result);
        return result;

    }
}
