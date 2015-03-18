
CREATE DATABASE IF NOT EXISTS dota2info;

CREATE TABLE matchID(
  match_seq_num BIGINT PRIMARY KEY,
  match_id BIGINT,
  isParsed BOOLEAN DEFAULT FALSE
);

CREATE TABLE matches(
  match_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  match_seq_num BIGINT,
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
  leagueid INT
);