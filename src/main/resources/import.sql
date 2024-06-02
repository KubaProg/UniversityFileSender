-- Test user (username: john, password: password) teacher
INSERT INTO app_user (id, username, password,first_name,last_name, role) VALUES (NULL, 'john', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK','John','Doe', 'ROLE_TEACHER');

-- Test user (username: alice, password: password) student
INSERT INTO app_user (id, username, password,first_name,last_name, role) VALUES (NULL, 'alice', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK','Alice','Smith', 'ROLE_STUDENT');


-- Inserting courses
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'Introduction to Physics', 1);
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'Introduction to Chemistry', 1);

-- Adding user course relationship to make alice a student of the courses
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 2, 1, 'PENDING');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 2, 2, 'ACCEPTED');

-- Adding an assignments
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'First Assignment', 'Complete the exercises from chapters 1-3', '2024-06-01', 1);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'Second Assignment', 'Complete the exercises from chapters 4-6', '2024-06-01', 1);

-- Adding user assignment relationships
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 2, 1, 'SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 2, 2, 'NOT_SUBMITTED');


-- Adding a submission file
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 1, 'example_file.pdf', 1001, 'application/pdf');




