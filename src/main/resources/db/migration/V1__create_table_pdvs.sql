CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;

CREATE TABLE IF NOT EXISTS pdvs(
    id BIGSERIAL NOT NULL,
    trading_name VARCHAR(255) NOT NULL,
    owner_name VARCHAR(255) NOT NULL,
    document VARCHAR(255) NOT NULL,
    coverage_area GEOMETRY NOT NULL,
    address GEOMETRY NOT NULL,
    primary key (id),
    UNIQUE (id, document)
);
