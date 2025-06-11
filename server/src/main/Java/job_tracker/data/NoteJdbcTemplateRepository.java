package job_tracker.data;

import job_tracker.data.mappers.NoteMapper;
import job_tracker.models.Note;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteJdbcTemplateRepository implements NoteRepository{
    private final JdbcTemplate jdbcTemplate;

    public NoteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Note> findByJobId(int jobId) {
        String sql = "select note_id, note, job_id from notes where job_id = ?;";
        return jdbcTemplate.query(sql, new NoteMapper(), jobId);
    }

    @Override
    public Note findById(int noteId) {
        String sql = "select note_id, note, job_id from notes where note_id = ?;";
        return jdbcTemplate.query(sql, new NoteMapper(), noteId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Note add(Note note) {
        return null;
    }

    @Override
    public boolean update(Note note) {
        return false;
    }

    @Override
    public boolean deleteById(int noteId) {
        return false;
    }
}
