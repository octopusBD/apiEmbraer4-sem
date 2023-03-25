package apiembraer.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import apiembraer.backend.entity.ViewSampleEntity;
import apiembraer.backend.service.ListarService;

@Controller
@RequestMapping(value = "/consultor")
public class ListarController {

	@Autowired
	ListarService ListarService;
	
	@GetMapping("/{ultUsuAlt}")
	public List<ViewSampleEntity> getViewSampleEntities(@PathVariable String ultUsuAlt) {
	    List<ViewSampleEntity> result = ListarService.getViewSampleEntities(ultUsuAlt);
	    System.out.println(result);
	    return result;
	}
}
