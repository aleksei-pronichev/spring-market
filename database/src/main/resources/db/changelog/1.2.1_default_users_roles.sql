INSERT INTO USERS_ROLES(USER_ID, ROLE_ID)
SELECT (
           SELECT user_id
           FROM USERS
           WHERE username = 'admin'
       ),
       (
           SELECT role_id
           FROM ROLES
           WHERE name = 'ROLE_ADMIN'
       )
UNION ALL
SELECT (
           SELECT user_id
           FROM USERS
           WHERE username = 'guest'
       ),
       (
           SELECT role_id
           FROM ROLES
           WHERE name = 'ROLE_GUEST'
       );