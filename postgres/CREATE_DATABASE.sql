CREATE DATABASE nation;
GRANT ALL PRIVILEGES ON DATABASE nation TO root;
--SELECT datname FROM pg_database WHERE datname='nation'';
--IF datname='nation'
--    THEN CREATE DATABASE nation PASSWORD 'root';
--END IF;