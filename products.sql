CREATE table products (
	id serial primary key,
	name varchar(255),
	count integer,
	oldPosition boolean
);
insert into products (name,count,oldPosition) values ('стул',10,true);
update products set count=8;
delete from products;
select * from products;