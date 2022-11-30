create table person_hardskills (
  	id serial primary key,
	name varchar(250)
); 
insert into person_hardskills (name) values ('java'), ('python'), ('c++');

create table person_softskills (
  	id serial primary key,
	name varchar(250)
); 
insert into person_softskills (name) values ('остроумие'), ('дружелюбность');

create table persons (
  	id serial primary key,
	name varchar(250),
	hardskill_id int references person_hardskills(id),
	softskill_id int references person_softskills(id)
); 
insert into persons (name, hardskill_id, softskill_id) 
  values ('Вася', 1, null), ('Петя', null, 2), ('Сергей', 1, 2);

create table projects (
  	id serial primary key,
	name varchar(250),
	hardskill_id int references person_hardskills(id)
); 
insert into projects (name, hardskill_id) 
  values ('Java-project', 1), ('C++-project', 3);


create view usefull_people_in_project_with_softskills
    as select pr.name project_name, p.id people_id, p.name people_name, ps.name people_softskill 
		from projects pr
		join persons p
		on p.hardskill_id =pr.hardskill_id
		join 
		person_softskills ps 
		on ps.id = p.softskill_id;;
		 
select * from usefull_people_in_project_with_softskills;
