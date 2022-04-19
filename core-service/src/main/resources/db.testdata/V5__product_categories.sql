create table product_categories
(
    id         bigserial primary key,
    title      VARCHAR(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into product_categories (title)
values ('Other'),
       ('Category 1'),
       ('Category 2'),
       ('Category 3');

/*alter table products
    add category_id bigserial references product_categories (id);

update products
set category_id = 1
where id < 5;
update products
set category_id = 2
where id = 6;
update products
set category_id = 3
where id = 7;
update products
set category_id = 4
where id = 8;*/