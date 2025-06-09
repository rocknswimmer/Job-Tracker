package job_tracker.models;

import java.util.List;

public class Job {
    private String title;
    private String description;
    private JobStatus jobStatus;
    private String url;
    private List<String> notes;

    public Job() {}

    public Job(String title, String description, JobStatus jobStatus, String url) {
        this.title = title;
        this.description = description;
        this.jobStatus = jobStatus;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
