create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function calc_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create or replace function make_history_record()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date) 
		values (new.name, new.price, now());
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_after_trigger
after insert
on products
for each statement
execute procedure calc_tax();

create trigger tax_before_trigger
before insert
on products
for each row
execute procedure calc_tax();

create trigger history_after_trigger
after insert
on products
for each row
execute procedure make_history_record();

