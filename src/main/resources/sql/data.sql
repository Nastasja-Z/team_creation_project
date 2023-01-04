insert into roles(name)
values ('ROLE_ADMIN'),
       ('ROLE_PM'),
       ('ROLE_HR'),
       ('ROLE_USER');

insert into users(password, username)
values ('$2a$10$RYofohWktwl5xDkPm9FQQuwjubusMDz9rfO21tUWb1dB.41HRBq3C', 'admin'),
       ('$2a$10$NOfPE/3LRpK/9GkOxn7ls.0GGdqC37GZOxoaFZSZkcNvpzEaxfbTG', 'PM'),
       ('$c@"k`*rNw+*7ZJsYFjVhhjJ?K.%gRr{v?#?tGTL>,k%P4SU:$=m^8`>ug=S', 'HR'),
       ('$EmQ2L2T45gY87YRbjh8akCMk5VagsudGF2kQBzJGJhHJtpWJw5L4gEa4m9M', 'testuser');

insert into roles_users (user_id, role_id)
values ((select id from users where username = 'admin'), (select id from roles where name = 'ROLE_ADMIN')),
       ((select id from users where username = 'testuser'), (select id from roles where name = 'ROLE_USER')),
       ((select id from users where username = 'PM'), (select id from roles where name = 'ROLE_PM')),
       ((select id from users where username = 'HR'), (select id from roles where name = 'ROLE_HR'));

insert into projects(project_name, budget, start_project, end_project, length, laboriousness)
values ('Web-app for Tesla', 5000, current_date, current_date, 5, 400),
        ('Unity 3D project', 6000, current_date, current_date, 5, 400),
        ('Test cases', 7000, current_date, current_date, 5, 400);

insert into users_projects(project_id, user_id)
values ((select id from projects where project_name = 'Web-app for Tesla'), (select id from users where username = 'PM')),
        ((select id from projects where project_name = 'Web-app for Tesla'), (select id from users where username = 'HR'));

insert into indicators (indicator_name)
values ('Design patterns'),
       ('Nest.js'),
       ('OOP paradigm'),
       ('S.O.L.I.D Principles'),
       ('Functional testing'),
       ('Team player'),
       ('Attention to details'),
       ('Business understanding'),
       ('test9');

insert into indicators_projects(project_id, indicator_id, level)
values ((select id from projects where project_name = 'Web-app for Tesla'),(select id from indicators where indicator_name = 'Design patterns'), 3),
        ((select id from projects where project_name = 'Web-app for Tesla'),(select id from indicators where indicator_name = 'Nest.js'), 1),
        ((select id from projects where project_name = 'Web-app for Tesla'),(select id from indicators where indicator_name = 'OOP paradigm'), 2),
        ((select id from projects where project_name = 'Web-app for Tesla'),(select id from indicators where indicator_name = 'S.O.L.I.D Principles'), 2),
        ((select id from projects where project_name = 'Web-app for Tesla'),(select id from indicators where indicator_name = 'Functional testing'), 3),
        ((select id from projects where project_name = 'Web-app for Tesla'),(select id from indicators where indicator_name = 'Team player'), 2),
        ((select id from projects where project_name = 'Web-app for Tesla'),(select id from indicators where indicator_name = 'Attention to details'), 2),
        ((select id from projects where project_name = 'Web-app for Tesla'),(select id from indicators where indicator_name = 'Business understanding'), 2);

insert into candidates(name_of_candidate, surname_of_candidate, salary_pro_hour, employment)
values  ('Elsa', 'Gard', 10.00, 40),
        ('Mark', 'Levi', 10.00, 30),
        ('Tritan', 'Peterson', 15.00, 20),
        ('Merch', 'Nenson', 11.00, 30),
        ('Kora', 'Biran', 8.00, 30),
        ('Nina', 'Lars', 12.00, 40),
        ('Medina', 'Oshim', 9.00, 30),
        ('Liana', 'Thoman', 7.00, 20),
        ('Korin', 'Gendalf', 15.00, 30),
        ('Tera', 'Minevra', 12.00, 20),
        ('Sofie', 'Krass', 11.00, 30),
        ('Olaf', 'Holz', 8.00, 40);

insert into competences(competence_name)
values ((select indicator_name from indicators where indicator_name = 'Design patterns')),
       ((select indicator_name from indicators where indicator_name = 'Nest.js')),
       ((select indicator_name from indicators where indicator_name = 'OOP paradigm')),
       ((select indicator_name from indicators where indicator_name = 'S.O.L.I.D Principles')),
       ((select indicator_name from indicators where indicator_name = 'Functional testing')),
       ((select indicator_name from indicators where indicator_name = 'Team player')),
       ((select indicator_name from indicators where indicator_name = 'Attention to details')),
       ((select indicator_name from indicators where indicator_name = 'Business understanding'));

insert into competences_candidates(competence_id, candidate_id, level)
values ((select id from competences where competence_name = 'Design patterns'),(select id from candidates where name_of_candidate = 'Elsa'), 4),
        ((select id from competences where competence_name = 'Design patterns'),(select id from candidates where name_of_candidate = 'Mark'), 1),
        ((select id from competences where competence_name = 'Design patterns'),(select id from candidates where name_of_candidate = 'Tritan'), 3),
        ((select id from competences where competence_name = 'Design patterns'),(select id from candidates where name_of_candidate = 'Merch'), 1),
        ((select id from competences where competence_name = 'Design patterns'),(select id from candidates where name_of_candidate = 'Kora'), 3),
        ((select id from competences where competence_name = 'Nest.js'),(select id from candidates where name_of_candidate = 'Elsa'), 1),
        ((select id from competences where competence_name = 'Nest.js'),(select id from candidates where name_of_candidate = 'Mark'), 1),
        ((select id from competences where competence_name = 'Nest.js'),(select id from candidates where name_of_candidate = 'Tritan'), 1),
        ((select id from competences where competence_name = 'Nest.js'),(select id from candidates where name_of_candidate = 'Merch'), 1),
        ((select id from competences where competence_name = 'Nest.js'),(select id from candidates where name_of_candidate = 'Kora'), 1),
        ((select id from competences where competence_name = 'OOP paradigm'),(select id from candidates where name_of_candidate = 'Elsa'), 1),
        ((select id from competences where competence_name = 'OOP paradigm'),(select id from candidates where name_of_candidate = 'Mark'), 2),
        ((select id from competences where competence_name = 'OOP paradigm'),(select id from candidates where name_of_candidate = 'Tritan'), 2),
        ((select id from competences where competence_name = 'OOP paradigm'),(select id from candidates where name_of_candidate = 'Merch'), 2),
        ((select id from competences where competence_name = 'OOP paradigm'),(select id from candidates where name_of_candidate = 'Kora'), 2),
        ((select id from competences where competence_name = 'S.O.L.I.D Principles'),(select id from candidates where name_of_candidate = 'Elsa'), 2),
        ((select id from competences where competence_name = 'S.O.L.I.D Principles'),(select id from candidates where name_of_candidate = 'Mark'), 2),
        ((select id from competences where competence_name = 'S.O.L.I.D Principles'),(select id from candidates where name_of_candidate = 'Tritan'), 2),
        ((select id from competences where competence_name = 'S.O.L.I.D Principles'),(select id from candidates where name_of_candidate = 'Merch'), 2),
        ((select id from competences where competence_name = 'S.O.L.I.D Principles'),(select id from candidates where name_of_candidate = 'Kora'), 2),
        ((select id from competences where competence_name = 'Functional testing'),(select id from candidates where name_of_candidate = 'Elsa'), 0),
        ((select id from competences where competence_name = 'Functional testing'),(select id from candidates where name_of_candidate = 'Mark'), 4),
        ((select id from competences where competence_name = 'Functional testing'),(select id from candidates where name_of_candidate = 'Tritan'), 4),
        ((select id from competences where competence_name = 'Functional testing'),(select id from candidates where name_of_candidate = 'Merch'), 3),
        ((select id from competences where competence_name = 'Functional testing'),(select id from candidates where name_of_candidate = 'Kora'), 3),
        ((select id from competences where competence_name = 'Team player'),(select id from candidates where name_of_candidate = 'Elsa'), 2),
        ((select id from competences where competence_name = 'Team player'),(select id from candidates where name_of_candidate = 'Mark'), 1),
        ((select id from competences where competence_name = 'Team player'),(select id from candidates where name_of_candidate = 'Tritan'), 2),
        ((select id from competences where competence_name = 'Team player'),(select id from candidates where name_of_candidate = 'Merch'), 1),
        ((select id from competences where competence_name = 'Team player'),(select id from candidates where name_of_candidate = 'Kora'), 1),
        ((select id from competences where competence_name = 'Attention to details'),(select id from candidates where name_of_candidate = 'Elsa'), 3),
        ((select id from competences where competence_name = 'Attention to details'),(select id from candidates where name_of_candidate = 'Mark'), 2),
        ((select id from competences where competence_name = 'Attention to details'),(select id from candidates where name_of_candidate = 'Tritan'), 2),
        ((select id from competences where competence_name = 'Attention to details'),(select id from candidates where name_of_candidate = 'Merch'), 2),
        ((select id from competences where competence_name = 'Attention to details'),(select id from candidates where name_of_candidate = 'Kora'), 2),
        ((select id from competences where competence_name = 'Business understanding'),(select id from candidates where name_of_candidate = 'Elsa'), 3),
        ((select id from competences where competence_name = 'Business understanding'),(select id from candidates where name_of_candidate = 'Mark'), 3),
        ((select id from competences where competence_name = 'Business understanding'),(select id from candidates where name_of_candidate = 'Tritan'), 1),
        ((select id from competences where competence_name = 'Business understanding'),(select id from candidates where name_of_candidate = 'Merch'), 3),
        ((select id from competences where competence_name = 'Business understanding'),(select id from candidates where name_of_candidate = 'Kora'), 3);

insert into projects_candidates(project_id, candidate_id)
values  ((select id from projects where project_name = 'Web-app for Tesla'), (select id from candidates where name_of_candidate ='Elsa')),
        ((select id from projects where project_name = 'Web-app for Tesla'), (select id from candidates where name_of_candidate ='Mark')),
        ((select id from projects where project_name = 'Web-app for Tesla'), (select id from candidates where name_of_candidate ='Tritan')),
        ((select id from projects where project_name = 'Web-app for Tesla'), (select id from candidates where name_of_candidate ='Merch')),
        ((select id from projects where project_name = 'Web-app for Tesla'), (select id from candidates where name_of_candidate ='Kora'));


       /*((select id from competences where competence_name = 'test2'),(select id from candidates where name_of_candidate = 'test1'), 5),
       ((select id from competences where competence_name = 'test3'),(select id from candidates where name_of_candidate = 'test1'), 5),
       ((select id from competences where competence_name = 'test5'),(select id from candidates where name_of_candidate = 'test1'), 5),
       ((select id from competences where competence_name = 'test7'),(select id from candidates where name_of_candidate = 'test1'), 5),
       ((select id from competences where competence_name = 'test4'),(select id from candidates where name_of_candidate = 'test1'), 5),
       ((select id from competences where competence_name = 'test8'),(select id from candidates where name_of_candidate = 'test1'), 5),
       ((select id from competences where competence_name = 'test1'),(select id from candidates where name_of_candidate = 'test2'), 5),
       ((select id from competences where competence_name = 'test2'),(select id from candidates where name_of_candidate = 'test3'), 5),
       ((select id from competences where competence_name = 'test3'),(select id from candidates where name_of_candidate = 'test4'), 5),
       ((select id from competences where competence_name = 'test5'),(select id from candidates where name_of_candidate = 'test5'), 5),
       ((select id from competences where competence_name = 'test7'),(select id from candidates where name_of_candidate = 'test6'), 2),
       ((select id from competences where competence_name = 'test4'),(select id from candidates where name_of_candidate = 'test7'), 3),
       ((select id from competences where competence_name = 'test8'),(select id from candidates where name_of_candidate = 'test8'), 2),
       ((select id from competences where competence_name = 'test2'),(select id from candidates where name_of_candidate = 'test2'), 5),
       ((select id from competences where competence_name = 'test4'),(select id from candidates where name_of_candidate = 'test3'), 3),
       ((select id from competences where competence_name = 'test5'),(select id from candidates where name_of_candidate = 'test4'), 4),
       ((select id from competences where competence_name = 'test7'),(select id from candidates where name_of_candidate = 'test5'), 1);*/

/*
insert into projects_candidates(project_id, candidate_id)
values  ((select id from projects where project_name = 'test1'), (select id from candidates where name_of_candidate ='test1')),
        ((select id from projects where project_name = 'test1'), (select id from candidates where name_of_candidate ='test6')),
        ((select id from projects where project_name = 'test1'), (select id from candidates where name_of_candidate ='test2')),
        ((select id from projects where project_name = 'test1'), (select id from candidates where name_of_candidate ='test3')),
        ((select id from projects where project_name = 'test1'), (select id from candidates where name_of_candidate ='test4')),
        ((select id from projects where project_name = 'test2'), (select id from candidates where name_of_candidate ='test5')),
        ((select id from projects where project_name = 'test3'), (select id from candidates where name_of_candidate ='test7')),
        ((select id from projects where project_name = 'test2'), (select id from candidates where name_of_candidate ='test8')),
        ((select id from projects where project_name = 'test3'), (select id from candidates where name_of_candidate ='test2')),
        ((select id from projects where project_name = 'test2'), (select id from candidates where name_of_candidate ='test1'));*/
