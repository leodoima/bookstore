create table books (
    id bigint not null auto_increment,
    title varchar(255) not null,
    author varchar(255) not null,
    publisher varchar(255) not null,
    publication_date date,
    sale_price float not null,

    primary key(id)
);