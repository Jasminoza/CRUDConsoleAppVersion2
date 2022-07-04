--changeset author:YolkinNV
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