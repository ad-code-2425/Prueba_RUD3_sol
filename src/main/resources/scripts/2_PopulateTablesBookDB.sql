USE bookdb;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Publishers
INSERT INTO `publishers` (`publisher_id`, `name`) VALUES
	(1, 'Bruma'),
	(2, 'John Wiley & Sons'),
	(3, 'Mike Murach & Associates'),
	(4, 'O''Reilly Media');

-- Authors
INSERT INTO `authors` (`author_id`, `first_name`, `middle_name`, `last_name`, `birth_date`) VALUES
	(1, 'John', NULL, 'Wiley', '1970-05-12'),
	(2, 'Ray', NULL, 'Harris', '1972-09-01'),
	(3, 'Joel', NULL, 'Murach', '1968-02-02'),
	(4, 'Robin', NULL, 'Nixon', '1961-01-01'),
	(5, 'Ada', NULL, 'Lovelace', '1815-12-10'),
	(6, 'Grace', NULL, 'Hopper', '1906-12-09'),
	(7, 'Alan', NULL, 'Turing', '1912-06-23'),
	(8, 'Dennis', NULL, 'Ritchie', '1941-09-09'),
	(9, 'Brian', NULL, 'Kernighan', '1942-01-01'),
	(10, 'Bjarne', NULL, 'Stroustrup', '1950-12-30');

-- Books
INSERT INTO `books` (`book_id`, `title`, `isbn`, `published_date`, `publisher_id`) VALUES
	(1, 'PHP Programming', '978-0000000001', '2023-01-01', 1),
	(2, 'HTML & CSS', '978-1118008188', '2011-11-08', 2),
	(3, 'Murach''s PHP and MySQL', '978-1943873005', '2022-08-31', 3),
	(4, 'Learning Java', '978-0596008734', '2005-03-01', 4),
	(5, 'C Programming Language', '978-0131103627', '1988-04-01', 2),
	(6, 'Introduction to Algorithms', '978-0262033848', '2009-07-31', 3),
	(7, 'Artificial Intelligence: A Modern Approach', '978-0136042594', '2010-12-11', 2),
	(8, 'The Art of Computer Programming', '978-0201896831', '1997-07-20', 1),
	(9, 'Structure and Interpretation of Computer Programs', '978-0262510875', '1996-07-25', 4),
	(10, 'Effective Java', '978-0134685991', '2018-01-06', 4);

-- Book-Author relationships
INSERT INTO `book_authors` (`book_id`, `author_id`) VALUES
	(1, 1),
	(1, 2),   -- PHP Programming también por Ray Harris
	(2, 4),   -- HTML & CSS por Robin Nixon
	(2, 5),   -- y Ada Lovelace
	(3, 3),
	(3, 6),   -- Murach + Grace Hopper
	(4, 7),
	(4, 8),   -- Java por Turing y Ritchie
	(5, 8),
	(5, 9),   -- C Programming Language por Ritchie y Kernighan
	(6, 7),
	(6, 10),  -- Algoritmos por Turing y Stroustrup
	(7, 5),
	(7, 6),   -- AI por Ada y Grace
	(8, 6),
	(8, 9),   -- Art of CP por Grace y Kernighan
	(9, 7),
	(9, 1),   -- SICP por Turing y Wiley
	(10, 10),
	(10, 4);  -- Effective Java por Stroustrup y Nixon

COMMIT;
