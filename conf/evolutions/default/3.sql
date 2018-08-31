
# --- !Ups

create table accounts (
  id                            bigint auto_increment not null,
  version                       bigint not null,
  deleted                       tinyint(1) default 0 not null,
  constraint pk_users primary key (id)
);

INSERT INTO accounts (id,deleted,version) VALUES(1,0,1);
INSERT INTO accounts (id,deleted,version) VALUES(2,0,1);
INSERT INTO accounts (id,deleted,version) VALUES(3,0,1);
INSERT INTO accounts (id,deleted,version) VALUES(4,0,1);
INSERT INTO accounts (id,deleted,version) VALUES(5,0,1);


# --- !Downs