create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price int    not null,
    address     varchar(255),
    phone       varchar(255)
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint references products (id),
    user_id           bigint references users (id),
    order_id          bigint references orders (id),
    price_per_product int,
    price             int
)