CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values ('Ivan', 'Ivanov', 25, 'Russia');
insert into customers(first_name, last_name, age, country) values ('Petr', 'Petrov', 25, 'Russia');
insert into customers(first_name, last_name, age, country) values ('Ali', 'Rahmed', 30, 'Turkey');

select customers.first_name || ' ' || customers.last_name as name
from customers
where customers.age = (select min(с.age) age 
	  from customers с);

