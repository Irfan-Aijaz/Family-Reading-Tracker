INSERT INTO prizes (prize_name, description, milestone_minutes, user_group, max_prizes, date_start, date_end, family_id)
VALUES ('Chew Toy','My children are dogs',2,'PARENT',2,'08/10/2021','08/10/2021', 1);

INSERT INTO books (isbn,title,author,pages_total) VALUES ('9780060254926','Where the Wild Things Are','Maurice Sendak','40');

INSERT INTO claim_prize_request_statuses (claim_prize_request_status_desc) VALUES ('Pending');
INSERT INTO claim_prize_request_statuses (claim_prize_request_status_desc) VALUES ('Approved');
INSERT INTO claim_prize_request_statuses (claim_prize_request_status_desc) VALUES ('Rejected');