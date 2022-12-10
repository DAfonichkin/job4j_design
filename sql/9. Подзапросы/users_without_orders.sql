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

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values (10, 1);
insert into orders(amount, customer_id) values (5, 2);

select customers.first_name || ' ' || customers.last_name as name
from customers
where customers.id not in (select o.customer_id 
	  from orders o);