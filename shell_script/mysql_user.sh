#!/bin/bash
DB_USER=$1
DB_PASSWORD=$2
DB_NAME='sns'

mysql -u$DB_USER -p$DB_PASSWORD << EOF
use $DB_NAME

insert into profile values ('japen','','');

insert into user (id,pw,email,name,isAccountNonExpired,isAccountNonLocked,isCredentialsNonExpired,isEnabled) values ('japen', '1234', 'singapore@naver.com','SI',true,true,true,true);

insert into profile values ('ab','','');

insert into user (id,pw,email,name,isAccountNonExpired,isAccountNonLocked,isCredentialsNonExpired,isEnabled) values ('ab', 'abcde', 'abcd@naver.com','ABCE',true,true,true,true);

insert into authority values ('japen','ADMIN');"

insert into authority values ('japen','USER');

insert into authority values ('ab','USER');

exit
