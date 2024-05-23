USE AdministrationOfTheCarPark;
INSERT INTO users (name, username, password, role) VALUES
('Ion Popescu', 'ionpopescu', '$2a$10$abcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdef', 'ROLE_USER'),
('Maria Ionescu', 'mariaionescu', '$2a$10$123456789012345678901234567890123456789012345678901234', 'ROLE_EDITOR');

INSERT INTO cars (registration_number, user_id, brand, model, color, year_of_fabrication, engine_capacity, fuel_type, power, torque, trunk_volume, price) VALUES
('B-100-XYZ', 1, 'Ford', 'Focus', 'Albastru', 2015, 1600, 'benzina', 125, 240, 480, 9500.00),
('B-200-ZZZ', 2, 'Volkswagen', 'Golf', 'Gri', 2017, 2000, 'diesel', 150, 320, 390, 15000.00);
