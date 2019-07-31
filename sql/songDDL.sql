drop table song cascade constraint;

drop table singer cascade constraint;

drop table users cascade constraint;

create table song(
	song_id number(5) constraint song_id_pk primary key,
	name varchar2(50) not null,
--나중에 레퍼런스로 바꿔야한다 FK
	singer varchar2(50) not null,
	release_date varchar2(10) not null,
	lyrics varchar2(50) not null
);

create table singer(
	singer_id number(5) constraint singer_id_pk primary key,
	name varchar2(50) not null
);

create table users(
	user_id number(5) constraint user_id_pk primary key,
	name varchar2(20) not null
);