-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 05-12-2024 a las 14:58:56
-- Versión del servidor: 8.0.39
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `js_nails_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo_venta`
--

CREATE TABLE `articulo_venta` (
  `id` int NOT NULL,
  `denominacion` text,
  `estado` int NOT NULL,
  `observacion` text,
  `linea_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `articulo_venta`
--

INSERT INTO `articulo_venta` (`id`, `denominacion`, `estado`, `observacion`, `linea_id`) VALUES
(1, 'Articulo Venta 1', 0, 'Observacion 1', 1),
(2, 'Articulo Venta 2', 0, 'Observación 3', 3),
(3, 'Articulo Venta 3', 0, NULL, 2),
(4, 'Articulo Venta 4', 0, NULL, 1),
(5, 'Articulo Venta 5', 0, NULL, 3),
(6, 'articulo nuevo', 0, NULL, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int NOT NULL,
  `celular` text,
  `contacto` text,
  `estado` int NOT NULL,
  `fecha_inicio` datetime(6) DEFAULT NULL,
  `fecha_nacimiento` datetime(6) DEFAULT NULL,
  `letra` text,
  `mail` text,
  `razon_social` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `celular`, `contacto`, `estado`, `fecha_inicio`, `fecha_nacimiento`, `letra`, `mail`, `razon_social`) VALUES
(1, '3532468166', 'contacto 1', 0, '2024-11-26 17:37:09.000000', '2014-11-20 17:37:16.000000', 'letra 1', 'mail 1', 'razon 1'),
(2, '3532468166', 'contacto 2', 0, '2024-11-26 17:37:09.000000', '2014-11-20 17:37:16.000000', 'letra 2', 'mail 2', 'razon 2'),
(3, '3454324234', NULL, 1, NULL, NULL, NULL, 'mail3@gmail', 'cliente 3'),
(6, '3532468166', 'contacto 1', 0, '2024-11-26 17:37:09.000000', '2014-11-20 17:37:16.000000', 'letra 1', 'mail 1', 'razon 1'),
(7, '3532468166', 'contacto 2', 0, '2024-11-26 17:37:09.000000', '2014-11-20 17:37:16.000000', 'letra 2', 'mail 2', 'razon 2'),
(8, '345643244', 'contacto 3', 0, '2024-11-19 19:33:45.000000', '2024-11-27 19:33:39.000000', 'letra 3', 'mail@gmail.com', 'cliente 3'),
(9, '3532468166', 'contacto 1', 0, '2024-11-26 17:37:09.000000', '2014-11-20 17:37:16.000000', 'letra 1', 'mail 1', 'razon 1'),
(10, '3532468166', 'contacto 2', 0, '2024-11-26 17:37:09.000000', '2014-11-20 17:37:16.000000', 'letra 2', 'mail 2', 'razon 2'),
(11, '345643244', 'contacto 3', 0, '2024-11-19 19:33:45.000000', '2024-11-27 19:33:39.000000', 'letra 3', 'mail@gmail.com', 'cliente 3'),
(12, '3532468166', 'contacto 1', 0, '2024-11-26 17:37:09.000000', '2014-11-20 17:37:16.000000', 'letra 1', 'mail 1', 'razon 1'),
(13, '3532468166', 'contacto 2', 0, '2024-11-26 17:37:09.000000', '2014-11-20 17:37:16.000000', 'letra 2', 'mail 2', 'razon 2'),
(14, '345643244', 'contacto 3', 0, '2024-11-19 19:33:45.000000', '2024-11-27 19:33:39.000000', 'letra 3', 'mail@gmail.com', 'cliente 3'),
(15, '4353654364', NULL, 0, NULL, NULL, NULL, '1fernando1caglier@gmail.com', 'cliente nuevo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item_servicio`
--

CREATE TABLE `item_servicio` (
  `id` int NOT NULL,
  `estado` int NOT NULL,
  `observacion` text,
  `precio` double DEFAULT NULL,
  `servicio_id` int DEFAULT NULL,
  `tipo_servicio_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `item_servicio`
--

INSERT INTO `item_servicio` (`id`, `estado`, `observacion`, `precio`, `servicio_id`, `tipo_servicio_id`) VALUES
(1, 0, 'Item Servicio 1', 10000, 2, 2),
(2, 0, 'Item Servicio 1', 20000, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea`
--

CREATE TABLE `linea` (
  `id` int NOT NULL,
  `codigo` int NOT NULL,
  `denominacion` text,
  `detalle` text,
  `estado` int NOT NULL,
  `observacion` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `linea`
--

INSERT INTO `linea` (`id`, `codigo`, `denominacion`, `detalle`, `estado`, `observacion`) VALUES
(1, 1, 'Linea 1', 'Es la Linea 1', 0, 'observacion 1'),
(2, 2, 'Linea 2', 'Esta es la Linea 2', 0, 'observacion 2'),
(3, 3, 'Linea 3', 'Esta es la Linea 3', 0, 'observacion 3'),
(4, 4, 'linea 4', 'detalle 4', 0, 'observacion 4'),
(5, 0, 'Linea 5', NULL, 0, 'observacion 5'),
(6, 0, 'Linea 6', NULL, 0, NULL),
(7, 0, 'Linea 7', NULL, 0, NULL),
(8, 0, 'Linea 8', NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id` int NOT NULL,
  `estado` int NOT NULL,
  `fecha_realizacion` datetime(6) DEFAULT NULL,
  `fecha_registro` datetime(6) DEFAULT NULL,
  `total` double NOT NULL,
  `cliente_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id`, `estado`, `fecha_realizacion`, `fecha_registro`, `total`, `cliente_id`) VALUES
(1, 0, '2024-11-26 17:38:44.000000', '2024-11-07 17:38:44.000000', 30000, 1),
(2, 0, '2024-11-26 17:38:44.000000', '2024-11-05 17:38:44.000000', 40000, 2),
(3, 0, NULL, NULL, 3000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_objeto`
--

CREATE TABLE `tipo_objeto` (
  `id` int NOT NULL,
  `codigo` int NOT NULL,
  `denominacion` text,
  `detalle` text,
  `estado` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipo_objeto`
--

INSERT INTO `tipo_objeto` (`id`, `codigo`, `denominacion`, `detalle`, `estado`) VALUES
(1, 1, 'Tipo Objeto 1', 'Detalle 1', 0),
(2, 2, 'Tipo Objeto 2', 'Detalle 2', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_objeto_id_seq`
--

CREATE TABLE `tipo_objeto_id_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipo_objeto_id_seq`
--

INSERT INTO `tipo_objeto_id_seq` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_servicio`
--

CREATE TABLE `tipo_servicio` (
  `id` int NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `denominacion` text,
  `detalle` text,
  `estado` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipo_servicio`
--

INSERT INTO `tipo_servicio` (`id`, `codigo`, `denominacion`, `detalle`, `estado`) VALUES
(1, '1', 'Tipo Servicio 1', 'Detalle 1', 0),
(2, '2', 'Tipo Servicio 2', 'Detalle 2', 0),
(3, NULL, 'nuevo ', NULL, 1),
(4, NULL, 'tipo nuevo', NULL, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo_venta`
--
ALTER TABLE `articulo_venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKte68hnsa0jst8i5h3olrmunod` (`linea_id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `item_servicio`
--
ALTER TABLE `item_servicio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj4n5onjmo5nmqn5qb6iq2522j` (`servicio_id`),
  ADD KEY `FKai60b8bjnxpft939dpk5gepxx` (`tipo_servicio_id`);

--
-- Indices de la tabla `linea`
--
ALTER TABLE `linea`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK78icgfs42juijhtl4qpgapvya` (`cliente_id`);

--
-- Indices de la tabla `tipo_objeto`
--
ALTER TABLE `tipo_objeto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_servicio`
--
ALTER TABLE `tipo_servicio`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo_venta`
--
ALTER TABLE `articulo_venta`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `item_servicio`
--
ALTER TABLE `item_servicio`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `linea`
--
ALTER TABLE `linea`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipo_servicio`
--
ALTER TABLE `tipo_servicio`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo_venta`
--
ALTER TABLE `articulo_venta`
  ADD CONSTRAINT `FKte68hnsa0jst8i5h3olrmunod` FOREIGN KEY (`linea_id`) REFERENCES `linea` (`id`);

--
-- Filtros para la tabla `item_servicio`
--
ALTER TABLE `item_servicio`
  ADD CONSTRAINT `FKai60b8bjnxpft939dpk5gepxx` FOREIGN KEY (`tipo_servicio_id`) REFERENCES `tipo_servicio` (`id`),
  ADD CONSTRAINT `FKj4n5onjmo5nmqn5qb6iq2522j` FOREIGN KEY (`servicio_id`) REFERENCES `servicio` (`id`);

--
-- Filtros para la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `FK78icgfs42juijhtl4qpgapvya` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
