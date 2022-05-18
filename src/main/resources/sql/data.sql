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

insert into projects(project_name, budget, start_project, end_project)
values ('test1', 3000000, current_date, current_date),
        ('test2', 5000000, current_date, current_date),
        ('test3', 7000000, current_date, current_date);

insert into users_projects(project_id, user_id)
values ((select id from projects where project_name = 'test1'), (select id from users where username = 'PM')),
        ((select id from projects where project_name = 'test1'), (select id from users where username = 'HR'));

insert into indicators (indicator_name)
values ('test1'),
       ('test2'),
       ('test3'),
       ('test4'),
       ('test5'),
       ('test6'),
       ('test7'),
       ('test8'),
       ('test9');

insert into indicators_projects(project_id, indicator_id, level)
values ((select id from projects where project_name = 'test1'),(select id from indicators where indicator_name = 'test1'), 5),
        ((select id from projects where project_name = 'test1'),(select id from indicators where indicator_name = 'test2'), 2),
        ((select id from projects where project_name = 'test1'),(select id from indicators where indicator_name = 'test3'), 4),
        ((select id from projects where project_name = 'test1'),(select id from indicators where indicator_name = 'test4'), 4),
        ((select id from projects where project_name = 'test3'),(select id from indicators where indicator_name = 'test2'), 2),
        ((select id from projects where project_name = 'test3'),(select id from indicators where indicator_name = 'test3'), 3),
        ((select id from projects where project_name = 'test3'),(select id from indicators where indicator_name = 'test4'), 4),
        ((select id from projects where project_name = 'test2'),(select id from indicators where indicator_name = 'test5'), 5);

insert into candidates(name_of_candidate, surname_of_candidate, salary_pro_hour, employment)
values  ('test1', 'test1', 11.00, 1),
        ('test2', 'test2', 12.00, 2),
        ('test3', 'test3', 13.00, 3),
        ('test4', 'test4', 14.00, 4),
        ('test5', 'test5', 15.00, 5),
        ('test6', 'test6', 16.00, 6),
        ('test7', 'test7', 17.00, 7),
        ('test8', 'test8', 18.00, 8);

insert into competences(competence_name)
values ((select indicator_name from indicators where indicator_name = 'test1')),
       ((select indicator_name from indicators where indicator_name = 'test2')),
       ((select indicator_name from indicators where indicator_name = 'test3')),
       ((select indicator_name from indicators where indicator_name = 'test5')),
       ((select indicator_name from indicators where indicator_name = 'test7')),
       ((select indicator_name from indicators where indicator_name = 'test4')),
       ((select indicator_name from indicators where indicator_name = 'test8'));

insert into competences_candidates(competence_id, candidate_id, level)
values ((select id from competences where competence_name = 'test1'),(select id from candidates where name_of_candidate = 'test1'), 4),
       ((select id from competences where competence_name = 'test2'),(select id from candidates where name_of_candidate = 'test1'), 5),
       ((select id from competences where competence_name = 'test2'),(select id from candidates where name_of_candidate = 'test2'), 2),
       ((select id from competences where competence_name = 'test1'),(select id from candidates where name_of_candidate = 'test2'), 3),
       ((select id from competences where competence_name = 'test2'),(select id from candidates where name_of_candidate = 'test3'), 2),
       ((select id from competences where competence_name = 'test3'),(select id from candidates where name_of_candidate = 'test3'), 5),
       ((select id from competences where competence_name = 'test5'),(select id from candidates where name_of_candidate = 'test1'), 3),
       ((select id from competences where competence_name = 'test8'),(select id from candidates where name_of_candidate = 'test4'), 4),
       ((select id from competences where competence_name = 'test4'),(select id from candidates where name_of_candidate = 'test5'), 1);

