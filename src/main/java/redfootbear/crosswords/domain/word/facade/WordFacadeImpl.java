package redfootbear.crosswords.domain.word.facade;

import javax.inject.Inject;
import redfootbear.crosswords.api.word.NewWord;
import redfootbear.crosswords.domain.word.model.Word;
import redfootbear.crosswords.domain.word.repository.WordRepository;
import redfootbear.crosswords.domain.word.service.WordIndexerService;

public class WordFacadeImpl implements WordFacade {

    private final WordIndexerService wordIndexerService;
    private final WordRepository wordRepository;

    @Inject
    public WordFacadeImpl(WordIndexerService wordIndexerService,
                          WordRepository wordRepository) {
        this.wordIndexerService = wordIndexerService;
        this.wordRepository = wordRepository;
    }

    @Override
    public Word createNewWord(NewWord newWord) {
        Word word = Word.from(newWord);
        wordIndexerService.buildIndexedWord(word);
        wordRepository.save(word);
        return word;
    }

    @Override
    public Word findByWord(String word) {
        return wordRepository.findByWord(word);
    }

    @Override
    public Word findByLengthAndIndexPositionAndIndexCharacter(Integer length, Integer position, Character character) {
        return wordRepository.findByLengthAndIndexPositionAndIndexCharacter(5, 4, 'E');
    }

}
