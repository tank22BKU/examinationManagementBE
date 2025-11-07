INSERT INTO roles (role_id, role_name) VALUES
(UUID(), 'ADMIN'),
(UUID(), 'TEACHER'),
(UUID(), 'STUDENT');

INSERT INTO users (user_id, full_name, is_active, bdate, address, phone_number, email, password, sex)
VALUES
('11111111-1111-1111-1111-111111111111', 'Admin System', TRUE, '1990-01-01', 'Hà Nội', '0900000001', 'admin@example.com', 'admin123', 'Male'),
('22222222-2222-2222-2222-222222222222', 'Nguyễn Văn A', TRUE, '1985-03-15', 'Đà Nẵng', '0900000002', 'teacher@example.com', 'teacher123', 'Male'),
('33333333-3333-3333-3333-333333333333', 'Trần Thị B', TRUE, '2000-08-10', 'TP.HCM', '0900000003', 'student@example.com', 'student123', 'Female');
-- Admin
INSERT INTO users_roles (user_id, role_id)
SELECT '11111111-1111-1111-1111-111111111111', role_id FROM roles WHERE role_name = 'ADMIN';

-- Teacher
INSERT INTO users_roles (user_id, role_id)
SELECT '22222222-2222-2222-2222-222222222222', role_id FROM roles WHERE role_name = 'TEACHER';

-- Student
INSERT INTO users_roles (user_id, role_id)
SELECT '33333333-3333-3333-3333-333333333333', role_id FROM roles WHERE role_name = 'STUDENT';

INSERT INTO teachers (user_id, specialization, qualification)
VALUES ('22222222-2222-2222-2222-222222222222', 'Computer Science', 'Master of IT');

INSERT INTO students (user_id, grade_level, major, education_level)
VALUES ('33333333-3333-3333-3333-333333333333', 'Year 3', 'Software Engineering', 'Undergraduate');

INSERT INTO semesters (semester_id, semester_name, start_date, end_date, academic_year)
VALUES ('44444444-4444-4444-4444-444444444444', 'Fall 2025', '2025-09-01', '2025-12-31', '2025-2026');

INSERT INTO courses (course_id, course_name, course_language, semester_id)
VALUES ('55555555-5555-5555-5555-555555555555', 'Introduction to Databases', 'English', '44444444-4444-4444-4444-444444444444');

INSERT INTO classes (class_group, course_id)
VALUES ('DBSE1', '55555555-5555-5555-5555-555555555555');

INSERT INTO teachers_courses (user_id, course_id)
VALUES ('22222222-2222-2222-2222-222222222222', '55555555-5555-5555-5555-555555555555');

INSERT INTO students_courses (user_id, course_id)
VALUES ('33333333-3333-3333-3333-333333333333', '55555555-5555-5555-5555-555555555555');

INSERT INTO tests (test_id, user_id, title, description, test_score, test_status, question_number, duration, pass_code)
VALUES ('66666666-6666-6666-6666-666666666666', '22222222-2222-2222-2222-222222222222', 'Midterm Test', 'Database midterm exam', 100, 'NOT_STARTED', 3, 45, 'DB2025');

INSERT INTO classes_tests (course_id, class_group, test_id, deadline)
VALUES ('55555555-5555-5555-5555-555555555555', 'DBSE1', '66666666-6666-6666-6666-666666666666', '2025-12-01 23:59:59');

INSERT INTO questions (question_id, user_id, question_text, question_score, status)
VALUES
('77777777-7777-7777-7777-777777777777', '22222222-2222-2222-2222-222222222222', 'What is SQL?', 10.00, 'NOT_YET_ANSWERED'),
('88888888-8888-8888-8888-888888888888', '22222222-2222-2222-2222-222222222222', 'What does SELECT do?', 10.00, 'NOT_YET_ANSWERED'),
('99999999-9999-9999-9999-999999999999', '22222222-2222-2222-2222-222222222222', 'What is a primary key?', 10.00, 'NOT_YET_ANSWERED');

INSERT INTO answers (answer_id, question_id, answer_text, correct_answer)
VALUES
(UUID(), '77777777-7777-7777-7777-777777777777', 'A query language for databases', TRUE),
(UUID(), '77777777-7777-7777-7777-777777777777', 'A programming language', FALSE),
(UUID(), '88888888-8888-8888-8888-888888888888', 'Retrieve data from tables', TRUE),
(UUID(), '99999999-9999-9999-9999-999999999999', 'A unique identifier for each record', TRUE);

INSERT INTO tests_questions (question_id, test_id)
VALUES
('77777777-7777-7777-7777-777777777777', '66666666-6666-6666-6666-666666666666'),
('88888888-8888-8888-8888-888888888888', '66666666-6666-6666-6666-666666666666'),
('99999999-9999-9999-9999-999999999999', '66666666-6666-6666-6666-666666666666');

INSERT INTO students_tests (user_id, test_id, start_time, submit_time, actual_time)
VALUES ('33333333-3333-3333-3333-333333333333', '66666666-6666-6666-6666-666666666666', '2025-11-10 09:00:00', '2025-11-10 09:40:00', '00:40:00');

INSERT INTO students_answers (user_id, answer_id, student_answer_text)
SELECT '33333333-3333-3333-3333-333333333333', a.answer_id, a.answer_text
FROM answers a
WHERE a.correct_answer = TRUE;

