create table persons (
  	id serial primary key,
	name varchar(250),
	hardskill_id int references person_hardskills(id),
	softskill_id int references person_softskills(id)
); 
insert into persons (name, hardskill_id, softskill_id) 
  values ('Вася', 1, null), ('Петя', null, 2), ('Сергей', 1, 2);