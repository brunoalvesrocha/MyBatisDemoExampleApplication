

DROP TABLE IF EXISTS person;

CREATE TABLE person (

  id        BIGINT SERIAL NOT NULL,
  firstname VARCHAR(255)  NOT NULL,
  lastname  VARCHAR(255)  NOT NULL,
  age       INT           NOT NULL
)
