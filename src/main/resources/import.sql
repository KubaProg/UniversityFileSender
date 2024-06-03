-- Test users (teachers and students)
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'john', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'John', 'Doe', 'ROLE_TEACHER');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'alice', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'Alice', 'Smith', 'ROLE_STUDENT');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'bob', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'Bob', 'Johnson', 'ROLE_STUDENT');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'carol', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'Carol', 'Williams', 'ROLE_STUDENT');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'david', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'David', 'Brown', 'ROLE_TEACHER');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'eve', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'Eve', 'Davis', 'ROLE_STUDENT');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'frank', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'Frank', 'Miller', 'ROLE_STUDENT');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'grace', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'Grace', 'Wilson', 'ROLE_STUDENT');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'heidi', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'Heidi', 'Moore', 'ROLE_STUDENT');
INSERT INTO app_user (id, username, password, first_name, last_name, role) VALUES (NULL, 'ivan', '$2y$10$U9W.2XuqVecQwj/LF7zCbOdBZ4/ri398WSxcq97b9OU7AlXoEkyUK', 'Ivan', 'Taylor', 'ROLE_TEACHER');

-- Inserting courses
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'Introduction to Physics', 1);
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'Introduction to Chemistry', 1);
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'Advanced Mathematics', 5);
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'English Literature', 5);
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'Biology Basics', 10);
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'World History', 10);
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'Computer Science', 1);
INSERT INTO course (id, course_name, teacher_id) VALUES (NULL, 'Art History', 5);

-- Adding user course relationships
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 2, 1, 'PENDING');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 2, 2, 'ACCEPTED');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 3, 1, 'ACCEPTED');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 3, 3, 'PENDING');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 4, 3, 'ACCEPTED');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 4, 4, 'PENDING');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 6, 5, 'ACCEPTED');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 6, 6, 'PENDING');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 7, 5, 'PENDING');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 7, 6, 'ACCEPTED');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 8, 7, 'PENDING');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 8, 8, 'ACCEPTED');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 9, 7, 'ACCEPTED');
INSERT INTO course_enrollment (id, student_id, course_id, status) VALUES (NULL, 9, 8, 'PENDING');

-- Adding assignments
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'First Assignment', 'Complete the exercises from chapters 1-3', '2024-06-01', 1);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'Second Assignment', 'Complete the exercises from chapters 4-6', '2024-06-01', 1);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'Lab Report', 'Submit the lab report for Experiment 5', '2024-06-10', 2);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'Math Homework', 'Solve problems from chapter 2', '2024-06-05', 3);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'Essay on Shakespeare', 'Write an essay on Hamlet', '2024-06-15', 4);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'Biology Quiz', 'Answer the questions on cell structure', '2024-06-20', 5);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'History Paper', 'Write a paper on the Industrial Revolution', '2024-06-25', 6);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'Programming Assignment', 'Complete the Java project', '2024-06-30', 7);
INSERT INTO assignment (id, assignment_name, description, deadline_date, course_id) VALUES (NULL, 'Art Analysis', 'Analyze a Renaissance painting', '2024-07-05', 8);

-- Adding user assignment relationships
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 2, 1, 'SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 2, 2, 'NOT_SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 3, 1, 'SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 3, 4, 'NOT_SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 4, 4, 'SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 4, 5, 'NOT_SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 6, 6, 'SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 6, 7, 'NOT_SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 7, 6, 'PENDING');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 7, 7, 'SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 8, 8, 'SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 8, 9, 'NOT_SUBMITTED');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 9, 8, 'PENDING');
INSERT INTO student_assignment_relationship (id, student_id, assignment_id, status) VALUES (NULL, 9, 9, 'SUBMITTED');

-- Adding submission files
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 1, 'example_file.pdf', 1001, 'application/pdf');
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 2, 'second_assignment.docx', 1002, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 3, 'math_homework.pdf', 1003, 'application/pdf');
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 4, 'shakespeare_essay.pdf', 1004, 'application/pdf');
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 5, 'lab_report.pdf', 1005, 'application/pdf');
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 6, 'biology_quiz.pdf', 1006, 'application/pdf');
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 7, 'history_paper.pdf', 1007, 'application/pdf');
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 8, 'programming_assignment.java', 1008, 'text/x-java-source');
INSERT INTO file (id, student_assignment_relationship_id, file_name, data, file_type) VALUES (NULL, 9, 'art_analysis.docx', 1009, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document');
