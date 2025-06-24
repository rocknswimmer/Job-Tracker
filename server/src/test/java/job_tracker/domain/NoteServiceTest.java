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
    void shouldNotAddLongNote(){
        Note note = makeNote();
        note.setContent("Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque sem placerat in id cursus mi pretium tellus duis convallis tempus leo eu aenean sed diam urna tempor pulvinar vivamus fringilla lacus nec metus bibendum egestas iaculis massa nisl malesuada lacinia integer nunc posuere ut hendrerit semper vel class aptent taciti sociosqu ad litora torquent per conubia nostra inceptos himenaeos orci varius natoque penatibus et magnis dis parturient montes nascetur ridiculus mus donec rhoncus eros lobortis nulla molestie mattis scelerisque maximus eget fermentum odio phasellus non purus est efficitur laoreet mauris pharetra vestibulum fusce dictum risus.Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque sem placerat in id cursus mi pretium tellus duis convallis tempus leo eu aenean sed diam urna tempor pulvinar vivamus fringilla lacus nec metus bibendum egestas iaculis massa nisl malesuada lacinia integer nunc posuere ut hendrerit semper vel class aptent taciti sociosqu ad litora torquent per conubia nostra inceptos himenaeos orci varius natoque penatibus et magnis dis parturient montes nascetur ridiculus mus donec rhoncus eros lobortis nulla molestie mattis scelerisque maximus eget fermentum odio phasellus non purus est efficitur laoreet mauris pharetra vestibulum fusce dictum risus.Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque sem placerat in id cursus mi pretium tellus duis convallis tempus leo eu aenean sed diam urna tempor pulvinar vivamus fringilla lacus nec metus bibendum egestas iaculis massa nisl malesuada lacinia integer nunc posuere ut hendrerit semper vel class aptent taciti sociosqu ad litora torquent per conubia nostra inceptos himenaeos orci varius natoque penatibus et magnis dis parturient montes nascetur ridiculus mus donec rhoncus eros lobortis nulla molestie mattis scelerisque maximus eget fermentum odio phasellus non purus est efficitur laoreet mauris pharetra vestibulum fusce dictum risus.");
        Result<Note> actual = service.add(note);
        assertFalse(actual.isSuccess());
        assertTrue(actual.getMessages().get(0).equals("Note content must be less than 1000 characters"));
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