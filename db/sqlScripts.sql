create table roles(
   id serial primary key not null,
   role_name varchar(100) unique
);

create table users(
   id serial primary key not null,
   user_name varchar(100) unique not null,
   user_password varchar(100)
);

create table users_roles(
   user_id int,
   role_id int references roles(id)
);

create table messages(
   id serial primary key not null,
   text varchar(1000),
   created date default now(),
   room_id int,
   user_id int
);

create table rooms(
   id serial primary key not null,
   name varchar(50) unique,
   created date not null default now(),
   user_id int
);

create table rooms_messages(
   room_id int,
   message_id int
);


insert into roles (id, role_name) values (1, 'ROLE_ADMIN');
insert into roles (id, role_name) values (2, 'ROLE_USER');