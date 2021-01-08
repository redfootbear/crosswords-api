package redfootbear.crosswords.domain.crossword.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import redfootbear.crosswords.api.crossword.NewCrosswordDTO;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver.FakeNewCrossword;
import redfootbear.crosswords.domain.crossword.model.Crossword;
import redfootbear.crosswords.domain.crossword.repository.CrosswordRepository;
import redfootbear.crosswords.domain.crossword.service.CrosswordIndexerService;


@ExtendWith(NewCrosswordResolver.class)
class CrosswordFacadeImplTest {

    CrosswordFacadeImpl crosswordFacade;
    CrosswordIndexerService crosswordIndexerService;
    CrosswordRepository crosswordRepository;

    @BeforeEach
    void beforeEach() {
        crosswordIndexerService = mock(CrosswordIndexerService.class);
        crosswordRepository = mock(CrosswordRepository.class);
        crosswordFacade = new CrosswordFacadeImpl(crosswordIndexerService, crosswordRepository);
    }

    @Test
    void givenValidNewCrossword_WhenCreateNewCrosswordIsCalled_ThenCrosswordIndexerServiceShouldBeCalledOneTime(
            @FakeNewCrossword NewCrosswordDTO newCrossword) {
        crosswordFacade.createNewCrossword(newCrossword);
        verify(crosswordIndexerService, times(1)).buildIndexedCrossword(any(Crossword.class));
    }

}