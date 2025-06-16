package job_tracker.controllers;

import job_tracker.domain.JobService;
import job_tracker.domain.Result;
import job_tracker.models.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000")

public class JobController {
    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping
    public List<Job> findAll(){
        return service.findAll();
    }

    @GetMapping("/{jobId}")
    public Job findById(@PathVariable int jobId){
        return service.findById(jobId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Job job){
        Result<Job> result = service.add(job);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Object> update(@PathVariable int jobId, @RequestBody Job job){
        if(jobId != job.getJobId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Job> result = service.update(job);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteById(@PathVariable int jobId){
        if(service.deleteById(jobId).isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
