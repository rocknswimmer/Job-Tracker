drop database if exists jobs_test;
create database jobs_test;
use jobs_test;

create table tracked(
job_id int primary key auto_increment,
job_title varchar(250) not null,
status varchar(50) not null,
url varchar(400) not null,
description varchar(65535) not null
);

delimiter //
create procedure set_known_good_state()
begin

    delete from tracked;
    alter table tracked auto_increment = 1;

    insert into tracked (job_title, status, url, description) values
    ("junior dev", "APPLIED", "fakeurl.com", "description here");
    -- more here when get to tests and know


end //
--  Change the statement terminator back to the original.
delimiter ;

