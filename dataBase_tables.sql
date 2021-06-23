create table person (
	firstName varchar(25) not null,
	lastName varchar(25) not null,
	address varchar(250) not null,
	city varchar(25) not null,
	zip varchar(4) not null,
	phone varchar(12) not null,
	email varchar(100) not null,
	unique(firstName, lastName)
	);

create table firestation (
	address varchar(250) not null,
	station varchar(7) not null,
	unique(address, station)
    );
	
create table medicalrecord (
    firstName varchar(25) not null,
	lastName varchar(25) not null,
	birthdate date not null,
	medications varchar(50)[] not null,
	allergies varchar(50)[] not null,
	unique(firstName, lastName)
	);