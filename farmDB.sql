/*

create table animal_type (
type_id int unsigned not null unique auto_increment,
type_name varchar(25) not null,
constraint pk_animal_type primary key (type_id)
);



create table animal (
animal_id int unsigned not null unique auto_increment,
animal_name varchar(25) not null,
animal_sex char(1),
date_birth date,
date_death date,
date_arrival date,
date_departure date,
animal_type int unsigned not null,
father_id int unsigned,
mother_id int unsigned,
is_research boolean,
constraint pk_animal primary key (animal_id),
constraint fk_animal_type foreign key(animal_type) references animal_type(type_id),
constraint fk_animal_father foreign key (father_id) references animal(animal_id),
constraint fk_animal_mother foreign key(mother_id) references animal(animal_id)
);

create table animal_delivery (
delivery_id int unsigned not null unique auto_increment,
delivery_date date not null,
delivery_number int unsigned not null,
father_id int unsigned,
mother_id int unsigned not null,
constraint pk_delivery primary key (delivery_id),
constraint fk_delivery_father foreign key (father_id) references animal(animal_id),
constraint fk_delivery_mother foreign key(mother_id) references animal(animal_id)
);

create table animal_vaccine (
vaccine_id int unsigned not null unique auto_increment,
vaccine_date date not null,
vaccine_type varchar(55) not null,
vaccine_quantity float not null,
animal_id int unsigned not null,
constraint pk_vaccine primary key (vaccine_id),
constraint fk_vaccine_animal foreign key(animal_id) references animal(animal_id)
);



create table animal_weight (
weight_id int unsigned not null unique auto_increment,
weight_date date not null,
weight_number float not null,
animal_id int unsigned not null,
constraint pk_weight primary key (weight_id),
constraint fk_weight_animal foreign key(animal_id) references animal(animal_id)
);

*/
