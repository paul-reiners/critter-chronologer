create table if not exists employee (
   id bigint not null,
   primary key (id)
);

create table if not exists pet_owner (
   pet_id bigint not null,
   owner_id bigint not null
);
