CREATE TABLE user
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
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
);

CREATE TABLE user_tokens
(
    id            INT AUTO_INCREMENT PRIMARY KEY ,
    user_id      INT          NOT NULL ,
    access_token  VARCHAR(500) NOT NULL ,
    refresh_token VARCHAR(500) NOT NULL ,
    expires_at    DATETIME     NOT NULL ,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP ,
    updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    FOREIGN KEY (user_id) REFERENCES user (id)
);