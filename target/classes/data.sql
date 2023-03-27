INSERT INTO books
(title, author, publisher, publication, price)
VALUES
('Fortaleza Digital', 'Dan Brown', 'Sextante', '1998-01-15', 31),
('Impacto Profundo', 'Dan Brown', 'Sextante', '2003-04-28', 35),
('Código da Vinci', 'Dan Brown', 'Arqueiro', '2004-01-25', 32),
('Simbolo Perdido', 'Dan Brown', 'Arqueiro', '2006-06-06', 27),
('Anjos e Demonios', 'Dan Brown', 'Arqueiro', '2008-03-25', 32);


insert into stock
(book_id, available_quantity)
values
(1, 10);