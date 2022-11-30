create table car_bodies (
  	id serial primary key,
	name varchar(250)
); 
  
create table car_engines (
  	id serial primary key,
	name varchar(250)
); 
  
create table car_transmissions (
  	id serial primary key,
	name varchar(250)
); 
  
create table cars (
  	id serial primary key,
	name varchar(250),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
); 
  
insert into car_bodies (name) values ('sedan'), ('hatchback'), ('universal');
insert into car_engines (name) values ('2.0'), ('2.5'), ('3.0');
insert into car_transmissions (name) values ('5MT'), ('6MT'), ('6AT'), ('8AT');
insert into cars (name, body_id, engine_id, transmission_id) 
  values ('lada',1,1,1), ('toyota',2,3,null), ('bmw',2,null,4), ('jeep',null,3,3);

select c.id ID, c.name car_name, b.name body_name, e.name engine_name, t.name transmission_name 
from cars c
left join 
car_bodies b 
on c.body_id = b.id
left join 
car_engines e 
on c.engine_id = e.id
left join 
car_transmissions t 
on c.transmission_id = t.id;

select b.name body_name
from car_bodies b
left join 
cars c 
on c.body_id = b.id
where  c.id is null;

select e.name engine_name
from car_engines e
left join 
cars c 
on c.engine_id = e.id
where  c.id is null;

select t.name transmission_name
from car_transmissions t
left join 
cars c 
on c.transmission_id = t.id
where c.id is null;