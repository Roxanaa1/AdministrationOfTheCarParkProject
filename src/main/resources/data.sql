
CREATE TABLE utilizatori (
    id_utilizator INT AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(255) NOT NULL,
    utilizator VARCHAR(255) NOT NULL UNIQUE,
    parola VARCHAR(255) NOT NULL,
    rol ENUM('ROLE_USER', 'ROLE_EDITOR') NOT NULL
);

CREATE TABLE masini (
    nr_inmatriculare VARCHAR(255) PRIMARY KEY,
    id_utilizator INT NOT NULL,
    marca VARCHAR(255) NOT NULL,
    modelul VARCHAR(255) NOT NULL,
    cularea VARCHAR(255),
    anul_fabricatiei INT,
    capacitatea_cilindrica INT,
    tipul_de_combustibil ENUM('benzina', 'diesel', 'electric', 'hibrid') NOT NULL,
    puterea INT,
    cuplul INT,
    volumul_portbagajului INT,
    pretul DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_utilizator) REFERENCES utilizatori(id_utilizator)
);

INSERT INTO utilizatori (nume, utilizator, parola, rol) VALUES
('Ion Popescu', 'ionpopescu', 'parola_criptata_aici', 'ROLE_USER'),
('Maria Ionescu', 'mariaionescu', 'parola_criptata_aici', 'ROLE_EDITOR');

INSERT INTO masini (nr_inmatriculare, id_utilizator, marca, modelul, cularea, anul_fabricatiei, capacitatea_cilindrica, tipul_de_combustibil, puterea, cuplul, volumul_portbagajului, pretul) VALUES
('B-100-XYZ', 1, 'Ford', 'Focus', 'Albastru', 2015, 1600, 'benzina', 125, 240, 480, 9500.00),
('B-200-ZZZ', 2, 'Volkswagen', 'Golf', 'Gri', 2017, 2000, 'diesel', 150, 320, 390, 15000.00);
