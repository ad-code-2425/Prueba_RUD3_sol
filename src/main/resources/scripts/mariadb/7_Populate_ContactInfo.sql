USE instituto;

-- 1️⃣ Contar filas en contactInfo y almacenar en una variable
SELECT COUNT(*) INTO @filas FROM contactInfo;

-- 2️⃣ Si la tabla está vacía, ejecutar el INSERT dinámico
SET @sql = IF(@filas = 0, 
    'INSERT INTO contactInfo (profesorId, email, tlf_movil)
    SELECT ID, 
           REPLACE("profesorXX@edu.gal", "XX", 100 + ROW_NUMBER() OVER (ORDER BY ID)), 
           CONCAT("+34 600 123 ", 100 + ROW_NUMBER() OVER (ORDER BY ID))
    FROM profesor ORDER BY ID;', 
    'SELECT 1'); 

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
