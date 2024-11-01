-- MySQL Workbench Forward Engineering

GRANT ALL PRIVILEGES ON biblioteca.* TO 'root'@'localhost';
FLUSH PRIVILEGES; /*permissoes*/

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema e-commerce
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema e-commerce
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `e-commerce` DEFAULT CHARACTER SET utf8 ;
USE `e-commerce` ;

-- -----------------------------------------------------
-- Table `e-commerce`.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Clientes` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `Telefone` INT NULL,
  `Data_cadastro` DATE NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`StatusPedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`StatusPedido` (
  `idStatusPedido` INT NOT NULL AUTO_INCREMENT,
  `StatusAtual` VARCHAR(45) NULL,
  PRIMARY KEY (`idStatusPedido`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`Carrinho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Carrinho` (
  `idCarrinho` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `idStatusPedido` INT NOT NULL,
  `Quantidade` INT NULL,
  PRIMARY KEY (`idCarrinho`, `idStatusPedido`, `idCliente`),
  INDEX `idStatusPedido_idx` (`idStatusPedido` ASC) VISIBLE,
  INDEX `idCliente_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `idStatusPedido`
    FOREIGN KEY (`idStatusPedido`)
    REFERENCES `e-commerce`.`StatusPedido` (`idStatusPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `e-commerce`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Endereco` (
  `idEndereco` INT NOT NULL,
  `idCliente` INT NOT NULL,
  `Rua` VARCHAR(45) NULL,
  `Cidade` VARCHAR(9) NULL,
  `Estado` VARCHAR(45) NULL,
  `CEP` INT NULL,
  `NumCasa` INT NULL,
  `Complemento` VARCHAR(45) NULL,
  PRIMARY KEY (`idEndereco`, `idCliente`),
  INDEX `idCliente_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `idClienteEnd`
    FOREIGN KEY (`idCliente`)
    REFERENCES `e-commerce`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`Categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Categorias` (
  `idCategorias` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NULL,
  `Descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`idCategorias`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Fornecedor` (
  `idFornecedor` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NULL,
  `Contato` VARCHAR(45) NULL,
  `Telefone` INT NULL,
  `Email` VARCHAR(45) NULL,
  PRIMARY KEY (`idFornecedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`Produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Produtos` (
  `idProdutos` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NULL,
  `Descricao` VARCHAR(45) NULL,
  `Preco` DOUBLE NULL,
  `estoque` INT NULL,
  `idFornecedor` INT NULL,
  `idCategoria` INT NULL,
  PRIMARY KEY (`idProdutos`),
  INDEX `idCategoria_idx` (`idCategoria` ASC) VISIBLE,
  INDEX `idFornecedor_idx` (`idFornecedor` ASC) VISIBLE,
  CONSTRAINT `idCategoria`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `e-commerce`.`Categorias` (`idCategorias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idFornecedor`
    FOREIGN KEY (`idFornecedor`)
    REFERENCES `e-commerce`.`Fornecedor` (`idFornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`Venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `Data_Venda` DATE NULL,
  `idStatusPedido` INT NULL,
  PRIMARY KEY (`idVenda`, `idCliente`),
  INDEX `idCliente_idx` (`idCliente` ASC) VISIBLE,
  INDEX `idStatusPedido_idx` (`idStatusPedido` ASC) VISIBLE,
  CONSTRAINT `idClienteVend`
    FOREIGN KEY (`idCliente`)
    REFERENCES `e-commerce`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idStatusPedidoVend`
    FOREIGN KEY (`idStatusPedido`)
    REFERENCES `e-commerce`.`StatusPedido` (`idStatusPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`Itens_Venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Itens_Venda` (
  `idItem` INT NOT NULL,
  `idVenda` INT NOT NULL,
  `idProduto` INT NOT NULL,
  `Quantidade` INT NULL,
  `Preco_Unitario` DOUBLE NULL,
  PRIMARY KEY (`idItem`, `idProduto`, `idVenda`),
  INDEX `idVendas_idx` (`idVenda` ASC) VISIBLE,
  INDEX `idProduto_idx` (`idProduto` ASC) VISIBLE,
  CONSTRAINT `idVendas`
    FOREIGN KEY (`idVenda`)
    REFERENCES `e-commerce`.`Venda` (`idVenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idProduto`
    FOREIGN KEY (`idProduto`)
    REFERENCES `e-commerce`.`Produtos` (`idProdutos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-commerce`.`Pagamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-commerce`.`Pagamentos` (
  `idPagamentos` INT NOT NULL AUTO_INCREMENT,
  `idVenda` INT NOT NULL,
  `Data_Pagamento` DATE NULL,
  `Valor_Pago` DOUBLE NULL,
  `Metodo_Pagamento` INT NULL,
  PRIMARY KEY (`idPagamentos`, `idVenda`),
  INDEX `idVendas_idx` (`idVenda` ASC) VISIBLE,
  CONSTRAINT `idVenda`
    FOREIGN KEY (`idVenda`)
    REFERENCES `e-commerce`.`Venda` (`idVenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
