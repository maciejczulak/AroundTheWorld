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

create table trips(
                      id INTEGER PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR,
                      start_city_id INTEGER,
                      start_airport_id INTEGER,
                      dest_city_id INTEGER,
                      dest_airport_id  INTEGER,
                      dest_hotel_id INTEGER,
                      start_date DATE,
                      end_date DATE,
                      trip_lenght LONG,
                      price_for_one DOUBLE,
                      for_how_many INTEGER,
                      promoted BOOLEAN
);
alter table trips add foreign key (start_city_id) references cities (id);
alter table trips add foreign key (dest_city_id) references cities (id);
alter table trips add foreign key (start_airport_id) references airports (id);
alter table trips add foreign key (dest_airport_id) references airports (id);
alter table trips add foreign key (dest_hotel_id) references hotels (id);

create table users(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    role VARCHAR(30) NOT NULL
);



