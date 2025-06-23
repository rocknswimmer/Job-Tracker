drop database if exists jobs;
create database jobs;
use jobs;

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