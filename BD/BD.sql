CREATE TABLE `AdministradorSupremo` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `Usuario` (
  `id` BIGINT PRIMARY KEY,
  `nombre` varchar(255),
  `telefono` BIGINT,
  `direccion` varchar(255),
  `ciudad` varchar(255),
  `user` varchar(255),
  `password` varchar(255),
  `estado` boolean,
  `rol` varchar(255)
);

CREATE TABLE `Producto` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `descripcion` varchar(255),
  `unidad_medida` varchar(255),
  `valor_compra` double,
  `valor_venta` double,
  `ganancia` double,
  `estado` boolean,
  `cantidad_stock` double
);

CREATE TABLE `Movimiento` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `fecha_movimiento` datetime,
  `descripcion` varchar(255),
  `tipo_mov` varchar(255),
  `id_remitente` int,
  `id_destino` int,
  `descuento_aplicado` double,
  `usuario_trans` BIGINT,
  `id_cliente` BIGINT
);

CREATE TABLE `Cliente` (
  `id` BIGINT PRIMARY KEY,
  `nombre` varchar(255),
  `telefono` BIGINT,
  `direccion` varchar(255),
  `ciudad` varchar(255),
  `barrio` varchar(255)
);

CREATE TABLE `Producto_Movimiento` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_producto` int,
  `id_mov` int,
  `cant_trans` double,
  `valor_trans` double
);

ALTER TABLE `Movimiento` ADD FOREIGN KEY (`usuario_trans`) REFERENCES `Usuario` (`id`);

ALTER TABLE `Movimiento` ADD FOREIGN KEY (`id_cliente`) REFERENCES `Cliente` (`id`);

ALTER TABLE `Producto_Movimiento` ADD FOREIGN KEY (`id_producto`) REFERENCES `Producto` (`id`);

ALTER TABLE `Producto_Movimiento` ADD FOREIGN KEY (`id_mov`) REFERENCES `Movimiento` (`id`);
