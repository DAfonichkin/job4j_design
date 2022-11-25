create table cars (
	id serial primary key,
	model varchar(250)
);

create table car_numbers (
	id serial primary key, 
	car_number varchar(50) unique,	
	car_id int references cars(id) unique
);


insert into cars(model) values ('VAZ 2109');
insert into car_numbers(car_number,car_id) values ('e000ee13',1);

select * from cars;
select * from car_numbers;