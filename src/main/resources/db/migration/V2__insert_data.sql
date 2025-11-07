-- INSERT IGNORE INTO users (name) VALUES ('Alice'), ('Bob');
-- INSERT IGNORE INTO users (name) VALUES ('Faker'), ('Oner');
-- INSERT IGNORE INTO books (title, user_id) VALUES ('Java Basics', 9), ('Spring Boot Guide', 9), ('MySQL Mastery', 10);
-- INSERT IGNORE INTO books (title, user_id) VALUES ('Python Basics', 9), ('DSA Tutorial', 10);
-- INSERT IGNORE INTO books (title, user_id) VALUES ('How to win Worlds 2025', 11), ('DSA Tutorial', 12);

-- USE springdb;

-- Xóa dữ liệu cũ (chạy cẩn thận!)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE User_Role;
TRUNCATE TABLE Teaches;
TRUNCATE TABLE Joins;
TRUNCATE TABLE Class_Test;
TRUNCATE TABLE Test_Question;
TRUNCATE TABLE Student_Test_Attempt;
TRUNCATE TABLE Student_Answer_Log;
TRUNCATE TABLE Answer;
TRUNCATE TABLE Question;
TRUNCATE TABLE Test;
TRUNCATE TABLE Class;
TRUNCATE TABLE Student;
TRUNCATE TABLE Teacher;
TRUNCATE TABLE Course;
TRUNCATE TABLE Semester;
TRUNCATE TABLE Role;
TRUNCATE TABLE User;
SET FOREIGN_KEY_CHECKS = 1;

-- ---------------------------------
-- 1. Bảng không có phụ thuộc (hoặc phụ thuộc cấp thấp nhất)
-- ---------------------------------

-- Bảng Role (Vai trò)
INSERT INTO Role (Role_ID, Role_name) VALUES
                                          (1, 'Admin'),
                                          (2, 'Teacher'),
                                          (3, 'Student');

-- Bảng Semester (Học kỳ)
INSERT INTO Semester (Semester_Name) VALUES
                                         ('Học kỳ 1 2024-2025'),
                                         ('Học kỳ 2 2024-2025');

-- Bảng User (Người dùng)
-- (Mật khẩu nên được băm (hashed) trong thực tế)
-- INSERT INTO User (User_ID, Password, Fname, Minit, Lname, Bdate, Address, Phone_number, Email) VALUES
--                                                                                                    (1, 'hashed_pass_gv1', 'An', 'V', 'Nguyễn', '1980-05-10', '123 Đường ABC, Q1, TPHCM', '0901234567', 'gv.an.nguyen@edu.vn'),
--                                                                                                    (2, 'hashed_pass_gv2', 'Bình', 'T', 'Trần', '1985-11-20', '456 Đường DEF, Q3, TPHCM', '0912345678', 'gv.binh.tran@edu.vn'),
--                                                                                                    (3, 'hashed_pass_hs1', 'Cường', 'V', 'Lê', '2003-01-15', '789 Đường GHI, Q.Bình Thạnh, TPHCM', '0987654321', 'sv.cuong.le@edu.vn'),
--                                                                                                    (4, 'hashed_pass_hs2', 'Dung', 'T', 'Phạm', '2004-03-22', '101 Đường JKL, Q.Tân Bình, TPHCM', '0978654321', 'sv.dung.pham@edu.vn'),
--                                                                                                    (5, 'hashed_pass_admin', 'Quản', 'T', 'Viên', '1990-07-30', '001 Đường XYZ, Q1, TPHCM', '0909090909', 'admin.qtv@edu.vn');

INSERT INTO user (full_name, email, phone_number, password, profile_img, is_active, role_id, last_login, date_of_birth)
VALUES
    ('Nguyen Van A', 'nguyenvana@example.com', '0909123456', '$2a$10$k6Zpzt3rXc/9BTx1yCFXV.BRPP8dI4PaSAm9YJL51ICH6Mi88Z0qe', 'avatar_a.png', TRUE, 1, NOW(), '1999-03-15'),

    ('Tran Thi B', 'tranthib@example.com', '0909234567', '$2a$10$k6Zpzt3rXc/9BTx1yCFXV.BRPP8dI4PaSAm9YJL51ICH6Mi88Z0qe', 'avatar_b.png', TRUE, 2, NOW(), '2000-07-22'),

    ('Le Hoang C', 'lehoangc@example.com', '0909345678', '$2a$10$k6Zpzt3rXc/9BTx1yCFXV.BRPP8dI4PaSAm9YJL51ICH6Mi88Z0qe', 'avatar_c.png', TRUE, 1, NOW(), '1998-12-05'),

    ('Pham Minh D', 'phaminhd@example.com', '0909456789', '$2a$10$k6Zpzt3rXc/9BTx1yCFXV.BRPP8dI4PaSAm9YJL51ICH6Mi88Z0qe', NULL, TRUE, 3, NOW(), '1997-09-11'),

    ('Vo Thi E', 'vothie@example.com', '0909567890', '$2a$10$k6Zpzt3rXc/9BTx1yCFXV.BRPP8dI4PaSAm9YJL51ICH6Mi88Z0qe', 'avatar_e.png', FALSE, 2, NOW(), '2001-02-27');


-- ---------------------------------
-- 2. Bảng chuyên biệt hóa (phụ thuộc vào User)
-- ---------------------------------

-- Bảng Teacher (Giáo viên)
INSERT INTO Teacher (User_ID, Specialization, Qualification) VALUES
                                                                 (1, 'Công nghệ phần mềm', 'Tiến sĩ'),
                                                                 (2, 'Hệ thống thông tin', 'Thạc sĩ');

-- Bảng Student (Học sinh)
INSERT INTO Student (User_ID, Grade_level, Major, Education_level) VALUES
                                                                       (3, 'Năm 3', 'Kỹ thuật phần mềm', 'Đại học'),
                                                                       (4, 'Năm 2', 'Khoa học máy tính', 'Đại học');

-- ---------------------------------
-- 3. Bảng phụ thuộc cấp 2
-- ---------------------------------

-- Bảng Course (Khóa học) - (phụ thuộc vào Semester)
INSERT INTO Course (Course_ID, Name, Language, Semester_Name) VALUES
                                                                  ('SE101', 'Công nghệ phần mềm', 'Tiếng Việt', 'Học kỳ 1 2024-2025'),
                                                                  ('CS101', 'Cấu trúc dữ liệu & Giải thuật', 'Tiếng Anh', 'Học kỳ 1 2024-2025'),
                                                                  ('DB202', 'Cơ sở dữ liệu nâng cao', 'Tiếng Việt', 'Học kỳ 2 2024-2025');

-- Bảng Question (Câu hỏi) - (phụ thuộc vào Teacher)
INSERT INTO Question (Question_ID, Question_text, Score, Composer_Teacher_ID) VALUES
                                                                           (1, 'Mô hình Agile là gì?', 10,1),
                                                                           (2, 'Sự khác biệt giữa `PRIMARY KEY` và `UNIQUE KEY` trong SQL?', 15,2),
                                                                           (3, 'Hãy mô tả 3 tầng (3-tier architecture) trong phát triển phần mềm.', 20,1),
                                                                           (4, 'Có mấy loại danh sách liên Kết', 10,1),
                                                                           (5, 'Reduce có tối đa bao nhiêu tham số ?', 20,1),
                                                                           (6, 'Đồ thị có hướng có mấy đặc điểm chính ?', 15,1);

-- Bảng Test (Bài kiểm tra) - (phụ thuộc vào Teacher)
INSERT INTO Test (Test_ID, Title, Description, Pass_code, Status, Duration, Questions, Submissions, Creator_Teacher_ID, Released_Answer, Released_Score, test_status) VALUES
                                                                                  (1, 'Kiểm tra Giữa kỳ SE101', 'Kiến thức chương 1-3 về quy trình phát triển PM.', 'pass123', true, 45, 2, 0, 1, true, true, 'IN_PROGRESS'),
                                                                                  (2, 'Kiểm tra 15 phút CS101', 'Kiến thức về danh sách liên kết.', 'cs101test', true, 45, 2, 0, 2, true, true, 'IN_PROGRESS'),
                                                                                  (3, 'Kiểm tra 30 phút DSA', 'Kiến thức về đồ thị.', 'dsa111', true, 30, 10, 0, 2, true, true, 'IN_PROGRESS'),
                                                                                  (4, 'Kiểm tra 15 phút PPL', 'Kiến thức về Reduce.', 'ppl111', true, 45, 10, 0, 2, true, true, 'IN_PROGRESS');

-- ---------------------------------
-- 4. Bảng phụ thuộc cấp 3 (Thực thể yếu & Phụ thuộc Câu hỏi)
-- ---------------------------------

-- Bảng Class (Lớp học) - (phụ thuộc vào Course)
INSERT INTO Class (Course_ID, `Group`) VALUES
                                           ('SE101', 'G1'),
                                           ('SE101', 'G2'),
                                           ('CS101', 'G1');

-- Bảng Answer (Câu trả lời) - (phụ thuộc vào Question)
INSERT INTO Answer (Answer_ID, Answer_text, Correct_answer, Question_ID) VALUES
-- Đáp án cho Câu 1 (Agile)
(1, 'Một mô hình phát triển tuyến tính, tuần tự.', FALSE, 1),
(2, 'Một tập hợp các phương pháp phát triển lặp đi lặp lại và tăng trưởng.', TRUE, 1),
(3, 'Một mô hình chỉ tập trung vào việc kiểm thử.', FALSE, 1),
-- Đáp án cho Câu 2 (SQL)
(4, 'PRIMARY KEY không cho phép NULL, UNIQUE KEY cho phép một giá trị NULL.', TRUE, 2),
(5, 'Cả hai đều giống hệt nhau.', FALSE, 2),
(6, 'Một bảng chỉ có 1 PRIMARY KEY, nhưng có thể có nhiều UNIQUE KEY.', TRUE, 2),
-- Câu 3 là câu tự luận, không có đáp án trắc nghiệm
(7, 'Đây là câu trả lời mẫu cho câu tự luận.', FALSE, 3),
-- DA câu 4
(8, '1', FALSE, 4),
(9, '2', FALSE, 4),
(10, '3', TRUE, 4),
-- DA câu 5
(11, '1', TRUE, 5),
(12, '2', FALSE, 5),
(13, '3', FALSE, 5),
-- DA câu 6
(14, '1', FALSE, 6),
(15, '2', TRUE, 6),
(16, '3', FALSE, 6);


-- ---------------------------------
-- 5. Bảng liên kết (Mối quan hệ N-N)
-- ---------------------------------

-- Mối quan hệ N-N 'assigned to' giữa User và Role
INSERT INTO User_Role (User_ID, Role_ID) VALUES
                                             (1, 2), -- User 1 (GV An) là Teacher
                                             (2, 2), -- User 2 (GV Bình) là Teacher
                                             (3, 3), -- User 3 (SV Cường) là Student
                                             (4, 3), -- User 4 (SV Dung) là Student
                                             (5, 1); -- User 5 (QTV) là Admin

-- Mối quan hệ N-N 'teach' giữa Teacher và Class
INSERT INTO Teaches (Teacher_User_ID, Course_ID, `Group`) VALUES
                                                              (1, 'SE101', 'G1'), -- GV An dạy SE101 G1
                                                              (1, 'SE101', 'G2'), -- GV An dạy SE101 G2
                                                              (2, 'CS101', 'G1'); -- GV Bình dạy CS101 G1

-- Mối quan hệ N-N 'join' giữa Student và Class
INSERT INTO Joins (Student_User_ID, Course_ID, `Group`) VALUES
                                                            (3, 'SE101', 'G1'), -- SV Cường tham gia SE101 G1
                                                            (4, 'SE101', 'G1'), -- SV Dung tham gia SE101 G1
                                                            (3, 'CS101', 'G1'); -- SV Cường tham gia CS101 G1

-- Mối quan hệ N-N 'conduct' giữa Class và Test
INSERT INTO Class_Test (Course_ID, `Group`, Test_ID, Deadline, Create_date, Time, Number_question) VALUES
                                                                                                       ('SE101', 'G1', 1, '2024-10-30 23:59:00', '2024-10-15', 45, 15),
                                                                                                       ('CS101', 'G1', 2, '2024-10-25 17:00:00', '2024-10-20', 15, 5);

-- Mối quan hệ N-N 'include' giữa Test và Question
INSERT INTO Test_Question (Test_ID, Question_ID) VALUES
                                                     (1, 1), -- Test 1 có câu hỏi 1 (Agile)
                                                     (1, 3), -- Test 1 có câu hỏi 3 (3-tier)
                                                     (2, 2), -- Test 2 có câu hỏi 2 (SQL)
                                                 (3,1),
                                                 (3,2),
                                                 (3,3),
                                                 (3,4),
                                                 (3,5),
                                                 (4,2),
                                                 (4,3),
                                                 (4,4),
                                                 (4,5),
                                                 (4,6);

-- Mối quan hệ N-N 'Takes' giữa Student và Test
INSERT INTO Student_Test_Attempt (Student_User_ID, Test_ID, Start_time, Submit_time) VALUES
-- SV Cường làm Test 1 (SE101)
(3, 1, '2024-10-28 09:00:15', '2024-10-28 09:42:30'),
-- SV Dung làm Test 1 (SE101)
(4, 1, '2024-10-29 10:00:00', '2024-10-29 10:30:10'),
-- SV Cường làm Test 2 (CS101)
(3, 2, '2024-10-25 08:00:05', '2024-10-25 08:14:55'),

(3, 3, '2024-10-25 08:00:05', '2024-10-25 08:14:55'),

(4, 4, '2024-10-25 08:00:05', '2024-10-25 08:14:55');


-- Mối quan hệ N-N 'select' giữa Student và Answer
-- (Lưu ý: Thiết kế bảng này trong schema.sql hơi lạ
-- vì nó không liên kết trực tiếp với Test_ID hay Question_ID,
-- chỉ liên kết với Student và Answer)
INSERT INTO Student_Answer_Log (Student_User_ID, Question_ID, Selected_Answer_ID, Student_answer_text, Test_ID) VALUES
-- Giả sử SV Cường (3) trả lời Test 1:
-- Trả lời Câu 1 (Agile) -> chọn đáp án 2 (Đúng)
(3, 1 ,2, NULL, 1),
-- Trả lời Câu 3 (3-tier) -> đây là câu tự luận
(3, 3 ,7, 'Câu trả lời tự luận của Cường về 3 tầng: Presentation, Logic, Data.', 1),

-- Giả sử SV Dung (4) trả lời Test 1:
-- Trả lời Câu 1 (Agile) -> chọn đáp án 1 (Sai)
(4, 1 ,1, NULL, 1),
-- Trả lời Câu 3 (3-tier) -> đây là câu tự luận
(4, 3 ,7, 'Em không chắc lắm nhưng em nghĩ là...', 1),

-- Giả sử SV Cường (3) trả lời Test 2:
-- Trả lời Câu 2 (SQL) -> chọn đáp án 4 và 6 (chọn nhiều đáp án)
(3,2 ,4, NULL, 2),

-- std 3 - test 3 : 1-5
(3,1,1, NULL, 3),
(3,2,6, NULL, 3),
(3,3,7, NULL, 3),
(3,4,10, NULL, 3),
(3,5,11, NULL, 3),
-- std 4 - test 4 : 2-6
(4,2,5, NULL, 4),
(4,3,7, NULL, 4),
(4,4,10, NULL, 4),
(4,5,11, NULL, 4),
(4,6,15, NULL, 4);
