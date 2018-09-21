# --- !Ups


CREATE TABLE users (
  id               BIGINT AUTO_INCREMENT NOT NULL,
  version          BIGINT                NOT NULL,
  deleted          BIT(1) DEFAULT 0      NOT NULL,
  sys_period_start DATETIME DEFAULT now(),
  sys_period_end   DATETIME,
  CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE settings (
  id               BIGINT AUTO_INCREMENT NOT NULL,
  userid           BIGINT,
  version          BIGINT                NOT NULL,
  deleted          BIT(1) DEFAULT 0      NOT NULL,
  sys_period_start DATETIME DEFAULT now(),
  sys_period_end   DATETIME,
  CONSTRAINT uq_settings_userId UNIQUE (userid),
  CONSTRAINT pk_settings PRIMARY KEY (id),
  CONSTRAINT fk_settings_userId FOREIGN KEY (userid) REFERENCES users (id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
);

CREATE TABLE settings_history (
  id               BIGINT,
  userid           BIGINT,
  version          BIGINT,
  deleted          BIT(1),
  sys_period_start DATETIME,
  sys_period_end   DATETIME
);

CREATE VIEW settings_with_history AS SELECT *
                                     FROM settings
                                     UNION ALL SELECT *
                                               FROM settings_history;

CREATE TABLE users_history (
  id               BIGINT,
  version          BIGINT,
  deleted          BIT(1),
  sys_period_start DATETIME,
  sys_period_end   DATETIME
);

CREATE VIEW users_with_history AS SELECT *
                                  FROM users
                                  UNION ALL SELECT *
                                            FROM users_history;

CREATE TABLE accounts (
  id      BIGINT AUTO_INCREMENT NOT NULL,
  version BIGINT                NOT NULL,
  deleted TINYINT(1) DEFAULT 0  NOT NULL,
  CONSTRAINT pk_accounts PRIMARY KEY (id)
);

INSERT INTO users (id, deleted, version) VALUES (1, 0, 1);
INSERT INTO users (id, deleted, version) VALUES (2, 0, 1);
INSERT INTO users (id, deleted, version) VALUES (3, 0, 1);
INSERT INTO users (id, deleted, version) VALUES (4, 0, 1);
INSERT INTO users (id, deleted, version) VALUES (5, 0, 1);

INSERT INTO settings (id, userid, deleted, version) VALUES (1, 1, 0, 1);
INSERT INTO settings (id, userid, deleted, version) VALUES (2, 2, 0, 1);
INSERT INTO settings (id, userid, deleted, version) VALUES (3, 3, 0, 1);
INSERT INTO settings (id, userid, deleted, version) VALUES (4, 4, 0, 1);
INSERT INTO settings (id, userid, deleted, version) VALUES (5, 5, 0, 1);

INSERT INTO accounts (id, deleted, version) VALUES (1, 0, 1);
INSERT INTO accounts (id, deleted, version) VALUES (2, 0, 1);
INSERT INTO accounts (id, deleted, version) VALUES (3, 0, 1);
INSERT INTO accounts (id, deleted, version) VALUES (4, 0, 1);
INSERT INTO accounts (id, deleted, version) VALUES (5, 0, 1);

# --- !Downs

DROP VIEW settings_with_history;
DROP VIEW users_with_history;
DROP TABLE IF EXISTS settings_history;
DROP TABLE IF EXISTS settings;
DROP TABLE IF EXISTS users_history;
DROP TABLE IF EXISTS users;
