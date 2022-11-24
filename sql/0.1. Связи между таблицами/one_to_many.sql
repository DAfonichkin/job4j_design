create table organizations(
    id serial primary key,
    name varchar(255)
);

create table divisions(
    id serial primary key,
    name varchar(255),
    organization_id int references organizations(id)
);

insert into organizations(name) values ('Oracle');
insert into divisions(name, organization_id) VALUES ('IT-department', 1);

select * from organizations;
select * from divisions where id in (select organization_id from divisions);