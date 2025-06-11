package job_tracker.data;

import job_tracker.data.mappers.NoteMapper;
import job_tracker.models.Note;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class NoteJdbcTemplateRepository implements NoteRepository{
    private final JdbcTemplate jdbcTemplate;

    public NoteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Note> findByJobId(int jobId) {
        final String sql = "select note_id, note, job_id from notes where job_id = ?;";
        return jdbcTemplate.query(sql, new NoteMapper(), jobId);
    }

    @Override
    public Note findById(int noteId) {
        final String sql = "select note_id, note, job_id from notes where note_id = ?;";
        return jdbcTemplate.query(sql, new NoteMapper(), noteId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Note add(Note note) {
        final String sql = "insert into notes (note, job_id) values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, note.getContent());
            ps.setInt(2, note.getJobId());
            return ps;
        }, keyHolder);

        if(affectedRows <= 0){
            return null;
        }
        note.setNoteId(keyHolder.getKey().intValue());

        return note;
    }

    @Override
    public boolean update(Note note) {
        final String sql = "update notes set note = ? where note_id = ?;";

        return jdbcTemplate.update(sql, note.getContent(), note.getNoteId()) > 0;
    }

    @Override
    public boolean deleteById(int noteId) {
        final String sql = "delete from notes where note_id = ?;";

        return jdbcTemplate.update(sql, noteId) > 0;
    }
}
