SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecommerce
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ecommerce` ;
-----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecommerce` ;
USE `ecommerce` ;

-- -----------------------------------------------------
-- Table `ecommerce`.`Utente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`Utente` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`Utente` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(255) NOT NULL,
  `Indirizzo` VARCHAR(255) NOT NULL,
  `MetodoPag` VARCHAR(45) NOT NULL,
  UNIQUE KEY unique_email (email)
  );
  



-- -----------------------------------------------------
-- Table `ecommerce`.`Prodotti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`Prodotti` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`Prodotti` (
  `codice`INT NOT NULL,
  `prezzo` FLOAT NOT NULL,
  `descrizione` VARCHAR(1000) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `CategoriaID` VARCHAR(255) NOT NULL,
  `Disponibilita` INT NOT NULL,
  `immagine` VARCHAR(255) NOT NULL,
   UNIQUE KEY unique_codice (codice));


-- -----------------------------------------------------
-- Table `ecommerce`.`Carrello`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`Carrello` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`Carrello` (
  `Utente_id` INT NOT NULL,
  `Prodotti_codice` INT NOT NULL,
  `Quantit` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`Utente_id`),
  CONSTRAINT `fk_Carrello_Utente1`
    FOREIGN KEY (`Utente_id`)
    REFERENCES `ecommerce`.`Utente` (`id`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_Carrello_Prodotti1`
    FOREIGN KEY (`Prodotti_codice`)
    REFERENCES `ecommerce`.`Prodotti` (`codice`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce`.`Ordine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`Ordine` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`Ordine` (
   CarrelloJSON TEXT,	
  `Data` DATE NOT NULL,
  `Stato` varchar(50) NOT NULL,
  `Carrello_Utente_id` INT NOT NULL,
  PRIMARY KEY ( `Carrello_Utente_id`)
   );


-- -----------------------------------------------------
-- Table `ecommerce`.`GestoreCatalogo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`GestoreCatalogo` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`GestoreCatalogo` (
  `Utente_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  CONSTRAINT `fk_Gestore Catalogo_Utente1`
    FOREIGN KEY (`Utente_id`)
    REFERENCES `ecommerce`.`Utente` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `ecommerce`.`GestoreOrdini`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`GestoreOrdini` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`GestoreOrdini` (
  `Utente_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  CONSTRAINT `fk_Gestore Ordinii_Utente1`
    FOREIGN KEY (`Utente_id`)
    REFERENCES `ecommerce`.`Utente` (`id`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
