INSERT INTO televisions (id, type, brand, name, price, availablesize, refreshrate, screentype, screenquality,
smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (1, 'NH3216SMART', 'Nikkei', 'HD smart TV', 159.00, 32.00, 100.00, 'LED', 'HD ready', true, true, false, false,
false, false, 10, 2),
(2, '43PUS6504/12/L', 'Philips', '4K UHD LED Smart Tv', 379.00, 43.00, 60.00, 'LED', 'Ultra HD', true, true,
false, true, false, false, 2, 0),
(3, 'OLED55C16LA', 'LG', 'LG OLED55C16LA', 989.00, 55.00, 100.00, 'OLED', 'ULTRA HD', true, true, true, true,
true, false, 10, 4);

INSERT INTO remote_controllers(compatiblewith, batterytype, brand, name ,price, original_tock, sold)
VALUES ('Philips', 'AAA', 'Philips', 'controller pro',19, 10, 5),
('Nikkei', 'AAA', 'Nikkei', 'controller expert',12, 12, 12),
('LG', 'AAA', 'LG', 'controller basis',10, 22, 10);

INSERT INTO ci_modules (name,type,price)
VALUES ('module one', 'one', 15),
('module two', 'one', 11),
('module three', 'one', 14);

INSERT INTO wall_brackets(size, adjustable, name, price)
VALUES ('10cm', true, 'hangman', 50 ),
('40cm', false , 'klik direct', 30 ),
('24', true, 'hangman', 50 );
