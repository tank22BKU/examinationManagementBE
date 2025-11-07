-- Tạo database
CREATE DATABASE IF NOT EXISTS examination;
USE examination;
-- Tạo bảng user
CREATE TABLE users (
    user_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()), -- UUID dạng xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
    full_name VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    bdate DATE,
    address TEXT,
    phone_number VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    last_login DATETIME,
    profile_img VARCHAR(255),
    create_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    password VARCHAR(255) NOT NULL, -- Nên mã hóa (ví dụ: bcrypt)
    sex ENUM('Male', 'Female', 'Other')
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE roles (
    role_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    role_name VARCHAR(50) NOT NULL UNIQUE
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE user_tokens (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    user_id CHAR(36) NOT NULL,
    token VARCHAR(512) NOT NULL,
    token_type ENUM('ACCESS', 'REFRESH') DEFAULT 'ACCESS',
    expired BOOLEAN DEFAULT FALSE,
    revoked BOOLEAN DEFAULT FALSE,
    issued_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    expires_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE teachers (
    user_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    specialization VARCHAR(100),
    qualification VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE students (
    user_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    grade_level VARCHAR(50),
    major VARCHAR(100),
    education_level VARCHAR(100),
    FOREIGN KEY (user_ID) REFERENCES users(user_ID)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE semesters (
    semester_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    semester_name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    academic_year VARCHAR(20) NOT NULL
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE courses (
    course_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    course_name VARCHAR(100) NOT NULL,
    course_language VARCHAR(50), -- Thuộc tính 'Language' của Course
    semester_id CHAR(36) NOT NULL, -- Mối quan hệ N-1 với Semester
    FOREIGN KEY (semester_id) REFERENCES semesters(semester_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE classes (
    class_group VARCHAR(50),
    course_id CHAR(36) NOT NULL,
    PRIMARY KEY ( class_group, course_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE tests (
    test_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    user_id CHAR(36) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    test_score INT,
    test_status ENUM('NOT_STARTED', 'IN_PROGRESS', 'SUBMITTED'),
    question_number INT NOT NULL CHECK (question_number > 0),
    duration INT NOT NULL, -- Thời gian làm bài (phút)
    pass_code VARCHAR(20), -- Mã tham gia (nếu có)
	released_answer BOOLEAN DEFAULT FALSE,
    released_score BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES teachers(user_id)
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- select * from questions;
CREATE TABLE questions (
    question_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    user_id CHAR(36) NOT NULL,
    question_text VARCHAR(500) NOT NULL,
    question_score DECIMAL(5,2) NOT NULL,
    status ENUM('ANSWER_SAVED', 'NOT_YET_ANSWERED'),
    FOREIGN KEY (user_id) REFERENCES teachers(user_id)
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- select * from answers;
CREATE TABLE answers (
    answer_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    question_id CHAR(36) NOT NULL,
    answer_text VARCHAR(500) NOT NULL,
    correct_answer BOOLEAN NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions(question_id)
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE  users_roles (
    user_id CHAR(36) NOT NULL,
    role_id CHAR(36) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_ID) REFERENCES roles(role_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE  teachers_courses (
    user_id CHAR(36) NOT NULL,
    course_id CHAR(36) NOT NULL,
    PRIMARY KEY (user_id, course_id),
    FOREIGN KEY (user_id) REFERENCES teachers(user_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE  students_courses (
    user_id CHAR(36) NOT NULL,
    course_id CHAR(36) NOT NULL,
    PRIMARY KEY (user_id, course_id),
    FOREIGN KEY (user_id) REFERENCES students(user_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE classes_tests (
    course_id CHAR(36) NOT NULL,
    class_group VARCHAR(50),
    test_id CHAR(36) NOT NULL,
    deadline DATETIME,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (course_id , class_group, test_id),
    FOREIGN KEY (course_id, class_group) REFERENCES classes(course_id, class_group),
    FOREIGN KEY (test_id) REFERENCES tests(test_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE tests_questions (
	question_id CHAR(36) NOT NULL,
    test_id CHAR(36) NOT NULL,
    PRIMARY KEY (question_id , test_id),
    FOREIGN KEY (question_id) REFERENCES questions(question_id),
    FOREIGN KEY (test_id) REFERENCES tests(test_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- select* from students_tests;
CREATE TABLE students_tests(
	user_id CHAR(36) NOT NULL,
    test_id CHAR(36) NOT NULL,
	start_time DATETIME,
    submit_time DATETIME,
	actual_time TIME,
    PRIMARY KEY (user_id, test_id),
    FOREIGN KEY (user_id) REFERENCES students(user_id),
    FOREIGN KEY (test_id) REFERENCES tests(test_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
-- select* from students_answers;
-- CREATE TABLE students_answers(
-- 	user_id CHAR(36) NOT NULL,
--     answer_id CHAR(36) NOT NULL,
-- 	student_answer_text VARCHAR(500) NOT NULL,
--     PRIMARY KEY (user_id, answer_id),
--     FOREIGN KEY (user_id) REFERENCES students(user_id),
--     FOREIGN KEY (answer_id) REFERENCES answers(answer_id)
-- ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE students_questions(
	user_id CHAR(36) NOT NULL,
    question_id CHAR(36) NOT NULL,
	student_answer_text VARCHAR(500) NOT NULL,
    PRIMARY KEY (user_id, question_id),
    FOREIGN KEY (user_id) REFERENCES students(user_id),
    FOREIGN KEY (question_id) REFERENCES questions(question_id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 select * from students_questions;
 select * from students_tests;
 select * from tests;
select * from students;





