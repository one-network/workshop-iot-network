create table if not exists device_orientation (
    name text,
    version integer not null default 0,
    x double precision,
    y double precision,
    z double precision,
    w double precision,
    constraint pk_device_orient primary key (name),
    constraint fk_device_orient_name foreign key (name) references device (name)
);
