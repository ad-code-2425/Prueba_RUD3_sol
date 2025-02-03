USE instituto;



-- 🔹 Eliminar la tabla si existe
DROP TABLE IF EXISTS contactInfo;

-- 🔹  Crear la tabla si no existe
CREATE TABLE IF NOT EXISTS contactInfo (
    profesorId INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) NOT NULL,
    tlf_movil VARCHAR(15) NULL,
    PRIMARY KEY (profesorId),
    UNIQUE (email)
) ENGINE=InnoDB;

-- 🔹 Agregar la clave foránea
ALTER TABLE contactInfo 
ADD CONSTRAINT FK_contactInfo_profesor 
FOREIGN KEY (profesorId) REFERENCES profesor(Id);
