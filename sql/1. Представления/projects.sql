create table projects (
  	id serial primary key,
	name varchar(250),
	hardskill_id int references person_hardskills(id)
); 
insert into projects (name, hardskill_id) 
  values ('Java-project', 1), ('C++-project', 3);