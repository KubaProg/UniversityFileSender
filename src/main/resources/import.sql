-- Test user (username: janusz, password: password) teacher
INSERT INTO app_user (id, username, password,first_name,last_name, role) VALUES (1, 'john', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK','John','Doe', 'ROLE_TEACHER');

-- Test user (username: alice, password: password) student
INSERT INTO app_user (id, username, password,first_name,last_name, role) VALUES (2, 'alice', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK','Alice','Smith', 'ROLE_STUDENT');


-- Inserting courses
INSERT INTO course (id, course_name, teacher_id) VALUES (1, 'Introduction to Physics', 1);
INSERT INTO course (id, course_name, teacher_id) VALUES (2, 'Introduction to Chemistry', 1);

-- Adding user course relationship to make alice a student of the courses
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (1, 2, 1, 'PENDING');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (2, 2, 2, 'ACCEPTED');

-- Adding an assignments
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (1, 'First Assignment', 'Complete the exercises from chapters 1-3', '2024-06-01', 1);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (2, 'Second Assignment', 'Complete the exercises from chapters 4-6', '2024-06-01', 1);

-- Adding user assignment relationships
INSERT INTO user_assignment_relationship (id, user_id, assignment_id, status) VALUES (1, 1, 1, 'PENDING');
INSERT INTO user_assignment_relationship (id, user_id, assignment_id, status) VALUES (2, 1, 2, 'ACCEPTED');

-- Adding a submission
INSERT INTO submission (id, submission_date, user_assignment_relationship_id) VALUES (1, '2024-05-08', 1);

-- Adding a submission file
INSERT INTO submission_file (id, submission_id, file_name) VALUES (1, 1, 'example_file.pdf');



