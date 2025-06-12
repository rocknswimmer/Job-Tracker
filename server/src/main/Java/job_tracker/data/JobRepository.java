package job_tracker.data;

import job_tracker.models.Job;

import java.util.List;

public interface JobRepository {

    List<Job> findAll();

    Job findById(int jobId);

    Job add(Job job);

    boolean update(Job job);

    boolean delete(int jobId);
}
