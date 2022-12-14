create table rules(
	id serial primary key,
    name varchar(250),
	description text
);

create table roles(
	id serial primary key,
    name varchar(250),
	description text
);

create table rules_of_roles(
	id serial primary key,
    role_id int references roles (id),
    rule_id int references rules (id)
);

create table users(
	id serial primary key,
    name varchar(250),
	role_id int references roles(id)
);

create table categories(
	id serial primary key,
    name varchar(250)
);

create table states(
	id serial primary key,
    name varchar(250)
);

create table item(
	id serial primary key,
    creating_date date,
	description text,	
	user_id int references users(id),
	state_id int references states(id),
	category_id int references categories(id)
);

create table commentaries(
	id serial primary key,
    name varchar(250),
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
    name varchar(250),
	item_id int references item(id)
);
