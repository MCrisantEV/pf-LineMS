package mc.pf.ms.line.app.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.pf.ms.line.app.models.Course;
import mc.pf.ms.line.app.models.Line;
import mc.pf.ms.line.app.repositories.LineRepository;
import mc.pf.ms.line.app.services.LineService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LineImpl implements LineService {

	@Autowired
	private LineRepository lineRep;

	@Override
	public Mono<Line> save(Line line) {
		
		if (line.getLine().equals("") || line.getLine() == null || line.getNet() == null) {
			line.setNet(null);
			line.setLine("No se puede registrar, Campos vacios");
			return Mono.just(line);
		} else {
			if (line.getNet().size() >= 3) {
				double sum = 0;
				List<String> lstTeacher = new ArrayList<>();

				for (Course net : line.getNet()) {
					sum = sum + net.getHours();
					lstTeacher.add(net.getTeacher());
				}

				if (sum > 100) {
					String teacher = null;
					Set<String> contTeacher = new HashSet<String>(lstTeacher);
					for (String key : contTeacher) {
						if (Collections.frequency(lstTeacher, key) > 2) {
							teacher = key;
						}
					}
					if (teacher != null) {
						line.setNet(null);
						line.setLine("No se puede registrar, el profesor, " + teacher
								+ " no puede dictar más de dos cursos.");
						return Mono.just(line);
					} else {
						return lineRep.findByLine(line.getLine()).map(db -> {
							line.setNet(null);
							line.setLine(line.getLine() + ", Ya esta registrado");
							return line;
						}).switchIfEmpty(lineRep.save(line));
					}
				} else {
					line.setNet(null);
					line.setLine("No se puede registrar, Mínimo 100 horas");
					return Mono.just(line);
				}
			} else {
				line.setNet(null);
				line.setLine("No se puede registrar, Mínimo 3 cursos");
				return Mono.just(line);
			}
		}
	}

	@Override
	public Flux<Line> findAll() {
		return lineRep.findAll();
	}

	@Override
	public Mono<Line> findId(String id) {
		return lineRep.findById(id);
	}

	@Override
	public Mono<Line> findLine(String line) {
		return lineRep.findByLine(line);
	}

}
