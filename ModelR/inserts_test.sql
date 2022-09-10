INSERT INTO `challenge_java`.`personajes` 
(`edad`, `historia`, `imagen`, `nombre_personaje`, `peso`) 
VALUES 
('12', 'Alguna historia', '/assets/img/personajes/imagen01', 'Pepito', '21.3'),
('32', 'Alguna historia', '/assets/img/personajes/imagen01', 'Mario', '41.3'),
('23', 'Alguna historia', '/assets/img/personajes/imagen02', 'Francia', '18.9'),
('15', 'Alguna historia', '/assets/img/personajes/imagen03', 'Mickey', '41.3'),
('18', 'Alguna historia', '/assets/img/personajes/imagen04', 'Pluto', '16.8');

INSERT INTO `challenge_java`.`peliculas_series` (`calificacion`, `titulo`, `fecha_creacion`, `imagen`) 
VALUES 
('4', 'la bella y la bestia', '2001-12-21', '/assets/img/peliculas/imagen01'),
('5', 'mickey mouse', '2002-01-12', '/assets/img/peliculas/imagen02'),
('2', 'alturas de amor', '2002-08-01', '/assets/img/peliculas/imagen03'),
('1', 'princesita sofia', '2002-11-19', '/assets/img/peliculas/imagen04');

INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`fk_id_pelicula_serie`, `fk_id_personaje`) 
VALUES 
('2', '1'),
('2', '2'),
('2', '3'),
('3', '4'),
('4', '5'),
('1', '1'),
('1', '5');

INSERT INTO `challenge_java`.`generos` (`nombre_genero`, `imagen`) VALUES 
('terror', '/assets/img/generos/imagen01'),
('accion', '/assets/img/generos/imagen02'),
('drama', '/assets/img/generos/imagen03'),
('ficcion', '/assets/img/generos/imagen04'),
('anime', '/assets/img/generos/imagen05'),
('comedia', '/assets/img/generos/imagen06');

INSERT INTO `challenge_java`.`rel_generos_peliculas` (`fk_id_genero`, `fk_id_pelicula_serie`) VALUES 
('1', '1'),
('2', '1'),
('1', '4'), 
('5', '2'),
('3', '3');



