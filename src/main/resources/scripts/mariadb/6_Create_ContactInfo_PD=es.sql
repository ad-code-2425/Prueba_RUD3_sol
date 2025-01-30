USE instituto;

-- 🔹 1️⃣ Buscar y eliminar la clave foránea si existe
SELECT CONSTRAINT_NAME 
INTO @constraint_name
FROM information_schema.TABLE_CONSTRAINTS
WHERE TABLE_SCHEMA = DATABASE() 
AND TABLE_NAME = 'contactInfo' 
AND CONSTRAINT_TYPE = 'FOREIGN KEY'
LIMIT 1;

SET @sql = IF(@constraint_name IS NOT NULL, 
              CONCAT('ALTER TABLE contactInfo DROP FOREIGN KEY ', @constraint_name), 
              'SELECT 1');
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- 🔹 2️⃣ Eliminar la tabla si existe
DROP TABLE IF EXISTS contactInfo;

-- 🔹 3️⃣ Crear la tabla si no existe
CREATE TABLE IF NOT EXISTS contactInfo (
    profesorId INT NOT NULL,
    email VARCHAR(255) NOT NULL,
    tlf_movil VARCHAR(15) NULL,
    PRIMARY KEY (profesorId),
    UNIQUE (email)
) ENGINE=InnoDB;

-- 🔹 4️⃣ Agregar la clave foránea
ALTER TABLE contactInfo 
ADD CONSTRAINT FK_contactInfo_profesor 
FOREIGN KEY (profesorId) REFERENCES profesor(Id);
