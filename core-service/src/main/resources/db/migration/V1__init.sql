create table if not exists products
(
    id         bigserial primary key,
    price      int,
    title      VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

INSERT INTO products (title, price)
VALUES ('Item 1', 645),
       ('Item 2', 423),
       ('Item 3', 2343);
