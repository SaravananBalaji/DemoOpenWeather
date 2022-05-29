create table weather (
       id integer not null,
        country_name varchar(255),
        created_by varchar(255) not null,
        created_date timestamp not null,
        description varchar(255),
        json_details json,
        lat double,
        lon double,
        modified_by varchar(255),
        modified_date timestamp,
        open_weather_id integer,
        timezone integer,
        primary key (id)
    )