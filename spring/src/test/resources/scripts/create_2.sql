drop table person if exists;

create table person (
  ID INT PRIMARY KEY,
  NAME VARCHAR(255),
  AGE INT
);

insert into person 
select * from csvread ('classpath:/scripts/testdata/person.csv');