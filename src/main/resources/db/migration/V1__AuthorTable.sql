CREATE table author (
    id bigserial primary key,
    name varchar(30) not null,
    age int not null,
    unique (name)
)