package redfootbear.crosswords.config.word;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import redfootbear.crosswords.domain.word.facade.WordFacade;
import redfootbear.crosswords.domain.word.facade.WordFacadeImpl;
import redfootbear.crosswords.domain.word.repository.WordRepository;
import redfootbear.crosswords.domain.word.service.WordIndexerService;

@Dependent
public class WordFacadeProducer {

    @Produces
    @DefaultBean
    public WordFacade wordFacade(WordIndexerService wordIndexerService, WordRepository wordRepository) {
        return new WordFacadeImpl(wordIndexerService, wordRepository);
    }

}
