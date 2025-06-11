package job_tracker.data.mappers;

import job_tracker.models.Note;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NoteMapper implements RowMapper<Note> {
    @Override
    public Note mapRow(ResultSet resultSet, int i) throws SQLException {
        Note note = new Note();
        note.setNoteId(resultSet.getInt("note_id"));
        note.setJobId(resultSet.getInt("job_id"));
        note.setContent(resultSet.getString("note"));

        return note;
    }
}
