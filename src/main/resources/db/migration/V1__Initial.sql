create table SALES
(
    ID           uuid primary key,
    IS_ON_SALE   boolean default false,
    TITLE        varchar(255)             not null,
    EXTRA        text,
    CREATED_DATE timestamp with time zone not null
);