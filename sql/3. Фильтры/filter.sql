create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values ('МОЛОКО');
insert into type(name) values ('СЫР');
insert into type(name) values ('РЫБА');
insert into type(name) values ('МОРОЖЕНОЕ');

insert into product(name,type_id,expired_date,price) 
  values ('Молоко коровье',1,'2022-11-30',50);
insert into product(name,type_id,expired_date,price) 
  values ('Молоко кокосовое',1,'2022-11-10',100);
insert into product(name,type_id,expired_date,price) 
  values ('Молоко козье',1,'2022-12-01',120);
insert into product(name,type_id,expired_date,price) 
  values ('Сыр плавленный',2,'2022-11-01',400);
insert into product(name,type_id,expired_date,price) 
  values ('Сыр моцарелла',2,'2022-12-10',600);
insert into product(name,type_id,expired_date,price) 
  values ('Сыр с плесенью',2,'2022-12-10',900);
insert into product(name,type_id,expired_date,price) 
  values ('Селедка',3,'2022-12-10',100);
insert into product(name,type_id,expired_date,price) 
  values ('Форель филе мороженое',3,'2022-12-10',900);
insert into product(name,type_id,expired_date,price) 
  values ('Фисташковое мороженое',4,'2022-12-10',100);
insert into product(name,type_id,expired_date,price) 
  values ('Сливочное мороженое',4,'2022-12-10',700);

select p.name "Продукт"
from product p
join type t
on t.id = p.type_id
where t.name = 'СЫР';

select p.name "Продукт"
from product p
where p.name like '%мороженое%';

select p.name "Продукт"
from product p
where p.expired_date < Now();

select p.name
from product p 
join (select max(p.price) price 
	  from product p) max_price
on p.price = max_price.price;

select t.name "Тип", count(p.name)
from product p
join type t
on t.id = p.type_id
group by t.name;

select p.name "Продукт"
from product p
join type t
on t.id = p.type_id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name "Тип", count(p.name)
from product p
join type t
on t.id = p.type_id
group by t.name
having count(p.name) < 10;

select p.name "Продукт", t.name "Тип"
from product p
join type t
on t.id = p.type_id;
