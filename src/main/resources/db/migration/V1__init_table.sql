-- USE springdb;

-- CREATE TABLE  IF NOT EXISTS users (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255) NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS books (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     title VARCHAR(255) NOT NULL,
--     user_id BIGINT,
--     FOREIGN KEY (user_id) REFERENCES users(id)
-- );

-- Vô hiệu hóa kiểm tra khóa ngoại
SET FOREIGN_KEY_CHECKS = 0;

-- Xóa tất cả các bảng (dùng IF EXISTS để không bị lỗi nếu bảng không tồn tại)
DROP TABLE IF EXISTS Student_Answer_Log;
DROP TABLE IF EXISTS Student_Test_Attempt;
DROP TABLE IF EXISTS Test_Question;
DROP TABLE IF EXISTS Class_Test;
DROP TABLE IF EXISTS Joins;
DROP TABLE IF EXISTS Teaches;
DROP TABLE IF EXISTS User_Role;
DROP TABLE IF EXISTS Answer;
DROP TABLE IF EXISTS Question;
DROP TABLE IF EXISTS Test;
DROP TABLE IF EXISTS Class;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS Teacher;
DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Semester;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS user_tokens;
DROP TABLE IF EXISTS user;

-- Bật lại kiểm tra khóa ngoại
SET FOREIGN_KEY_CHECKS = 1;

-- ---------------------------------
-- Bảng cho Thực thể & Siêu lớp
-- ---------------------------------

-- Bảng siêu lớp User (Người dùng)
-- CREATE TABLE IF NOT EXISTS User (
--     User_ID INT AUTO_INCREMENT PRIMARY KEY,
--     Password VARCHAR(255) NOT NULL,
--     Fname VARCHAR(50),
--     Minit CHAR(1),
--     Lname VARCHAR(50),
--     Bdate DATE,
--     Address VARCHAR(255),
--     Phone_number VARCHAR(20),
--     Email VARCHAR(100) UNIQUE NOT NULL
-- ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS user
(
    User_ID       INT PRIMARY KEY AUTO_INCREMENT,
    full_name     VARCHAR(50)         NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    phone_number  VARCHAR(15)         NOT NULL,
    password      VARCHAR(255)        NOT NULL,
    profile_img   VARCHAR(255),
    is_active     BOOLEAN  DEFAULT TRUE,
    role_id       INT,
    last_login    DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_of_birth DATE,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE user_tokens
(
    id            INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID token',
    USER_ID       INT          NOT NULL COMMENT 'Liên kết tới admin',
    access_token  VARCHAR(500) NOT NULL COMMENT 'Token truy cập',
    refresh_token VARCHAR(500) NOT NULL COMMENT 'Token làm mới',
    expires_at    DATETIME     NOT NULL COMMENT 'Thời điểm token hết hạn',
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo token',
    updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Ngày cập nhật',
    FOREIGN KEY (USER_ID) REFERENCES user (USER_ID)
);

-- Bảng Role (Vai trò)
CREATE TABLE IF NOT EXISTS Role (
                                    Role_ID INT AUTO_INCREMENT PRIMARY KEY,
                                    Role_name VARCHAR(50) NOT NULL UNIQUE
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng Semester (Học kỳ)
CREATE TABLE IF NOT EXISTS Semester (
                                        Semester_Name VARCHAR(50) PRIMARY KEY
    -- Giả định tên học kỳ là duy nhất, ví dụ: 'Học kỳ 1 2025-2026'
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng Course (Khóa học)
CREATE TABLE IF NOT EXISTS Course (
                                      Course_ID VARCHAR(20) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Language VARCHAR(50), -- Thuộc tính 'Language' của Course
    Semester_Name VARCHAR(50), -- Mối quan hệ N-1 với Semester
    FOREIGN KEY (Semester_Name) REFERENCES Semester(Semester_Name)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------
-- Bảng cho các Lớp con (Chuyên biệt hóa)
-- ---------------------------------

-- Bảng Teacher (Giáo viên) - Lớp con của User
CREATE TABLE IF NOT EXISTS Teacher (
                                       User_ID INT PRIMARY KEY,
                                       Specialization VARCHAR(100),
    Qualification VARCHAR(100),
    FOREIGN KEY (User_ID) REFERENCES User(User_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng Student (Học sinh) - Lớp con của User
CREATE TABLE IF NOT EXISTS Student (
                                       User_ID INT PRIMARY KEY,
                                       Grade_level VARCHAR(50),
    Major VARCHAR(100),
    Education_level VARCHAR(100),
    FOREIGN KEY (User_ID) REFERENCES User(User_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------
-- Bảng cho Thực thể Yếu
-- ---------------------------------

-- Bảng Class (Lớp học) - Thực thể yếu phụ thuộc vào Course
CREATE TABLE IF NOT EXISTS Class (
                                     Course_ID VARCHAR(20),
    `Group` VARCHAR(20), -- 'Group' là từ khóa, nên dùng dấu backtick
    PRIMARY KEY (Course_ID, `Group`),
    FOREIGN KEY (Course_ID) REFERENCES Course(Course_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------
-- Bảng cho các Thực thể chính còn lại
-- ---------------------------------

-- Bảng Test (Bài kiểm tra)
CREATE TABLE IF NOT EXISTS Test (
                                    Test_ID INT AUTO_INCREMENT PRIMARY KEY,
                                    Title VARCHAR(255) UNIQUE NOT NULL,
    Description TEXT,
    Pass_code VARCHAR(50),
    Status BOOLEAN DEFAULT TRUE,
    Duration INT,
    Questions INT,
    Submissions INT,
    Creator_Teacher_ID INT, -- Mối quan hệ 1-N 'create'
    FOREIGN KEY (Creator_Teacher_ID) REFERENCES Teacher(User_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng Question (Câu hỏi)
CREATE TABLE IF NOT EXISTS Question (
                                        Question_ID INT AUTO_INCREMENT PRIMARY KEY,
                                        Question_text TEXT NOT NULL,
                                        Composer_Teacher_ID INT, -- Mối quan hệ 1-N 'compose'
                                        FOREIGN KEY (Composer_Teacher_ID) REFERENCES Teacher(User_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Bảng Answer (Câu trả lời)
CREATE TABLE IF NOT EXISTS Answer (
                                      Answer_ID INT AUTO_INCREMENT PRIMARY KEY,
                                      Answer_text TEXT NOT NULL,
                                      Correct_answer BOOLEAN DEFAULT FALSE, -- Thuộc tính 'Correct_answer'
                                      Question_ID INT, -- Mối quan hệ 1-N 'has'
                                      FOREIGN KEY (Question_ID) REFERENCES Question(Question_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------
-- Bảng cho các Mối quan hệ N-N (Bảng liên kết)
-- ---------------------------------

-- Mối quan hệ N-N 'assigned to' giữa User và Role
CREATE TABLE IF NOT EXISTS User_Role (
    User_ID INT,
    Role_ID INT,
    PRIMARY KEY (User_ID, Role_ID),
    FOREIGN KEY (User_ID) REFERENCES User(User_ID),
    FOREIGN KEY (Role_ID) REFERENCES Role(Role_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Mối quan hệ N-N 'teach' giữa Teacher và Class
CREATE TABLE IF NOT EXISTS Teaches (
    Teacher_User_ID INT,
    Course_ID VARCHAR(20),
    `Group` VARCHAR(20),
    PRIMARY KEY (Teacher_User_ID, Course_ID, `Group`),
    FOREIGN KEY (Teacher_User_ID) REFERENCES Teacher(User_ID),
    FOREIGN KEY (Course_ID, `Group`) REFERENCES Class(Course_ID, `Group`)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Mối quan hệ N-N 'join' giữa Student và Class
CREATE TABLE IF NOT EXISTS Joins (
    Student_User_ID INT,
    Course_ID VARCHAR(20),
    `Group` VARCHAR(20),
    PRIMARY KEY (Student_User_ID, Course_ID, `Group`),
    FOREIGN KEY (Student_User_ID) REFERENCES Student(User_ID),
    FOREIGN KEY (Course_ID, `Group`) REFERENCES Class(Course_ID, `Group`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Mối quan hệ N-N 'conduct' giữa Class và Test (có thuộc tính)
CREATE TABLE IF NOT EXISTS Class_Test (
    Course_ID VARCHAR(20),
    `Group` VARCHAR(20),
    Test_ID INT,
    Deadline DATETIME,
    Create_date DATE,
    Time INT, -- Thời gian làm bài (ví dụ: số phút)
    Number_question INT,
    PRIMARY KEY (Course_ID, `Group`, Test_ID),
    FOREIGN KEY (Course_ID, `Group`) REFERENCES Class(Course_ID, `Group`),
    FOREIGN KEY (Test_ID) REFERENCES Test(Test_ID)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Mối quan hệ N-N 'include' giữa Test và Question
CREATE TABLE IF NOT EXISTS Test_Question (
    Test_ID INT,
    Question_ID INT,
    PRIMARY KEY (Test_ID, Question_ID),
    FOREIGN KEY (Test_ID) REFERENCES Test(Test_ID),
    FOREIGN KEY (Question_ID) REFERENCES Question(Question_ID)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Mối quan hệ N-N 'Takes' giữa Student và Test (có thuộc tính)
CREATE TABLE IF NOT EXISTS Student_Test_Attempt (
                                                    Student_User_ID INT,
                                                    Test_ID INT,
                                                    Start_time DATETIME,
                                                    Submit_time DATETIME,
    -- Thuộc tính 'Result' (dạng dotted) là thuộc tính dẫn xuất,
    -- nó sẽ được tính toán chứ không lưu trữ.
                                                    PRIMARY KEY (Student_User_ID, Test_ID),
    FOREIGN KEY (Student_User_ID) REFERENCES Student(User_ID),
    FOREIGN KEY (Test_ID) REFERENCES Test(Test_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Mối quan hệ N-N 'select' giữa Student và Answer (có thuộc tính)
-- Bảng này ghi lại câu trả lời của sinh viên cho từng câu hỏi trong một lần làm bài
CREATE TABLE IF NOT EXISTS Student_Answer_Log (
                                                  Student_User_ID INT,
                                                  Selected_Answer_ID INT, -- Có thể NULL nếu là câu hỏi tự luận
                                                  Student_answer_text TEXT, -- Thuộc tính 'Student_answer_text'
                                                  PRIMARY KEY (Student_User_ID, Selected_Answer_ID),
    -- Liên kết đến lần làm bài cụ thể
    FOREIGN KEY (Student_User_ID) REFERENCES Student(User_ID),
    FOREIGN KEY (Selected_Answer_ID) REFERENCES Answer(Answer_ID)
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;