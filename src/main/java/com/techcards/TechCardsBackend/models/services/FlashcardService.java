package com.techcards.TechCardsBackend.models.services;

import com.techcards.TechCardsBackend.models.dao.flashcards.Flashcard;
import com.techcards.TechCardsBackend.models.dao.flashcards.FlashcardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FlashcardService {

    FlashcardDAO flashcardDAO;

    @Autowired
    public FlashcardService(FlashcardDAO flashcardDAO) {
        this.flashcardDAO = flashcardDAO;
    }

    public Flashcard getFlashcardById(UUID id) {
        Flashcard flashcard = flashcardDAO.getFlashcardById(id);

        return flashcard;
    }

    public List<Flashcard> getAllFlashcardsByDeckId(UUID deckId) {
        List<Flashcard> allFlashcardsByDeckId = flashcardDAO.getAllFlashcardsByDeckId(deckId);
        return allFlashcardsByDeckId;
    }

    public List<Flashcard> getAllFlashcards() {
        List<Flashcard> allFlashcards = flashcardDAO.getAllFlashcards();
        return allFlashcards;
    }

    public int createFlashcard(Flashcard flashcard) {
        return flashcardDAO.createFlashcard(flashcard);
    }

    public int updateFlashcard(Flashcard flashcard) {
        return flashcardDAO.updateFlashcard(flashcard);
    }

    public int deleteFlashcard(UUID flashcardId) {
        return flashcardDAO.deleteFlashcard(flashcardId);
    }
}
