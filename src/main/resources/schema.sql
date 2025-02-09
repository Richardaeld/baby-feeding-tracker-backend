CREATE TABLE IF NOT EXISTS USERS (
  user_id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  phone VARCHAR(20) NULL UNIQUE,
  PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS BABY (
  baby_id INT NOT NULL AUTO_INCREMENT,
  birthday DATE NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  gender ENUM('male', 'female') NOT NULL,
  PRIMARY KEY (baby_id)
);

CREATE TABLE IF NOT EXISTS BABY_CARE_GIVER (
  baby_id INT NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (baby_id, user_id),
  FOREIGN KEY (baby_id) REFERENCES BABY(baby_id),
  FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

CREATE TABLE IF NOT EXISTS ALLERGY (
  allergy_id INT NOT NULL AUTO_INCREMENT,
  baby_id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NULL,
  PRIMARY KEY (allergy_id),
  FOREIGN KEY (baby_id) REFERENCES BABY(baby_id)
);

CREATE TABLE IF NOT EXISTS NOTE (
  note_id INT NOT NULL AUTO_INCREMENT,
  note TEXT NOT NULL,
  PRIMARY KEY (note_id)
);

CREATE TABLE IF NOT EXISTS EVENT (
  event_id INT NOT NULL AUTO_INCREMENT,
  baby_id INT NOT NULL,
  event_type ENUM('BATH', 'DIAPER', 'FEEDING', 'GROWTH', 'MEDICATION', 'NIGHT_CHECK', 'PUMPING', 'TEMPERATURE', 'TUMMY_TIME') NOT NULL,
  start_on DATETIME NOT NULL,
  end_on DATETIME NULL,
   PRIMARY KEY (event_id)
);


CREATE TABLE IF NOT EXISTS BATH_PRODUCTS (
    bath_products_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NULL,
    PRIMARY KEY (bath_products_id)
);
CREATE TABLE IF NOT EXISTS BATH_PRODUCTS_REFERENCE (
    event_id INT NOT NULL,
    bath_product_id INT NOT NULL,
    PRIMARY KEY (event_id, bath_product_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (bath_product_id) REFERENCES BATH_PRODUCTS(bath_products_id)
);
CREATE TABLE IF NOT EXISTS BATH (
    event_id INT NOT NULL,
    note_id INT NULL,
    post_care ENUM('OIL', 'LOTION') NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);

CREATE TABLE IF NOT EXISTS DIAPER_TYPE (
    diaper_type_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NULL,
    PRIMARY KEY (diaper_type_id)
);
CREATE TABLE IF NOT EXISTS DIAPER_TYPE_REFERENCE (
    event_id INT NOT NULL,
    diaper_type_id INT NOT NULL,
    PRIMARY KEY (event_id, diaper_type_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (diaper_type_id) REFERENCES DIAPER_TYPE(diaper_type_id)
);
CREATE TABLE IF NOT EXISTS DIAPER (
    event_id INT NOT NULL,
    note_id INT NULL,
    type ENUM('PEE', 'POOP', 'BLOWOUT') NOT NULL,
    color VARCHAR(50) NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);

CREATE TABLE IF NOT EXISTS FOOD (
    food_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NULL,
    PRIMARY KEY (food_id)
);
CREATE TABLE IF NOT EXISTS FEEDING_FOOD_REFERENCE (
    event_id INT NOT NULL,
    food_id INT NOT NULL,
    PRIMARY KEY (event_id, food_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (food_id) REFERENCES FOOD(food_id)
);
CREATE TABLE IF NOT EXISTS FEEDING (
    event_id INT NOT NULL,
    note_id INT NULL,
    type ENUM('BREAST', 'FORMULA', 'SOLID') NOT NULL,
    amount VARCHAR(50) NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);

CREATE TABLE IF NOT EXISTS GROWTH (
    event_id INT NOT NULL,
    note_id INT NULL,
    height DECIMAL(5,2) NULL,
    weight DECIMAL(5,2) NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);

CREATE TABLE IF NOT EXISTS MEDICATION (
    event_id INT NOT NULL,
    note_id INT NULL,
    name VARCHAR(50) NOT NULL,
    dosage VARCHAR(50) NOT NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);

CREATE TABLE IF NOT EXISTS NIGHT_CHECK (
    event_id INT NOT NULL,
    note_id INT NULL,
    temperature DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);

CREATE TABLE IF NOT EXISTS PUMPING (
    event_id INT NOT NULL,
    note_id INT NULL,
    amount DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);

CREATE TABLE IF NOT EXISTS TEMPERATURE (
    event_id INT NOT NULL,
    note_id INT NULL,
    temperature DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);

CREATE TABLE IF NOT EXISTS TUMMY_TIME (
    event_id INT NOT NULL,
    note_id INT NULL,
    PRIMARY KEY (event_id),
    FOREIGN KEY (event_id) REFERENCES EVENT(event_id),
    FOREIGN KEY (note_id) REFERENCES NOTE(note_id)
);


