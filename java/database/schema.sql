BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, books, user_book, sessions, family, prizes, user_prize;
DROP SEQUENCE IF EXISTS seq_user_id, seq_family_id, seq_session_id, seq_prize_id, seq_claimed_prize_id, seq_claim_prize_request_status_id;

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
  
CREATE SEQUENCE seq_prize_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_claimed_prize_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_claim_prize_request_status_id
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
	pages_read int NOT NULL,
	minutes_read int NOT NULL,
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

CREATE TABLE prizes (
	prize_id int DEFAULT nextval('seq_prize_id'::regclass) NOT NULL,
	prize_name varchar(20) NOT NULL,
	description text NOT NULL,
	milestone_minutes int NOT NULL,
	user_group varchar(10) NOT NULL,
	max_prizes int NOT NULL,
	date_start date NOT NULL,
	date_end date NOT NULL,
	family_id int NOT NULL,
	CONSTRAINT PK_prize_id PRIMARY KEY (prize_id)
	
);

CREATE TABLE user_prize (
	user_id int NOT NULL,
	prize_id int NOT NULL,
	CONSTRAINT PK_user_prize PRIMARY KEY (user_id, prize_id),
	CONSTRAINT FK_user_prize_user FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_user_prize_prize FOREIGN KEY (prize_id) REFERENCES prizes (prize_id)


);

CREATE TABLE claimed_prizes (
	claimed_prize_id int DEFAULT nextval('seq_claimed_prize_id'::regclass) NOT NULL,
	user_id int NOT NULL,
	description text NOT NULL,
	milestone_minutes int NOT NULL,
	date_claimed date NOT NULL,
	family_id int NOT NULL,
	CONSTRAINT PK_claimed_prizes PRIMARY KEY (claimed_prize_id)
	
);

CREATE TABLE claim_prize_request_statuses (
	claim_prize_request_status_id int DEFAULT nextval('seq_claim_prize_request_status_id'::regclass) NOT NULL,
	claim_prize_request_status_desc varchar(10) NOT NULL,
	CONSTRAINT PK_claim_prize_request_statuses PRIMARY KEY (claim_prize_request_status_id)
);


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');




COMMIT TRANSACTION;
