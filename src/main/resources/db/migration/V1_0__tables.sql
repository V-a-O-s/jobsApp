

CREATE TABLE IF NOT EXISTS Company (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    logo_url VARCHAR(255),
    address VARCHAR(255),
    plz VARCHAR(7),
    ort VARCHAR(70),
    website VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Extras (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    remote BOOLEAN DEFAULT false,
    flexibel BOOLEAN DEFAULT false,
    sign_up BOOLEAN DEFAULT false,
    devices BOOLEAN DEFAULT false,
    extraPay BOOLEAN DEFAULT false,
    sonstiges TEXT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Jobs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    company_id BIGINT NOT NULL,
    anzahl INT DEFAULT 1,
    status VARCHAR(12), --ENUM('Praktikum', 'Lehrstelle', 'Job'),
    pensum VARCHAR(3), --ENUM('20', '40', '60', '80', '100'),
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);