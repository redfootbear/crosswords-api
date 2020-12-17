package redfootbear.crosswords.domain.crossword.service;

import redfootbear.crosswords.domain.crossword.model.Crossword;

public interface CrosswordIndexerService {

    Crossword buildIndexedCrossword(Crossword crossword);

}
