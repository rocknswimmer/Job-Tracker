drop database if exists jobs;
create database jobs;
use jobs;

create table tracked(
job_id int primary key auto_increment,
job_title varchar(250) not null,
status varchar(50) not null,
url varchar(400) not null,
description varchar(65535) not null
);