CREATE USER 'user-bookdb'@'localhost'
    IDENTIFIED BY 'abc123.';
GRANT CREATE,ALTER,INSERT,SELECT,UPDATE,DELETE
    ON bookdb.*
  TO 'user-bookdb'@'localhost';