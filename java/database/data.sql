INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780060254926','Where the Wild Things Are','Maurice Sendak','40');
INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780439708180','Harry Potter and the Sorcerers Stone','J.K. Rowling','309');
INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780439064873','Harry Potter and the Chamber of Secrets','J.K. Rowling','341');
INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780439136365','Harry Potter and the Prisoner of Azkaban','J.K. Rowling','448');
INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780425232200','The Help','Kathryn Stockett','544');
INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780689852237','The House of the Scorpion','Nancy Farmer','434');
INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780091877101','The Pelican Brief','John Grisham','498');
INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780345806789','The Shining','Stephen King','688');

INSERT INTO claim_prize_request_statuses (claim_prize_request_status_desc) VALUES ('Pending');
INSERT INTO claim_prize_request_statuses (claim_prize_request_status_desc) VALUES ('Approved');
INSERT INTO claim_prize_request_statuses (claim_prize_request_status_desc) VALUES ('Rejected');


INSERT INTO family (family_id, family_name) VALUES('2','Simpson');
INSERT INTO users (user_id, username, password_hash, role, family_id) VALUES('1005','Homer','$2a$10$CwXiFJ9EpiNbJiM893xWP.FELWzq9IJ9MIAfchqb3TsSfgNab3qs.','ROLE_ADMIN', '2');
INSERT INTO users (user_id, username, password_hash, role, family_id) VALUES('1006','Bart','$2a$10$CwXiFJ9EpiNbJiM893xWP.FELWzq9IJ9MIAfchqb3TsSfgNab3qs.','ROLE_USER', '2');
INSERT INTO users (user_id, username, password_hash, role, family_id) VALUES('1007','Lisa','$2a$10$CwXiFJ9EpiNbJiM893xWP.FELWzq9IJ9MIAfchqb3TsSfgNab3qs.','ROLE_USER', '2');

INSERT INTO user_book (user_id,isbn, pages_read, minutes_read, completed) VALUES ('1005', '9780425232200','544', '300', 'true');
