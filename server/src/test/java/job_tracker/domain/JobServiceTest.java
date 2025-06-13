package job_tracker.domain;

import job_tracker.data.JobRepository;
import job_tracker.models.Job;
import job_tracker.models.JobStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class JobServiceTest {
    @Autowired
    JobService service;

    @MockBean
    JobRepository repository;

    public Job jobMaker(){
        Job job = new Job();
        job.setTitle("Testing job");
        job.setJobStatus(JobStatus.BEHAVIORAL);
        job.setDescription("testing the service validations not ability to mock");
        job.setUrl("testing.com");

        return job;
    }

    @Test
    void shouldNotAddNullJob(){
        Result<Job> result = service.add(null);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).equals("Job cannot be null"));
    }

    @Test
    void shouldNotAddNullJobTitle(){
        Job job = jobMaker();
        job.setTitle(null);
        Result<Job> result = service.add(job);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).equals("Job title is required"));
    }

    @Test
    void shouldNotAddNullJobStatus(){
        Job job = jobMaker();
        job.setJobStatus(null);
        Result<Job> result = service.add(job);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).equals("Job status is required"));
    }

    @Test
    void shouldNotAddEmptyJobDescription(){
        Job job = jobMaker();
        job.setDescription("");
        Result<Job> result = service.add(job);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).equals("Job description is required"));
    }

    @Test
    void shouldNotAddEmptyJobUrl(){
        Job job = jobMaker();
        job.setUrl("");
        Result<Job> result = service.add(job);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).equals("Job url is required"));
    }

    @Test
    void shouldHandleDBNotAdding(){
        Job job = jobMaker();
        when(repository.add(job)).thenReturn(null);
        Result<Job> result = service.add(job);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).equals("Job could not be added"));
    }

    @Test
    void shouldNotUpdateNonExistingJob(){
        Job job = jobMaker();
        when(repository.update(job)).thenReturn(false);
        Result<Job> result = service.update(job);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).equals("Job not found"));
    }


}