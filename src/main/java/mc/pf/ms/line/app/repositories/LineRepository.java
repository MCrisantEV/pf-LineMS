package mc.pf.ms.line.app.repositories;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import mc.pf.ms.line.app.models.Line;
import reactor.core.publisher.Mono;

public interface LineRepository extends ReactiveMongoRepository<Line, String> {
	
	public Mono<Line> findByLine(String line);
}
