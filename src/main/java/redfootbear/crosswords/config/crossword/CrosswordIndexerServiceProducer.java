package redfootbear.crosswords.config.crossword;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import redfootbear.crosswords.domain.crossword.service.CrosswordIndexerService;
import redfootbear.crosswords.domain.crossword.service.CrosswordIndexerServiceImpl;

@Dependent
public class CrosswordIndexerServiceProducer {

    @Produces
    @DefaultBean
    public CrosswordIndexerService crosswordIndexerService() {
        return new CrosswordIndexerServiceImpl();
    }

}
