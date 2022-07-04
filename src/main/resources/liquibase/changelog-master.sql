--liquibase formatted sql

--changeset id:create-table-status author:YolkinNV
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` enum('ACTIVE','DELETED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `status` (`status`)
);
--rollback drop table status;

--changeset id:create-table-specialties author:YolkinNV
create TABLE `specialties` (
    `id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
--rollback drop table specialties;

--changeset id:create-table-skills author:YolkinNV
create TABLE `skills` (
    `id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
--rollback drop table skills;

--changeset id:create-table-developers author:YolkinNV
create TABLE `developers` (
	`id` int NOT NULL AUTO_INCREMENT,
	`firstName` varchar(255) NOT NULL,
	`lastName` varchar(255) NOT NULL,
	`specialty` int NOT NULL,
	`status` int NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `developers_fk0`
	FOREIGN KEY (`specialty`) REFERENCES `specialties`(`id`),
	CONSTRAINT `developers_fk1`
	FOREIGN KEY (`status`) REFERENCES `status`(`id`)
);
--rollback drop table developers;

--changeset id:create-table-developers_Skills author:YolkinNV
create TABLE `developers_Skills` (
    `developer_ID` int NOT NULL,
    `skill_ID` int NOT NULL,
	CONSTRAINT `developers_Skills_fk0`
	FOREIGN KEY (`developer_ID`) REFERENCES `developers`(`id`),
	CONSTRAINT `developers_Skills_fk1`
	FOREIGN KEY (`skill_ID`) REFERENCES `skills`(`id`)
);
--rollback drop table developers_Skills;

--changeset id:insert-initial-values-into-status author:YolkinNV
insert into status values('1', 'ACTIVE'), ('2', 'DELETED');
--rollback delete from status where id > 0;

--changeset id:insert-initial-values-into-specialties author:YolkinNV
insert into `specialties` values ('1', 'Java') , ('2', 'SQL'), ('3', 'Python'), ('4', 'C++');
--rollback delete from specialties where id > 0;

--changeset id:insert-initial-values-into-skills author:YolkinNV
insert into `skills` values ('1', 'drink coffee'), ('2', 'writing code'), ('3', 'sleeping');
--rollback delete from skills where id > 0;

--changeset id:insert-initial-values-into-developers author:YolkinNV
insert into developers (`firstName`,`lastName`,specialty,status) values('John','Smith',4,1);
insert into developers (`firstName`,`lastName`,specialty,status) values('Anna','Bloomberg',1,1);
insert into developers (`firstName`,`lastName`,specialty,status) values('Nick','Dalton',2,1);
--rollback delete from developers where id > 0;

--changeset id:insert-initial-values-into-developers_Skills author:YolkinNV
insert into developers_Skills (`developer_ID`, `skill_ID`) values(1, 1);
insert into developers_Skills (`developer_ID`, `skill_ID`) values(1, 2);
insert into developers_Skills (`developer_ID`, `skill_ID`) values(2, 2);
insert into developers_Skills (`developer_ID`, `skill_ID`) values(3, 1);
insert into developers_Skills (`developer_ID`, `skill_ID`) values(3, 2);
insert into developers_Skills (`developer_ID`, `skill_ID`) values(3, 3);
--rollback delete from developers_Skills where developer_id > 0;