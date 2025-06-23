drop database if exists jobs_test;
create database jobs_test;
use jobs_test;

create table tracked(
job_id int primary key auto_increment,
job_title varchar(250) not null,
status varchar(50) not null,
url varchar(400) not null,
description text(65535) not null,
company varchar(100) not null
);

create table notes(
note_id int primary key auto_increment,
note varchar(1000) not null,
job_id int not null,
constraint fk_note_job foreign key(job_id)
references tracked(job_id)
);

delimiter //
create procedure set_known_good_state()
begin

	delete from notes;
    alter table notes auto_increment = 1;
    delete from tracked;
    alter table tracked auto_increment = 1;

    insert into tracked (job_title, status, url, description, company) values
    ("junior dev", "APPLIED", "fakeurl.com", "description here", "fake Company"),
    ("job to delete", "FINAL", "deleteurl.com", "need a job with no notes to delete", "delete Company"),
    ("job to update", "FINAL", "updateurl.com", "need a job to update", "update Company");
    -- more here when get to tests and know
    
    insert into notes (note, job_id) values
    ("test note", 1),
    ("Note to update",1),
    ("Note to delete", 1);


end //
--  Change the statement terminator back to the original.
delimiter ;

