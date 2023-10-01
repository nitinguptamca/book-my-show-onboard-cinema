DROP SCHEMA IF EXISTS "booking" CASCADE;

CREATE SCHEMA "booking";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS booking_status;
CREATE TYPE booking_status AS ENUM ('PENDING', 'PAID', 'APPROVED', 'CANCELLED', 'CANCELLING');

DROP TABLE IF EXISTS "booking".bookings CASCADE;

CREATE TABLE "booking".bookings
(
    id uuid NOT NULL,
    customer_id uuid NOT NULL,
    cinema_id uuid NOT NULL,
    tracking_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    booking_status booking_status NOT NULL,
    failure_messages character varying COLLATE pg_catalog."default",
    CONSTRAINT bookings_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "booking".booking_items CASCADE;

CREATE TABLE "booking".booking_items
(
    id bigint NOT NULL,
    booking_id uuid NOT NULL,
    movie_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    seatNumber varchar(20) NOT NULL,
    movieTime TIMESTAMP NOT NULL,
    quantity integer NOT NULL,
    sub_total numeric(10,2) NOT NULL,
    CONSTRAINT booking_items_pkey PRIMARY KEY (id, booking_id)
);

ALTER TABLE "booking".booking_items
    ADD CONSTRAINT "FK_booking_ID" FOREIGN KEY (booking_id)
    REFERENCES "booking".bookings (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS "booking".booking_address CASCADE;

CREATE TABLE "booking".booking_address
(
    id uuid NOT NULL,
    booking_id uuid UNIQUE NOT NULL,
    street character varying COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying COLLATE pg_catalog."default" NOT NULL,
    city character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT booking_address_pkey PRIMARY KEY (id, booking_id)
);

ALTER TABLE "booking".booking_address
    ADD CONSTRAINT "FK_booking_ID" FOREIGN KEY (booking_id)
    REFERENCES "booking".bookings (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;


