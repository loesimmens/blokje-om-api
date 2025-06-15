create table lego_set (
   id varchar(8) not null,
   name varchar(255) not null,
   year int,
   theme varchar(255) not null,
   number_of_parts int,
   image_url varchar(255),
   creation_time timestamp(6) without time zone not null,
   modification_time timestamp(6) without time zone not null,
   rental_price_per_week real,
   build_together boolean,
   available boolean,
   description varchar(8000),

   primary key (id)
);

create table board_game (
    id varchar(8) not null,
    name varchar(255) not null,
    year int,
    image_url varchar(255),
    creation_time timestamp(6) without time zone not null,
    modification_time timestamp(6) with time zone not null,
    rental_price_per_week real,
    available boolean,
    description varchar(8000),
    min_players int,
    max_players int,
    playing_time int,
    min_play_time int,
    max_play_time int,
    age int,

    primary key (id)
);
