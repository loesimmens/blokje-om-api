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

   primary key (id)
);
