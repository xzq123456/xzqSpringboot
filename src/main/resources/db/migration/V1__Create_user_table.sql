create table USER (
    ID INTEGER auto_increment primary key not null ,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(100),
    TOKEN CHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT);