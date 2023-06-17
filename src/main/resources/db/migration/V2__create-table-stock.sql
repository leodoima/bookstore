create table stock(
    id bigint not null auto_increment,
    book_id bigint not null,
    available_quantity int not null,

    primary key (id),
    foreign key (book_id) references books(id)
);