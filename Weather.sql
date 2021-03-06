CREATE TABLE public."Astronomy" (
  id INTEGER NOT NULL,
  sunrise VARCHAR(100) NOT NULL,
  sunset VARCHAR(100) NOT NULL,
  CONSTRAINT Astronomy_fk FOREIGN KEY (user_id) REFERENCES "Channel" (id),
  PRIMARY KEY(id)
)
WITH (oids = false);


CREATE TABLE public."table" (
  id INTEGER NOT NULL,
  humidity VARCHAR(100) NOT NULL,
  pressure VARCHAR(100) NOT NULL,
  rising VARCHAR(1) NOT NULL,
  visibility VARCHAR(1) NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);


CREATE TABLE public."Condition" (
  id INTEGER NOT NULL,
  code CHAR(100) NOT NULL,
  date_c VARCHAR(100) NOT NULL,
  temp VARCHAR(100) NOT NULL,
  text VARCHAR(100),
  PRIMARY KEY(id)
)
WITH (oids = false);

CREATE TABLE public."Item" (
  id INTEGER NOT NULL,
  link VARCHAR(100) NOT NULL,
  condition public."Condition",
  PRIMARY KEY(id)
)
WITH (oids = false);

CREATE TABLE public."Wind" (
  id INTEGER NOT NULL,
  chill CHAR(100) NOT NULL,
  direction CHAR(100) NOT NULL,
  speed CHAR(100) NOT NULL,
  PRIMARY KEY(id)
)
WITH (oids = false);


CREATE TABLE public."Units" (
  id INTEGER NOT NULL,
  distance CHAR(100) NOT NULL,
  pressure CHAR(100) NOT NULL,
  speed CHAR(100) NOT NULL,
  temperature CHAR(100),
  PRIMARY KEY(id)
)
WITH (oids = false);

CREATE TABLE public."Channel" (
  id INTEGER NOT NULL,
  units public."Units",
  wind public."Wind",
  atmosphere public."Atmosphere",
  astronomy public."Astronomy",
  item public."Item",
  PRIMARY KEY(id)
)
WITH (oids = false);

CREATE TABLE public."Results" (
  id INTEGER NOT NULL,
  channel public."Channel",
  PRIMARY KEY(id)
)
WITH (oids = false);


CREATE TABLE public.weather (
  city CHAR(100) NOT NULL,
  count CHAR(100) NOT NULL,
  created CHAR(100) NOT NULL,
  lang CHAR(1) NOT NULL,
  results public."Results" NOT NULL,
  PRIMARY KEY(city)
)
WITH (oids = false);


ALTER TABLE public."Astronomy"
  ADD COLUMN channel_id INTEGER;

  ALTER TABLE public."Atmosphere"
  ADD COLUMN channel_id INTEGER;

  ALTER TABLE public."Units"
  ADD COLUMN channel_id INTEGER;

  ALTER TABLE public."Wind"
  ADD COLUMN channel_id INTEGER;

  ALTER TABLE public."Item"
  ADD COLUMN channel_id INTEGER;

  ALTER TABLE public."Condition"
  ADD COLUMN item_id INTEGER;

ALTER TABLE public."Results"
  ADD COLUMN weather_id CHAR(100);

  ALTER TABLE public."Channel"
  ADD COLUMN results_id INTEGER;

