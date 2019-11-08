package mc.pf.ms.line.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mc.pf.ms.line.app.models.Line;
import mc.pf.ms.line.app.services.LineService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/lines")
public class LineController {
	
	@Autowired
	private LineService lineServ;
	
	@PostMapping()
	public Mono<Line> create(@RequestBody Line line){
		return lineServ.save(line);
	}
	
	@GetMapping
	public Flux<Line> findAll(){
		return lineServ.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Line> findId(@PathVariable String id){
		return lineServ.findId(id);
	}
	
	@GetMapping("/name/{line}")
	public Mono<Line> findLine(@PathVariable String line){
		return lineServ.findLine(line);
	}
	
}
