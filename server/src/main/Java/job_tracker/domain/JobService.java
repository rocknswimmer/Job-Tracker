package job_tracker.domain;

import job_tracker.data.JobRepository;
import job_tracker.models.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public List<Job> findAll(){
        return repository.findAll();
    }

    public Job findById(int jobId){
        return repository.findById(jobId);
    }

    public Result<Job> add(Job job){
        Result<Job> result = validate(job);

        if(!result.isSuccess()){
            return result;
        }
        Job added = repository.add(job);

        if(added == null){
            result.addMessage("Job could not be added", ResultType.INVALID);
        }

        result.setPayload(added);
        return result;
    }

    public Result<Job> update(Job job){
        Result<Job> result = validate(job);

        if(!result.isSuccess()){
            return result;
        }

        result.setPayload(job);

        if(!repository.update(job)){
            result.setPayload(null);
            result.addMessage("Job not found", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Job> deleteById(int jobId){
        Result<Job> result = new Result<>();

        if(!repository.delete(jobId)){
            result.addMessage("Job not found", ResultType.NOT_FOUND);
        }
        return result;
    }



    //validations
    private Result<Job> validate(Job job){
        Result<Job> result = new Result<>();


        if(job == null){
            result.addMessage("Job cannot be null", ResultType.INVALID);
            return result;
        }

        if(job.getTitle() == null || job.getTitle().isBlank()){
            result.addMessage("Job title is required", ResultType.INVALID);
        }

        if(job.getJobStatus() == null){
            result.addMessage("Job status is required", ResultType.INVALID);
        }

        if(job.getUrl() == null || job.getUrl().isBlank()){
            result.addMessage("Job url is required", ResultType.INVALID);
        }

        if(job.getDescription() == null || job.getDescription().isBlank()){
            result.addMessage("Job description is required", ResultType.INVALID);
        }

        if(job.getCompany() == null || job.getCompany().isBlank()){
            result.addMessage("Job company is required", ResultType.INVALID);
        }

        return result;
    }

}
