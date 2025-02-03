-- Seleccionar la base de datos
USE instituto;

-- Eliminar la clave foránea si existe
ALTER TABLE contactInfo 
DROP FOREIGN KEY FK_contactInfo_profesor;

-- Eliminar la tabla si existe
DROP TABLE IF EXISTS contactInfo;

-- Crear la tabla contactInfo
CREATE TABLE contactInfo (
    id INT AUTO_INCREMENT NOT NULL,
    profesorId INT NOT NULL,
    email VARCHAR(255) NOT NULL,
    tlf_movil VARCHAR(15),
    PRIMARY KEY (id),
    UNIQUE KEY UC_contactInfo_UNIQUE_profesorID (profesorId),
    UNIQUE KEY UC_contactInfo_UNIQUE_email (email),
    CONSTRAINT FK_contactInfo_profesor FOREIGN KEY (profesorId) REFERENCES profesor(Id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Nota:
-- En MySQL, las restricciones de integridad referencial se crean directamente en la definición de la tabla.
-- La opción ENGINE=InnoDB permite el uso de claves foráneas.
