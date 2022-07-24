INSERT INTO lingxi.user (id, created_at, created_by, updated_at, updated_by, avatar, nick_name, password, username)
VALUES (1, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '/avatar.png', '灵希', '8b12aedc03901c59ba19a5a117e56936', 'lingxi');
INSERT INTO lingxi.role(id, created_at, created_by, updated_at, updated_by, code, name)
VALUES (1, DEFAULT, DEFAULT, DEFAULT, DEFAULT, 'DEFAULT_ROLE', '默认角色');
INSERT INTO lingxi.user_role_relation(user_id, role_id) VALUES (1, 1);