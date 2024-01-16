--------------------------------------------------------------
--                  INSERT PROFILES                         --
--------------------------------------------------------------
INSERT INTO profile --passwd = 123
(id,  visibility, first_name, last_name, phone, password, created_date, status)
SELECT
    '550e8400-e29b-41d4-a716-446655440000',  true, 'Ali', 'Aliyev', '123', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', now(), 'ACTIVE'
WHERE NOT EXISTS(SELECT id
                 FROM profile
                 WHERE id = '550e8400-e29b-41d4-a716-446655440000');


--------------------------------------------------------------
--                   INSERT PROFILE ROLES                   --
--------------------------------------------------------------
INSERT INTO profile_role
(visibility, id, created_date, profile_id, profile_role)
SELECT
    true, '550e8400-e29b-41d4-a716-446655440001',now(), '550e8400-e29b-41d4-a716-446655440000', 'SUPER_ADMIN'
WHERE NOT EXISTS(SELECT
                 FROM profile_role
                 WHERE id = '550e8400-e29b-41d4-a716-446655440001' );

INSERT INTO profile_role
(visibility, id, created_date, profile_id, profile_role)
SELECT
    true, '550e8400-e29b-41d4-a716-446655440002',now(), '550e8400-e29b-41d4-a716-446655440000', 'USER'
WHERE NOT EXISTS(SELECT
                 FROM profile_role
                 WHERE id = '550e8400-e29b-41d4-a716-446655440002');

INSERT INTO profile_role
(visibility, id, created_date, profile_id, profile_role)
SELECT
    true, '550e8400-e29b-41d4-a716-446655440003',now(), '550e8400-e29b-41d4-a716-446655440000', 'STAFF'
WHERE NOT EXISTS(SELECT
                 FROM profile_role
                 WHERE id = '550e8400-e29b-41d4-a716-446655440003');

INSERT INTO profile_role
(visibility, id, created_date, profile_id, profile_role)
SELECT
    true, '550e8400-e29b-41d4-a716-446655440004',now(), '550e8400-e29b-41d4-a716-446655440000', 'CHEF'
WHERE NOT EXISTS(SELECT
                 FROM profile_role
                 WHERE id = '550e8400-e29b-41d4-a716-446655440004');

INSERT INTO profile_role
(visibility, id, created_date, profile_id, profile_role)
SELECT
    true, '550e8400-e29b-41d4-a716-446655440005',now(), '550e8400-e29b-41d4-a716-446655440000', 'ADMINISTRATOR'
WHERE NOT EXISTS(SELECT id
                 FROM profile_role
                 WHERE id = '550e8400-e29b-41d4-a716-446655440005');