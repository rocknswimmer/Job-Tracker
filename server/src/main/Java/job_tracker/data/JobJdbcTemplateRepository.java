package job_tracker.data;

import job_tracker.data.mappers.JobMapper;
import job_tracker.models.Job;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class JobJdbcTemplateRepository implements JobRepository {
    private final JdbcTemplate jdbcTemplate;

    public JobJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Job> findAll() {
        final String sql = "select job_id, job_title, status, url, description from tracked;";

        return jdbcTemplate.query(sql, new JobMapper());
    }

    @Override
    public Job findById(int jobId) {
        final String sql = "select job_id, job_title, status, url, description from tracked where job_id = ?;";

        return jdbcTemplate.query(sql, new JobMapper(), jobId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Job add(Job job) {
        final String sql = "insert into tracked (job_title, status, url, description) values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getJobStatus().toString());
            ps.setString(3, job.getUrl());
            ps.setString(4, job.getDescription());
            return ps;
        }, keyHolder);

        if(affectedRows <= 0){
            return null;
        }

        job.setJobId(keyHolder.getKey().intValue());
        return job;
    }

    @Override
    public boolean update(Job job) {
        final String sql = "update tracked set job_title = ?, status = ?, url = ?, description = ? where job_id = ?;";

        return jdbcTemplate.update(sql, job.getTitle(), job.getJobStatus().toString(), job.getUrl(),
                job.getDescription(), job.getJobId()) > 0;
    }

    @Override
    public boolean delete(int jobId) {
         final String sql = "delete from tracked where job_id = ?;";

         return jdbcTemplate.update(sql, jobId) > 0;
    }
}
