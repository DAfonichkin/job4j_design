create table products(
    id serial primary key,
    name varchar(255)
);

create table products_weight(
    id serial primary key,
    weight int,
  	weighing_date date,
    product_id int references products(id)
);

insert into products(name)
values ('стол');
insert into products(name)
values ('стул');
insert into products(name)
values ('телевизор');

insert into products_weight(weight, product_id, weighing_date)
values(15, 1, '2022-01-01');
insert into products_weight(weight, product_id, weighing_date)
values(5, 2, '2022-01-01');
insert into products_weight(weight, product_id, weighing_date)
values(8, 3, '2022-05-01');
insert into products_weight(weight, product_id, weighing_date)
values(9, 3, '2022-10-01');

select prod.name as Номенклатура, weight.weight as Вес 
from products prod
join products_weight weight
on weight.product_id = prod.id;

select prod.name as Номенклатура, weight.weight as Вес,
weight.weighing_date as "Дата взвешивания"
from products prod
join products_weight weight
on weight.product_id = prod.id;

select prod.name as Номенклатура, weight.weight as Вес,
weight.weighing_date as "Дата взвешивания"
from products prod
join products_weight weight
on weight.product_id = prod.id and weight.weighing_date > '2022-06-01';