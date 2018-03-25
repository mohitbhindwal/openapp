GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO samajappdb;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO samajappdb;

CREATE TABLE images(id bigint primary key , imgname character varying(200), imgbytes bytea , created_dt timestamp without time zone DEFAULT DATE_TRUNC('second', NOW())); 


create table users (
id serial primary key,
userid character varying(300) unique,
username character varying(300),
password  character varying(100),
created_dt timestamp without time zone DEFAULT now(),
dob date,
address text ,
contactno character varying(15),
city character varying(30),
gender character varying(6),
profileid bigint
);