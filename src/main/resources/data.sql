INSERT INTO users (username, password, enabled, email)
VALUES
    ('user', '$2y$10$Asnk2HVhhHTwlOur0beC8.SQSHd.zOCKidUPIJYC4xSKy/CAxToQy', TRUE, 'dummy@novi.nl'),
    ('Jasper', '$2y$10$Asnk2HVhhHTwlOur0beC8.SQSHd.zOCKidUPIJYC4xSKy/CAxToQy', TRUE, 'dummy@novi.nl');

INSERT INTO authorities (username, authority)
VALUES
    ('user', 'ROLE_USER'),
    ('Jasper', 'ROLE_USER'),
    ('Jasper', 'ROLE_ADMIN');

