package job_tracker.data;

import job_tracker.models.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteJdbcTemplateRepositoryTest {
    @Autowired NoteJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void shouldFindByJobId(){
        List<Note> notes = repository.findByJobId(1);
        assertNotNull(notes);
        assertTrue(notes.size() > 0);
        assertEquals("test note", notes.get(0).getContent());
    }

    @Test
    void shouldNotFindByNonExistingId(){
        List<Note> notes = repository.findByJobId(1000);
        assertTrue(notes.size() == 0);
    }

    @Test
    void shouldFindByNoteId(){
        Note note = repository.findById(1);
        assertNotNull(note);
        assertEquals("test note", note.getContent());
    }

    @Test
    void shouldNotFindByNonExistingNoteId() {
        Note note = repository.findById(1000);
        assertNull(note);
    }
}