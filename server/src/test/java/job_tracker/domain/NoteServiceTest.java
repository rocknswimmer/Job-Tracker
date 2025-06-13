package job_tracker.domain;

import job_tracker.data.JobRepository;
import job_tracker.data.NoteRepository;
import job_tracker.models.Job;
import job_tracker.models.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NoteServiceTest {
    @Autowired
    NoteService service;

    @MockBean
    NoteRepository repository;

    @MockBean
    JobRepository jobRepository;

    public Note makeNote(){
        Note note = new Note();
        note.setContent("note for testing");
        note.setJobId(1);

        return note;
    }

    //any test other than validations is just a test of my ability to mock repository

    @Test
    void shouldNotAddNullNote(){
        Result<Note> actual = service.add(null);
        assertFalse(actual.isSuccess());
        assertTrue(actual.getMessages().get(0).equals("Note cannot be null"));
    }

    @Test
    void shouldNotAddNoteWithEmptyContent(){
        Note note = makeNote();
        note.setContent("");
        Result<Note> actual = service.add(note);
        assertFalse(actual.isSuccess());
        assertTrue(actual.getMessages().get(0).equals("Note content is required"));
    }

    @Test
    void shouldNotAddNoteForNonExistingJob(){
        Note note = makeNote();
        when(jobRepository.findById(1)).thenReturn(null);
        Result<Note> result = service.add(note);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).contains("does not exist"));
    }

    @Test
    void shouldNotUpdateNonExistingNote(){
        Note note = makeNote();
        note.setNoteId(1);
        when(jobRepository.findById(1)).thenReturn(new Job());
        when(repository.update(note)).thenReturn(false);
        Result<Note> result = service.update(note);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).contains("not found"));
    }
}