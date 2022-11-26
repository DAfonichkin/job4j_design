insert into rules(name, description)
values ('creating', 'user can create item');
insert into rules(name, description)
values ('reading', 'user can read item');
insert into rules(name, description)
values ('changing', 'user can change item');
insert into rules(name, description)
values ('deleting', 'user can delete item');

insert into roles(name, description)
values ('manager', 'can create and read items');
insert into roles(name, description)
values ('head of department', 'can create, read, update and delete items');

insert into rules_of_roles(role_id, rule_id)
values (1, 1);
insert into rules_of_roles(role_id, rule_id)
values (1, 2);
insert into rules_of_roles(role_id, rule_id)
values (2, 1);
insert into rules_of_roles(role_id, rule_id)
values (2, 2);
insert into rules_of_roles(role_id, rule_id)
values (2, 3);
insert into rules_of_roles(role_id, rule_id)
values (2, 4);

insert into users(name, role_id)
values ('Ivanov Ivan', 1);
insert into users(name, role_id)
values ('Petrov Petr', 2);

insert into categories(name)
values ('Item for product');
insert into categories(name)
values ('Item for service');

insert into states(name)
values ('not served');
insert into states(name)
values ('served, not saled');
insert into states(name)
values ('saled');

insert into item(creating_date, description, user_id, state_id, category_id)
values ('2022-09-28','item from sidorov', 1, 2, 1);
insert into item(creating_date, description, user_id, state_id, category_id)
values ('2022-09-30','new item', 1, 1, 2);

insert into commentaries(name, item_id)
values ('we cant serve it', 1);
insert into commentaries(name, item_id)
values ('need tomorrow', 2);

insert into attachs(name, item_id)
values ('\\price.xls', 1);

