create table user
(
    id              int          not null auto_increment PRIMARY KEY,
    username        varchar(50)  not null,
    password        char(70)     not null,
    phone_number    char(20)     not null,
    email           varchar(260),
    create_time     datetime     not null,
    last_login_time datetime,
    avatar          varchar(500),
    email_active    tinyint(1)   not null,
    role            varchar(100) not null,
    index(username),
    index (phone_number),
    index (email)
);