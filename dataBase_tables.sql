create table person (
	id serial primary key not null,
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
	id serial primary key not null,
	address varchar(250) not null,
	station varchar(7) not null,
	unique(address, station)
    );
	
create table medicalrecord (
	id serial primary key not null,
    first_name varchar(25) not null,
	last_name varchar(25) not null,
	birthdate date not null,
	medications varchar(50)[] not null,
	allergies varchar(50)[] not null,
	unique(first_name, last_name)
	);

--person
insert into person (first_name,last_name,address,city,zip,phone,email)
 values
('John', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
('Jacob', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6513', 'drk@email.com'),
('Tenley', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'tenz@email.com'),
('Roger', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
('Felicia', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com'),
('Jonanathan', 'Marrack', '29 15th St', 'Culver', '97451', '841-874-6513', 'drk@email.com'),
('Tessa', 'Carman', '834 Binoc Ave', 'Culver', '97451', '841-874-6512', 'tenz@email.com'),
('Peter', 'Duncan', '644 Gershwin Cir', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
('Foster', 'Shepard', '748 Townings Dr', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com'),
('Tony', 'Cooper', '112 Steppes Pl', 'Culver', '97451', '841-874-6874', 'tcoop@ymail.com'),
('Lily', 'Cooper', '489 Manchester St', 'Culver', '97451', '841-874-9845', 'lily@email.com'),
('Sophia', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7878', 'soph@email.com'),
('Warren', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'ward@email.com'),
('Zach', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'zarc@email.com'),
('Reginold', 'Walker', '908 73rd St', 'Culver', '97451', '841-874-8547', 'reg@email.com'),
('Jamie', 'Peters', '908 73rd St', 'Culver', '97451', '841-874-7462', 'jpeter@email.com'),
('Ron', 'Peters', '112 Steppes Pl', 'Culver', '97451', '841-874-8888', 'jpeter@email.com'),
('Allison', 'Boyd', '112 Steppes Pl', 'Culver', '97451', '841-874-9888', 'aly@imail.com'),
('Brian', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@email.com'),
('Shawna', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'ssanw@email.com'),
('Kendrik', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@email.com'),
('Clive', 'Ferguson', '748 Townings Dr', 'Culver', '97451', '841-874-6741', 'clivfd@ymail.com'),
('Eric', 'Cadigan', '951 LoneTree Rd', 'Culver', '97451', '841-874-7458', 'gramps@email.com');

--firestation
insert into firestation (address,station)
 values
('1509 Culver St', '3'),
('29 15th St', '2'),
('834 Binoc Ave', '3'),
('644 Gershwin Cir', '1'),
('748 Townings Dr', '3'),
('112 Steppes Pl', '3'),
('489 Manchester St', '4'),
('892 Downing Ct', '2'),
('908 73rd St', '1'),
('112 Steppes Pl', '4'),
('947 E. Rose Dr', '1'),
('951 LoneTree Rd', '2');

--medicalrecord
insert into medicalrecord (first_name,last_name,birthdate,medications,allergies)
 values
('John', 'Boyd', '1984-03-06', '{"aznol:350mg", "hydrapermazol:100mg"}', '{"nillacilan"}'),
('Jacob', 'Boyd', '1989-03-06', '{"pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"}', '{}'),
('Tenley', 'Boyd', '2012-02-18', '{}', '{"peanut"}'),
('Roger', 'Boyd', '2017-09-06', '{}', '{}'),
('Felicia', 'Boyd', '1986-01-08', '{"tetracyclaz:650mg"}', '{"xilliathal"}'),
('Jonanathan', 'Marrack', '1989-01-03', '{}', '{}'),
('Tessa', 'Carman', '2012-02-18', '{}', '{}'),
('Peter', 'Duncan', '2000-09-06', '{}', '{"shellfish"}'),
('Foster', 'Shepard', '1980-01-08', '{}', '{}'),
('Tony', 'Cooper', '1994-03-06', '{"hydrapermazol:300mg", "dodoxadin:30mg"}', '{"shellfish"}'),
('Lily', 'Cooper', '1994-03-06', '{}', '{}'),
('Sophia', 'Zemicks', '1988-03-06', '{"aznol:60mg", "hydrapermazol:900mg", "pharmacol:5000mg", "terazine:500mg"}', '{"peanut", "shellfish", 
"aznol"}'),
('Warren', 'Zemicks', '1985-03-06', '{}', '{}'),
('Zach', 'Zemicks', '2017-03-06', '{}', '{}'),
('Reginold', 'Walker', '1979-08-30', '{"thradox:700mg"}', '{"illisoxian"}'),
('Jamie', 'Peters', '1982-03-06', '{}', '{}'),
('Ron', 'Peters', '1965-04-06', '{}', '{}'),
('Allison', 'Boyd', '1965-03-15', '{"aznol:200mg"}', '{"nillacilan"}'),
('Brian', 'Stelzer', '1975-12-06', '{"ibupurin:200mg", "hydrapermazol:400mg"}', '{"nillacilan"}'),
('Shawna', 'Stelzer', '1980-07-08', '{}', '{}'),
('Kendrik', 'Stelzer', '2014-03-06', '{"noxidian:100mg", "pharmacol:2500mg"}', '{}'),
('Clive', 'Ferguson', '1994-03-06', '{}', '{}'),
('Eric', 'Cadigan', '1945-08-06', '{"tradoxidine:400mg"}', '{}');