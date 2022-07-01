liquibase --username=test --password=test --changelog-file=<crudConsoleApp.changelog-master.yaml> status
create TABLE `specialties` (
    `id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
insert into `specialties` values ('1', 'Java') , ('2', 'SQL'), ('3', 'Python'), ('4', 'C++');

create TABLE `skills` (
    `id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
insert into `skills` values ('1', 'drink coffee'), ('2', 'writing code'), ('3', 'sleeping');

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