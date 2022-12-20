
DROP DATABASE IF EXISTS employees;
CREATE DATABASE employees;

CREATE TABLE TBL_EMP
(
empid int AUTO_INCREMENT PRIMARY KEY,
managerid int,
name varchar(50),
Salary int
);


INSERT INTO `tbl_emp`(`empid`, `managerid`, `name`, `Salary`) 
VALUES ('1',NULL,'Ahmed','60000'),
('2','11','Bob','50000'),
('3','1','Bernard','45000'),
('4','11','Bill','46000'),
('5','1','Claire','50000'),
('6',NULL,'Mike','64000'),
('7','3','Megan','39000'),
('8','3','Charile','25000'),
('9','3','Davina','30000'),
('10',NULL,'David','48500'),
('11',NULL,'Edward','59000');

