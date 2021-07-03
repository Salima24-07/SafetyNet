create table person (
	first_name varchar(25) not null,
	last_name varchar(25) not null,
	address varchar(250) not null,
	city varchar(25) not null,
	zip varchar(5) not null,
	phone varchar(12) not null,
	email varchar(100) not null,
	unique(first_name, last_name)
	);

create table firestation (
	address varchar(250) not null,
	station varchar(7) not null,
	unique(address, station)
    );
	
create table medicalrecord (
    first_name varchar(25) not null,
	last_name varchar(25) not null,
	birthdate date not null,
	medications varchar(50)[] not null,
	allergies varchar(50)[] not null,
	unique(first_name, last_name)
	);