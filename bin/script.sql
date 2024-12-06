CREATE TYPE user_type AS ENUM ('Etudiant', 'prefosseur');

CREATE TABLE utilisateurs (
    id_user SERIAL PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(100),
    type user_type 
);

CREATE TABLE evenements (
    id_event SERIAL PRIMARY KEY,
    nom_event VARCHAR(100),
    date_event DATE,
    description TEXT,
    id_user INT REFERENCES utilisateurs(id_user) 
);


CREATE TABLE salles (
    id_salle SERIAL PRIMARY KEY,
    nom_salle VARCHAR(100),
    capacite INT
);

CREATE TABLE terrains (
    id_terrain SERIAL PRIMARY KEY,
    nom_terrain VARCHAR(100),
    type VARCHAR(50) 
);

CREATE TABLE reservations (
    id_reservation SERIAL PRIMARY KEY,
    id_user INT REFERENCES utilisateurs(id_user),
    id_event INT evenements(id_event),
    id_salle INT REFERENCES salles(id_salle),
    id_terrain INT REFERENCES terrains(id_terrain),
    date_reservation DATE 
);
