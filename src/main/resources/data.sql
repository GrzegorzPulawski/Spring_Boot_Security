INSERT INTO users (id, user_name, password,is_Account_Non_Expired,is_Account_Non_Locked, is_Credentials_Non_Expired, is_Enabled)
VALUES
    (1, 'student', '$2a$10$W1aW0lT8LUJAReMZ9vWh8usJt1IZ09sl0A4fueTno7JQ4G6bR8kgK', 1, 1, 1, 1),
    (2, 'old', '$2a$10$seOy6zPeGKGTs0TMgtvo9ujWysNYmS.j9XzrW0NkthsZ82O1CBg8S', 1, 1, 1, 1),
    (3, 'admin', '$2a$10$/xjGlwQ1wITKPZtPDhFRp.0DJKO8U/X7/Ial4179LBEGIaz94/fb2', 1, 1, 1, 1),
    (4, 'verbs', '$2a$10$ZphhVNFvksmUMIxTQyoon.FTzpohTjtrTf74Rsnz46Gm.P6oBmiYS', 1, 1, 1, 1);


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
