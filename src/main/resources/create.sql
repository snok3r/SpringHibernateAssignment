CREATE TABLE `mydb`.`articles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(150) NOT NULL,
  `content` MEDIUMTEXT NOT NULL,
  `publication_date` DATE NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));