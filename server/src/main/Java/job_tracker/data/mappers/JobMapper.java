package job_tracker.data.mappers;

import job_tracker.models.Job;
import job_tracker.models.JobStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JobMapper implements RowMapper<Job> {

    @Override
    public Job mapRow(ResultSet resultSet, int i) throws SQLException {
        Job job = new Job();
        job.setJobId(resultSet.getInt("job_id"));
        job.setTitle(resultSet.getString("job_title"));
        job.setJobStatus(JobStatus.valueOf(resultSet.getString("status")));
        job.setUrl(resultSet.getString("url"));
        job.setDescription(resultSet.getString("description"));
        job.setCompany(resultSet.getString("company"));

        return job;
    }
}
