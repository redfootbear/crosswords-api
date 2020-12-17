package redfootbear.crosswords.domain.crossword.facade;

import javax.inject.Inject;
import redfootbear.crosswords.api.crossword.NewCrossword;
import redfootbear.crosswords.domain.crossword.model.Crossword;
import redfootbear.crosswords.domain.crossword.repository.CrosswordRepository;
import redfootbear.crosswords.domain.crossword.service.CrosswordIndexerService;

public class CrosswordFacadeImpl implements CrosswordFacade {

    private final CrosswordIndexerService crosswordIndexerService;
    private final CrosswordRepository crosswordRepository;

    @Inject
    public CrosswordFacadeImpl(CrosswordIndexerService crosswordIndexerService,
                               CrosswordRepository crosswordRepository) {
        this.crosswordIndexerService = crosswordIndexerService;
        this.crosswordRepository = crosswordRepository;
    }

    @Override
    public Crossword createNewCrossword(NewCrossword newCrossword) {
        Crossword crossword = Crossword.from(newCrossword);
        crosswordIndexerService.buildIndexedCrossword(crossword);
        crosswordRepository.save(crossword);
        return crossword;
    }

    @Override
    public Crossword findByWord(String word) {
        return crosswordRepository.findByWord(word);
    }

    @Override
    public Crossword findByLengthAndIndexPositionAndIndexCharacter(Integer length, Integer position,
                                                                   Character character) {
        return crosswordRepository.findByLengthAndIndexPositionAndIndexCharacter(5, 4, 'E');
    }

}
