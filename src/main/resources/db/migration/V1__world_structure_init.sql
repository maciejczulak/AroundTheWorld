create table continents(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

create table countries(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    continent_id INTEGER
);
alter table countries add foreign key (continent_id) references continents (id);

create table cities(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    country_id INTEGER
);
alter table cities add foreign key (country_id) references countries (id);

create table airports(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    city_id INTEGER
);
alter table airports add foreign key (city_id) references cities (id);

create table hotels(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    stars INTEGER,
    description VARCHAR(300),
    city_id INTEGER
);
alter table hotels add foreign key (city_id) references cities (id);
