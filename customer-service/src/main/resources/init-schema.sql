DROP SCHEMA IF EXISTS customer CASCADE;

CREATE SCHEMA customer;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--DROP TABLE IF EXISTS customer.customers CASCADE;

CREATE TABLE customer.customers
(
    id uuid NOT NULL,
    email character varying COLLATE pg_catalog."default" ,
    name character varying COLLATE pg_catalog."default" ,
    mobile integer ,
    pin_code integer NOT NULL,
    visiting_Date Date NOT NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);