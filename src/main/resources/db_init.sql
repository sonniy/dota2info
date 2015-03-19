CREATE DATABASE IF NOT EXISTS dota2info;

USE dota2info;

CREATE TABLE matchID(
  match_seq_num BIGINT PRIMARY KEY,
  match_id BIGINT,
  isParsed BOOLEAN DEFAULT FALSE
);

CREATE TABLE lobbyTypes(
  id TINYINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45)
);

CREATE TABLE gameModes(
  id TINYINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45)
);

CREATE TABLE leagues(
  id BIGINT PRIMARY KEY,
  name VARCHAR(128),
  description VARCHAR(256),
  tournament_url VARCHAR(256)
);

CREATE TABLE matches(
  match_id BIGINT UNIQUE PRIMARY KEY,
  season VARCHAR(45),
  radiant_win BOOLEAN,
  duration BIGINT,
  start_time BIGINT,
  tower_status_radiant INT,
  tower_status_dire INT,
  barracks_status_radiant INT,
  barracks_status_dire INT,
  cluster INT,
  first_blood_time INT,
  human_players INT,
  positive_votes INT,
  negative_votes INT,
  picks_bans VARCHAR(45),
  id_lobby_type TINYINT,
  game_mode TINYINT,
  leagueid BIGINT,
  FOREIGN KEY (id_lobby_type) REFERENCES lobbyTypes(id),
  FOREIGN KEY (game_mode) REFERENCES gameModes(id),
  FOREIGN KEY (leagueid) REFERENCES leagues(id)
);