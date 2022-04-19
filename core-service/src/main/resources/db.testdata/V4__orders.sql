create table orders
(
    id             bigserial primary key,
    username       varchar(255),
    total_price    numeric(8, 2) not null,
    address        varchar(255),
    address_line_1 varchar(255),
    address_line_2 varchar(255),
    admin_area_1   varchar(255),
    admin_area_2   varchar(255),
    postal_code    varchar(255),
    country_code   varchar(255),
    phone          varchar(255),
    status         enum('CREATED', 'PAID', 'CANCELLED'),
    created_at     timestamp default current_timestamp,
    updated_at     timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint references products (id),
    order_id          bigint references orders (id),
    price_per_product numeric(8, 2),
    price             numeric(8, 2),
    quantity          int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

insert into orders (username, total_price, address, phone)
values ('bob', 200.00, 'address', '12345');

insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100.00, 200.00);