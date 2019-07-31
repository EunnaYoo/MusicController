create table singer(
	singer_id number(5) primary key,
	singer_name varchar2(50) not null
);

create table song(
	song_id number(5) primary key,
	song_name varchar2(50) not null,
	singer_id varchar2(50) not null, -- fk
	release_date varchar2(10) not null
);

create table users(
	user_id number(5) constraint user_id_pk primary key,
	user_name varchar2(20) not null
);

create table song_user_mapping (
	song_id number(5) -- song fk
	user_id number(5) -- user fk
);

create table new_song (
	song_id number(5), -- song fk
	song_name varchar2(50) not null
);

create table popular_chart (
	song_id number(5) not null, -- song fk
	song_name varchar2(50) not null
);

alter table song add foreign key (singer_id) references singer (singer_id);
alter table song_user_mapping add foreign key (song_id) references song (song_id);
alter table song_user_mapping add foreign key (user_id) references users (user_id);
alter table new_song add foreign key (song_id) references song (song_id);
alter table popular_chart add foreign key (song_id) references song (song_id);



