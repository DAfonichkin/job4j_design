create table specialists (
	id serial primary key,
	initials varchar(250)
);

create table skills (
	id serial primary key, 
	name varchar(250)	
);

create table specialists_skills(
	id serial primary key,
	specialist_id int references specialists(id),
	skill_id int references skills(id)	
);

insert into specialists(initials) values ('Ivanov I.I.');
insert into skills(name) values ('car-driving');
insert into specialists_skills(specialist_id, skill_id) VALUES (1, 1);

select * from specialists;
select * from skills;
select * from specialists_skills;