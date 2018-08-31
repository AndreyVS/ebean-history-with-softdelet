
# --- !Ups

create table settings (
  id                            bigint auto_increment not null,
  userid                        bigint,
  version                       bigint not null,
  deleted                       bit(1) default 0 not null,
  constraint uq_settings_userid unique (userid),
  constraint pk_settings primary key (id)
);

create table users (
  id                            bigint auto_increment not null,
  version                       bigint not null,
  deleted                       bit(1) default 0 not null,
  constraint pk_users primary key (id)
);

alter table settings add constraint fk_settings_userid foreign key (userid) references users (id) on delete restrict on update restrict;

alter table settings add column sys_period_start datetime(6) default now(6);
alter table settings add column sys_period_end datetime(6);
create table settings_history(
  id                            bigint,
  userid                        bigint,
  version                       bigint,
  deleted                       bit(1),
  sys_period_start              datetime(6),
  sys_period_end                datetime(6)
);
create view settings_with_history as select * from settings union all select * from settings_history;

create trigger settings_history_upd before update on settings for each row begin
    insert into settings_history (sys_period_start,sys_period_end,id, userId, version, deleted) values (OLD.sys_period_start, now(6),OLD.id, OLD.userId, OLD.version, OLD.deleted);;
    set NEW.sys_period_start = now(6);;
end;

create trigger settings_history_del before delete on settings for each row begin
    insert into settings_history (sys_period_start,sys_period_end,id, userId, version, deleted) values (OLD.sys_period_start, now(6),OLD.id, OLD.userId, OLD.version, OLD.deleted);;
end;
alter table users add column sys_period_start datetime(6) default now(6);
alter table users add column sys_period_end datetime(6);
create table users_history(
  id                            bigint,
  version                       bigint,
  deleted                       bit(1),
  sys_period_start              datetime(6),
  sys_period_end                datetime(6)
);
create view users_with_history as select * from users union all select * from users_history;


create trigger users_history_upd before update on users for each row begin
    insert into users_history (sys_period_start,sys_period_end,id, version, deleted) values (OLD.sys_period_start, now(6),OLD.id, OLD.version, OLD.deleted);;
    set NEW.sys_period_start = now(6);;
end;

create trigger users_history_del before delete on users for each row begin
    insert into users_history (sys_period_start,sys_period_end,id, version, deleted) values (OLD.sys_period_start, now(6),OLD.id, OLD.version, OLD.deleted);;
end;

# --- !Downs

alter table settings drop foreign key fk_settings_userid;

drop trigger settings_history_upd;
drop trigger settings_history_del;
drop view settings_with_history;
alter table settings drop column sys_period_start;
alter table settings drop column sys_period_end;
drop table settings_history;

drop table if exists settings;

drop trigger users_history_upd;
drop trigger users_history_del;
drop view users_with_history;
alter table users drop column sys_period_start;
alter table users drop column sys_period_end;
drop table users_history;

drop table if exists users;

