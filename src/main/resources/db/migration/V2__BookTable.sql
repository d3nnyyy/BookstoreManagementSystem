CREATE table book (
                        id bigserial primary key,
                        title varchar(30) not null,
                        year int not null,
                        genre varchar(30) not null,
                        pages int not null,
                        author_id bigint not null,
                        unique (title),
                        foreign key (author_id) references author(id)
)