use jobs;

insert into tracked (job_title, status, url, description, company) values
    ("junior dev", "APPLIED", "fakeurl.com", "description here", "fake Company"),
    ("job to delete", "FINAL", "deleteurl.com", "need a job with no notes to delete", "delete Company"),
    ("job to update", "FINAL", "updateurl.com", "need a job to update", "update Company");
    -- more here when get to tests and know

insert into notes (note, job_id) values
    ("test note", 1),
    ("Note to update",1),
    ("Note to delete", 1);