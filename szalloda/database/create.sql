CREATE TABLE szobak (
    id INT PRIMARY KEY AUTO_INCREMENT,
    szoba_merete INT,
    agyak_szama INT,
    terasz INT,
    haziallat INT
);

CREATE TABLE foglalok (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(32),
    szemelyek INT,
    foglalas_kezdete DATE,
    foglalas_vege DATE,
    szoba_id INT,
    FOREIGN KEY (szoba_id) REFERENCES szobak(id)
);



-- insert into foglalok(id, name, szemelyek,foglalas_kezdete, foglalas_vege, szoba_id) values
-- (
--     1, "Kiss Ferenc", 3, '2024-01-16', '2024-01-23', 1
-- ),
-- (
--     2, "David Janos", 2 ,'2024-01-24', '2024-01-30', 1
-- );
-- insert into szobak(id, szoba_merete, agyak_szama, terasz, haziallat) values
-- (
--     1, 60, 4, 0, 1
-- ),
-- (
--     2, 55, 2, 1, 1
-- );