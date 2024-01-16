------------CREATE DB WITH USER-------------------
-- CREATE DATABASE IF NOT EXISTS test;
--
-- \c test;
--
-- CREATE USER IF NOT EXISTS userjon WITH ENCRYPTED PASSWORD '123';
--
-- ALTER ROLE userjon SET client_encoding TO 'utf8';
-- ALTER ROLE userjon SET default_transaction_isolation TO 'read committed';
-- ALTER ROLE userjon SET timezone TO 'UTC';
--
-- GRANT ALL PRIVILEGES ON DATABASE test TO userjon;
--
-- \c -;
--
-- drop database test;

------------------UUID-------------------
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";