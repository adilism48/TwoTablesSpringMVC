create schema if not exists app;

create table if not exists app.user
(
    id            serial primary key,
    name          varchar(50) not null,
    email         varchar(50) not null,
    date_of_birth date not null
);

create table if not exists app.phone
(
    id           serial primary key,
    phone_number varchar(50) not null,
    user_id      int references app.user (id) on delete cascade
);