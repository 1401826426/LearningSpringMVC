DROP TABLE if EXISTS commodity ;
create table commodity(
    `id` varchar(50) primary key ,
    `description` varchar(1000) default "",
    `price` float(32,2) ,
    `image` blob ,
    `type` varchar(20)
) ;
CREATE index index_name on commodity(type) USING HASH;
