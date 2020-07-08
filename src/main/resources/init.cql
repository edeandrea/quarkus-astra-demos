CREATE TABLE IF NOT EXISTS quarkus_demo_ks.creature(name text PRIMARY KEY, age int, type text);

INSERT INTO quarkus_demo_ks.creature (name, age, type) VALUES ('Cassandra', 30, 'Warrior');