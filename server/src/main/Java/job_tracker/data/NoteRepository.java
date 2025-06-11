package job_tracker.data;

import job_tracker.models.Note;

import java.util.List;

public interface NoteRepository {
    List<Note> findByJobId(int jobId);

    Note findById(int noteId);

    Note add(Note note);

    boolean update(Note note);

    boolean deleteById(int noteId);
}
