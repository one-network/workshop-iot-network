create table if not exists device (
    name text,
    version integer not null default 0,
    last_seen timestamp,
    constraint pk_device primary key (name)
);

create table if not exists device_location (
    name text,
    version integer not null default 0,
    accuracy double precision,
    latitude double precision,
    longitude double precision,
    altitude double precision,
    altitude_accuracy double precision,
    heading double precision,
    speed double precision,
    constraint pk_device_loc primary key (name),
    constraint fk_device_loc_name foreign key (name) references device (name)
);
