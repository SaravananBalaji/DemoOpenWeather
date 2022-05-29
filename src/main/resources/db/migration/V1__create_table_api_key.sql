create table api_key (
       id integer not null,
        api_key varchar(255) not null,
        api_name varchar(255) not null,
        count integer,
        created_by varchar(255) not null,
        created_date timestamp not null,
        modified_by varchar(255),
        modified_date timestamp,
        version bigint,
        primary key (id)
    );

drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1