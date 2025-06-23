package job_tracker.models;

import java.util.List;

public class Job {
    private int jobId;
    private String title;
    private String description;
    private JobStatus jobStatus;
    private String url;
    private List<Note> notes;
    private String company;

    public Job() {}

    public Job(String title, String description, JobStatus jobStatus, String url) {
        this.title = title;
        this.description = description;
        this.jobStatus = jobStatus;
        this.url = url;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
