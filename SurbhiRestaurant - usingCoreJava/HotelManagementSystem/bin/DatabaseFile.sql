create database hotelmanagement;
use hotelmanagement;
create table user(userId int primary key auto_increment,userName varchar(20),emailId varchar(20),password varchar(20),type varchar(20));
create table item(menuId int primary key auto_increment,itemName varchar(20),price float,stock int,rating int);
create table orders(orderId int primary key auto_increment,menuId int,userId int , quantity int,price float,date date);
select * from item;
select * from orders;