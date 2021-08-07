BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, books, user_book, sessions, family;
DROP SEQUENCE IF EXISTS seq_user_id, seq_family_id, seq_session_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_family_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_session_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE family (
        family_id int DEFAULT nextval('seq_family_id'::regclass) NOT NULL,
        family_name varchar(15) NOT NULL,
        CONSTRAINT PK_family_id PRIMARY KEY (family_id)
);

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	family_id int,
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT FK_family_id FOREIGN KEY (family_id) REFERENCES family (family_id)
);

CREATE TABLE books (
	isbn varchar(20) NOT NULL,
	title varchar(50) NOT NULL,
	author varchar(30) NOT NULL,
	pages_total int NOT NULL,
	genre varchar(15),
	summary text,
	CONSTRAINT PK_isbn PRIMARY KEY (isbn)
);

CREATE TABLE user_book (
	user_id int NOT NULL,
	isbn varchar(20) NOT NULL,
	pages_read int,
	completed boolean,
	CONSTRAINT PK_user_book PRIMARY KEY (user_id, isbn),
	CONSTRAINT FK_user_book_user FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_user_book_book FOREIGN KEY (isbn) REFERENCES books (isbn)
);

CREATE TABLE sessions (
        session_id int DEFAULT nextval('seq_session_id'::regclass) NOT NULL,
        user_id int NOT NULL,
        isbn varchar(20) NOT NULL,
        day_session date NOT NULL,
        time_start time NOT NULL,
        time_end time NOT NULL,
		pages_read int NOT NULL,
        format varchar(15) NOT NULL,
        notes text NOT NULL,
        CONSTRAINT PK_session_id PRIMARY KEY (session_id),
        CONSTRAINT FK_session_user FOREIGN KEY (user_id) REFERENCES users (user_id),
        CONSTRAINT FK_session_isbn FOREIGN KEY (isbn) REFERENCES books (isbn)
);



INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780060254926','Where the Wild Things Are','Maurice Sendak','40');


COMMIT TRANSACTION;
