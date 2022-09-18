insert into address (id, city, street, zip) values (1,'Fairfield','4thN',52557);

insert into category (id, name) values (1,'Chips');



INSERT INTO users (id, email, first_name, last_name, password, address_id)
VALUES (1, 'john@miu.edu', 'John', 'Doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',1); --123
INSERT INTO users (id, email, first_name, last_name, password, address_id)
VALUES (2, 'james@miu.edu', 'James', 'Hems', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2',1); --123

INSERT INTO role (id, role)
VALUES (1, 'ADMIN');
INSERT INTO role (id, role)
VALUES (2, 'USER');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, roles_id)
VALUES (2, 2);


insert into product (id, name, price, rating,id_user, category_id)
VALUES (1, 'iPhone', 1200, 1,1,1);

insert into product (id, name, price, rating,id_user, category_id)
VALUES (2, 'iPad', 900, 2,2,1);

insert into product (id, name, price, rating,id_user, category_id)
VALUES (3, 'Pen', 5, 1,1,1);

insert into product (id, name, price, rating,id_user, category_id)
VALUES (4, 'springing', 1200, 1,1,1);