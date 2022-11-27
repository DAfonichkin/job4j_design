create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date)
values ('kingfish', 1000, '1946-01-01');
insert into fauna (name, avg_age, discovery_date)
values ('snowcat', 11000, null);
insert into fauna (name, avg_age, discovery_date)
values ('sandturtle', 25000, '2005-09-15');
insert into fauna (name, avg_age, discovery_date)
values ('beowolf', 15000, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
