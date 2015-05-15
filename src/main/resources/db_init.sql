CREATE DATABASE IF NOT EXISTS dota2info;

USE dota2info;

/*MATCHES*/

CREATE TABLE matchID(
  match_seq_num BIGINT PRIMARY KEY,
  match_id BIGINT,
  isParsed BOOLEAN DEFAULT FALSE
);

CREATE TABLE lobbyTypes(
  id TINYINT PRIMARY KEY,
  name VARCHAR(64)
);

CREATE TABLE gameModes(
  id TINYINT PRIMARY KEY,
  name VARCHAR(64)
);

CREATE TABLE leagues(
  id BIGINT PRIMARY KEY,
  name VARCHAR(128),
  description VARCHAR(256),
  tournament_url VARCHAR(256)
);

CREATE TABLE matches(
  match_id BIGINT UNIQUE PRIMARY KEY,
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
  id_lobby_type TINYINT,
  game_mode TINYINT,
  leagueid BIGINT,
  FOREIGN KEY (id_lobby_type) REFERENCES lobbyTypes(id),
  FOREIGN KEY (game_mode) REFERENCES gameModes(id),
  FOREIGN KEY (leagueid) REFERENCES leagues(id)
);

/*PLAYER*/

CREATE TABLE heroes(
  id INT PRIMARY KEY,
  name VARCHAR(64),
  localized_name VARCHAR(64),
  small_img VARCHAR(128),
  large_img VARCHAR(128),
  full_vertical_img VARCHAR(128),
  full_horizontal_img VARCHAR(128)
);

CREATE TABLE items(
  id INT PRIMARY KEY,
  name VARCHAR(128),
  img VARCHAR(256)
);

CREATE TABLE playerSlots (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  account_id BIGINT,
  player_slot INT,
  match_id BIGINT,
  hero INT,
  kills INT,
  deaths INT,
  assists INT,
  leaver_status INT,
  gold INT,
  last_hits INT,
  denies INT,
  xp_per_min INT,
  gold_per_min INT,
  gold_spent INT,
  hero_damage INT,
  tower_damage INT,
  hero_healing INT,
  level INT,
  item_0 INT,
  item_1 INT,
  item_2 INT,
  item_3 INT,
  item_4 INT,
  item_5 INT
);
