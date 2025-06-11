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

    public Note noteMaker(){
        Note note = new Note();
        note.setContent("Note made in test file");
        note.setJobId(1);// existing job with notes

        return note;
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

    @Test
    void shouldAddNote(){
        Note note = noteMaker();
        Note actual = repository.add(note);
        assertNotNull(actual);
        assertEquals("Note made in test file", actual.getContent());
    }

//    @Test // seems to be something validations will have to take care of
//    void shouldNotAddNoteWithNonExistingJobId(){
//        Note note = noteMaker();
//        note.setJobId(1000);
//        Note actual = repository.add(note);
//        assertNull(actual);
//    }

    @Test
    void shouldUpdateExistingNote(){
        Note note = noteMaker();
        note.setNoteId(2);
        assertTrue(repository.update(note));
    }

    @Test
    void shouldNotUpdateNonExistingNote(){
        Note note = noteMaker();
        note.setNoteId(2000);
        assertFalse(repository.update(note));
    }

    @Test
    void shouldDeleteExistingNote(){
        assertTrue(repository.deleteById(3));
    }

    @Test
    void shouldNotDeleteNonExistingNote(){
        assertFalse(repository.deleteById(3000));
    }
}