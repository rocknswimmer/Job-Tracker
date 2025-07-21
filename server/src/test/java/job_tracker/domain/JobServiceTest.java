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
        job.setCompany("test company");

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
    void shouldNotAddLongJobTitle(){
        Job job = jobMaker();
        job.setTitle("Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque sem placerat in id cursus mi pretium tellus duis convallis tempus leo eu aenean sed diam urna tempor pulvinar vivamus fringilla lacus nec metus bibendum egestas iaculis massa nisl malesuada lacinia integer nunc posuere ut hendrerit semper vel class aptent taciti sociosqu ad litora torquent per conubia nostra inceptos himenaeos orci varius natoque penatibus et magnis dis parturient montes nascetur ridiculus mus donec rhoncus eros lobortis nulla molestie mattis scelerisque maximus eget fermentum odio phasellus non purus est efficitur laoreet mauris pharetra vestibulum fusce dictum risus.Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus ex sapien vitae pellentesque sem placerat in id cursus mi pretium tellus duis convallis tempus leo eu aenean sed diam urna tempor pulvinar vivamus fringilla lacus nec metus bibendum egestas iaculis massa nisl malesuada lacinia integer nunc posuere ut hendrerit semper vel class aptent taciti sociosqu ad litora torquent per conubia nostra inceptos himenaeos orci varius natoque penatibus et magnis dis parturient montes nascetur ridiculus mus donec rhoncus eros lobortis nulla molestie mattis scelerisque maximus eget fermentum odio phasellus non purus est efficitur laoreet mauris pharetra vestibulum fusce dictum risus.Lorem ipsum dolor sit amet consectetur adipiscing eli");
        Result<Job> result = service.add(job);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().get(0).equals("Job title must be 250 characters or less"));
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

    //company tests
}