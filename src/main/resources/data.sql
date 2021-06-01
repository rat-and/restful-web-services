---------------------------------------------------------------------
-- users
---------------------------------------------------------------------
insert into user(id, birth_date, name)
values (123, CURRENT_TIMESTAMP, 'Cycuś');

insert into user(id, birth_date, name)
values (124, CURRENT_TIMESTAMP, 'Jąder');

insert into user(id, birth_date, name)
values (125, CURRENT_TIMESTAMP, 'Rzeżączka');

---------------------------------------------------------------------
-- posts
---------------------------------------------------------------------
insert into post(id, description, user_id)
values (1001, 'ekipa', '123');

insert into post(id, description, user_id)
values (1002, 'lody', '123');

insert into post(id, description, user_id)
values (1003, 'finanse', '125');