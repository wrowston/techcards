package com.techcards.TechCardsBackend.models.dao.flashcards;

import com.techcards.TechCardsBackend.models.dao.decks.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Component
public class FlashcardDAO {

    JdbcTemplate jdbcTemplate;

    public FlashcardDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Flashcard getFlashcardById(UUID id) {
        return jdbcTemplate.queryForObject("select * from flashcards where id = ?", new Object[] {id}, new FlashcardMapper());
    }
    
    public List<Flashcard> getAllFlashcards() {
        List<Flashcard> flashcards = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from flashcards");

        for (Map row : rows) {
            Flashcard flashcard = new Flashcard();
            flashcard.setId((UUID) row.get("flashcard_id"));
            flashcard.setClue((String) row.get("flashcard_clue"));
            flashcard.setAnswer((String) row.get("flashcard_answer"));
            flashcard.setDeck((Deck) row.get("flashcard_deck"));

            flashcards.add(flashcard);
        }
        return flashcards;
    }

    public int createFlashcard(Flashcard flashcard) {

        UUID newFlashcardId = UUID.randomUUID();
        flashcard.setId(newFlashcardId);

        String sql = "insert into flashcards " +
                "(flashcard_id, flashcard_clue, flashcard_answer, flashcard_deck) values " +
                "('" + flashcard.getId() +
                "','" + flashcard.getClue() +
                "','" + flashcard.getAnswer() +
                "','" + flashcard.getDeck() + "')";

        return jdbcTemplate.update(sql);
    }

    public int updateFlashcard(Flashcard flashcard) {
        String sql = "update flashcards set " +
                "flashcard_clue = '" + flashcard.getClue() +
                "', flashcard_answer = '" + flashcard.getAnswer() +
                "', flashcard_deck = '" + flashcard.getDeck() +
                "' where flashcard_id = " + flashcard.getId() + "";

        return jdbcTemplate.update(sql);
    }

    public int deleteFlashcard(UUID flashcardId) {
        String sql = "delete from flashcards where id = '" + flashcardId + "'";
        return jdbcTemplate.update(sql);
    }

}
