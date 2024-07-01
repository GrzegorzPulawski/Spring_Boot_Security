INSERT INTO users (id, user_name, password,is_Account_Non_Expired,is_Account_Non_Locked, is_Credentials_Non_Expired, is_Enabled)
VALUES
    (1, 'student', '123456', 1, 1, 1, 1),
    (2, 'old', '123456', 1, 1, 1, 1),
    (3, 'admin', '123456', 1, 1, 1, 1),
    (4, 'verbs', '123456', 1, 1, 1, 1);


INSERT INTO authorities(id, authority)
VALUES
    (1, 'STUDENT'),
    (2, 'OLD_STUDENT'),
    (3, 'HTTP_VERBS'),
    (4, 'ADMIN');

INSERT INTO users_authorities(user_id, authority_id)
VALUES
    (1,1),
    (2,3),
    (3,4),
    (4,3);
