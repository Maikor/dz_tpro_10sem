CREATE TABLE authors
(
    id    serial primary key,
    firstname        VARCHAR(40) not null,
    lastname        VARCHAR(40) not null,
    nationality        VARCHAR(40)
);


insert into authors (firstname, lastname)
values ( 'ivan', 'ivanof'),
	( 'petr', 'petrof'),
	( 'dima', 'kazakov'),
	( 'sasha', 'alexandrof')



CREATE TABLE articles
(
    id    serial primary key,
    content        VARCHAR(4000) not null,
    id_Author        int references authors(id),
    words        numeric,
    prepos	numeric,
    sentences numeric
);

-- , words, prepos, sentences

insert into articles (content, id_Author)
values ('This is called maintaining the referential integrity of your data. In simplistic database systems this would be implemented (if at all) by first looking at the cities table to check if a matching record exists, and then inserting or rejecting the new weather records. This approach has a number of problems and is very inconvenient, so PostgreSQL can do this for you.', '2'),
	('The behavior of foreign keys can be finely tuned to your application. We will not go beyond this simple example in this tutorial, but just refer you to Chapter 5 for more information. Making correct use of foreign keys will definitely improve the quality of your database applications, so you are strongly encouraged to learn about them.', '3'),
	('Now try inserting an invalid record:', '4')


	select * from authors, articles where authors.id = articles.id_author
--select * from authors
--select Count(*) from articles
--select * from articles


--SELECT id_author, SUM(words)/COUNT(*) as "words", SUM(prepos)/COUNT(*) as "prepos", SUM(sentences)/COUNT(*) as "sentences" FROM articles GROUP BY id_author
