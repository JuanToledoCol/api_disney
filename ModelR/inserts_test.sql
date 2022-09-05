INSERT INTO `challenge_java`.`personajes` 
(`edad`, `historia`, `imagen`, `nombre_personaje`, `peso`) 
VALUES 
('12', 'Alguna historia', '/assets/img/personajes/imagen01', 'Pepito', '21.3'),
('32', 'Alguna historia', '/assets/img/personajes/imagen01', 'Mario', '41.3'),
('23', 'Alguna historia', '/assets/img/personajes/imagen02', 'Francia', '18.9'),
('15', 'Alguna historia', '/assets/img/personajes/imagen03', 'Mickey', '41.3'),
('18', 'Alguna historia', '/assets/img/personajes/imagen04', 'Pluto', '16.8');

INSERT INTO `challenge_java`.`peliculas_series` (`calificacion`, `titulo`, `fecha_creacion`) 
VALUES 
('4', 'la bella y la bestia', '2001-12-21'),
('5', 'mickey mouse', '2002-01-12'),
('2', 'alturas de amor', '2002-08-01'),
('1', 'princesita sofia', '2002-11-19');

#INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`id_pelicula_serie`, `id_personaje`) VALUES ('2', '1');
#INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`id_pelicula_serie`, `id_personaje`) VALUES ('2', '2');
#INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`id_pelicula_serie`, `id_personaje`) VALUES ('2', '3');
#INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`id_pelicula_serie`, `id_personaje`) VALUES ('3', '4');
#INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`id_pelicula_serie`, `id_personaje`) VALUES ('4', '5');
#INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`id_pelicula_serie`, `id_personaje`) VALUES ('1', '1');
#INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`id_pelicula_serie`, `id_personaje`) VALUES ('1', '5');
#INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`id_pelicula_serie`, `id_personaje`) VALUES ('1', '3');

INSERT INTO `challenge_java`.`rel_personajes_peliculas` (`fk_id_pelicula_serie`, `fk_id_personaje`) 
VALUES 
('2', '1'),
('2', '2'),
('2', '3'),
('3', '4'),
('4', '5'),
('1', '1'),
('1', '5');

