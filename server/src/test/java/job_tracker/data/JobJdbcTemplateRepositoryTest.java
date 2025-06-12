package job_tracker.data;

import job_tracker.models.Job;
import job_tracker.models.JobStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobJdbcTemplateRepositoryTest {
    @Autowired
    JobJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    public Job jobMaker(){
        Job job = new Job();

        job.setTitle("test title");
        job.setJobStatus(JobStatus.BEHAVIORAL);
        job.setUrl("testurl.com");
        job.setDescription("testing repo");

        return job;
    }

    @Test
    void shouldFindAll(){
        List<Job> jobs = repository.findAll();
        assertNotNull(jobs);
        assertTrue(jobs.size() > 0);
    }

    @Test
    void shouldFindByExistingId(){
        Job job = repository.findById(1);
        assertNotNull(job);
        assertEquals(job.getTitle(), "junior dev");
        assertEquals(job.getJobStatus(), JobStatus.APPLIED);
    }

    @Test
    void shouldNotFindByNonExistingId(){
        Job job = repository.findById(10000);
        assertNull(job);
    }

    @Test
    void shouldAddJob(){
        Job job = jobMaker();
        Job actual = repository.add(job);

        assertNotNull(actual);
        assertEquals("testing repo", actual.getDescription());
    }

    @Test
    void shouldUpdateExistingJob(){
        Job job = jobMaker();
        job.setJobId(3);
        assertTrue(repository.update(job));
    }

    @Test
    void shouldNotUpdateNonExistingJob(){
        Job job = jobMaker();
        job.setJobId(3000);
        assertFalse(repository.update(job));
    }

    @Test
    void shouldDeleteExistingJob(){
        assertTrue(repository.delete(2));
    }

    @Test
    void shouldNotDeleteNonExistingJob(){
        assertFalse(repository.delete(200));
    }

}