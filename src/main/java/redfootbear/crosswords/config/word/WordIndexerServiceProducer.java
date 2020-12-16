package redfootbear.crosswords.config.word;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import redfootbear.crosswords.domain.word.service.WordIndexerService;
import redfootbear.crosswords.domain.word.service.WordIndexerServiceImpl;

@Dependent
public class WordIndexerServiceProducer {

    @Produces
    @DefaultBean
    public WordIndexerService wordIndexerService() {
        return new WordIndexerServiceImpl();
    }

}
