create table departments (
  id serial primary key,
  name varchar(250)
);

create table employees (
  id serial primary key,
  name varchar(250),
  department_id int references departments(id)
);

insert into departments (name) 
  values ('moscow dep.'), ('spb dep.'), ('sibir dep.');
insert into employees (name, department_id) 
  values ('Ivanov', 1), ('Petrov', 1), ('Sidorov', 2), ('Shishkin', null);

/*2. Выполнить запросы с left, right, full, cross соединениями*/
select * from 
departments d
left join 
employees e
on e.department_id =  d.id;

select * from 
departments d
right join 
employees e
on e.department_id =  d.id;

select * from 
departments d
full join 
employees e
on e.department_id =  d.id;

select * from 
departments
cross join 
employees;

/*3. Используя left join найти департаменты, у которых нет работников*/
select d.name from 
departments d
left join 
employees e
on e.department_id =  d.id
where e.id is null;

/*4. Используя left и right join написать запросы, 
которые давали бы одинаковый результат
(порядок вывода колонок в эти запросах также должен быть идентичный). */
select * from 
departments d
left join 
employees e
on e.department_id =  d.id
where e.id is not null;

select * from 
departments d
right join 
employees e
on e.department_id =  d.id
where d.id is not null;

/*5. Создать таблицу teens с атрибутами name, gender и заполнить ее. 
Используя cross join составить все возможные разнополые пары */
create table teens (
  id serial primary key,
  name varchar(250),
  gender varchar(50));

insert into teens (name, gender) 
  values ('Anna', 'female'), ('Petr', 'male'), ('Ivan', 'male'), ('Dmitriy', 'male'), ('Olga', 'female');

select t.name, tt.name
from
teens t cross join teens tt
where t.gender <> tt.gender;