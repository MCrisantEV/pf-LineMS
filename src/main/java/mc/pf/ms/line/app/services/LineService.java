package mc.pf.ms.line.app.services;

import mc.pf.ms.line.app.models.Line;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LineService {

	public Mono<Line> save(Line line);

	public Flux<Line> findAll();

	public Mono<Line> findId(String id);
	
	public Mono<Line> findLine(String line);

}
