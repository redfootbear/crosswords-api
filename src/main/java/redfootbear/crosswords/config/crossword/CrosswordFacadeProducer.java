package redfootbear.crosswords.config.crossword;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import redfootbear.crosswords.domain.crossword.facade.CrosswordFacade;
import redfootbear.crosswords.domain.crossword.facade.CrosswordFacadeImpl;
import redfootbear.crosswords.domain.crossword.repository.CrosswordRepository;
import redfootbear.crosswords.domain.crossword.service.CrosswordIndexerService;

@Dependent
public class CrosswordFacadeProducer {

    @Produces
    @DefaultBean
    public CrosswordFacade crosswordFacade(CrosswordIndexerService crosswordIndexerService,
                                           CrosswordRepository crosswordRepository) {
        return new CrosswordFacadeImpl(crosswordIndexerService, crosswordRepository);
    }

}
