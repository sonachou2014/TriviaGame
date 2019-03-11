    CREATE TABLE USERS(
      ID BIGINT AUTO_INCREMENT PRIMARY KEY,
      NAME VARCHAR(64),
      EMAIL VARCHAR(64),
      PASSWORD VARCHAR(64),
      IS_ONLINE BIT,
    );

    CREATE TABLE SCORE(
      ID BIGINT AUTO_INCREMENT PRIMARY KEY,
      user_id Bigint,
      current_score INT,
      total_score INT,
      FOREIGN KEY(user_id) REFERENCES Users(id),
    );
