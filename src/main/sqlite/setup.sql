DROP TABLE skill_prerequisite_reference, skillsets, skill_archetypes, skills, crafts_sets, craft_archetypes, craft_levels, crafts, secondary_reference, elements, spell_levels, characters, spells, races, archetypes, spellbook CASCADE;

CREATE TABLE archetypes (
  archetype_id          SERIAL PRIMARY KEY,
  archetype_name        TEXT UNIQUE NOT NULL,
  archetype_description TEXT
);

CREATE TABLE races (
  race_id          SERIAL PRIMARY KEY,
  race_name        TEXT UNIQUE NOT NULL,
  race_description TEXT
);

CREATE TABLE characters (
  character_id   SERIAL PRIMARY KEY,
  character_name TEXT    NOT NULL,
  xp             INT,
  archetype_id   INTEGER NOT NULL,
  race_id        INTEGER NOT NULL,
  FOREIGN KEY (archetype_id) REFERENCES archetypes (archetype_id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT,
  FOREIGN KEY (race_id) REFERENCES races (race_id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

CREATE TABLE spell_levels (
  spell_level   SERIAL PRIMARY KEY,
  casting_time  INT NOT NULL,
  cooldown      INT NOT NULL,
  cost          INT NOT NULL,
  discount_cost INT NOT NULL
);

CREATE TABLE elements (
  element_id                   SERIAL PRIMARY KEY,
  element_name                 TEXT UNIQUE NOT NULL,
  element_description          TEXT,
  element_type                 TEXT        NOT NULL,
  element_max_level            INT,
  prerequisite_spells          INT,
  prerequisite_one             INT,
  prerequisite_two             INT,
  prerequisite_race            INT,
  prerequisite_elements_number INT
);

CREATE TABLE secondary_reference (
  child_id  INTEGER,
  parent_id INTEGER,
  PRIMARY KEY (child_id, parent_id),
  FOREIGN KEY (child_id) REFERENCES elements (element_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY (parent_id) REFERENCES elements (element_id)
  ON UPDATE CASCADE
  ON DELETE SET NULL
);

CREATE TABLE spells (
  spell_id          SERIAL PRIMARY KEY,
  spell_name        TEXT NOT NULL,
  spell_description TEXT,
  element_id        INT  NOT NULL,
  spell_level       INT  NOT NULL,
  range             INT,
  self              BOOLEAN,
  touch             BOOLEAN,
  FOREIGN KEY (element_id) REFERENCES elements (element_id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT,
  FOREIGN KEY (spell_level) REFERENCES spell_levels (spell_level)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

CREATE TABLE spellbook (
  character_id INTEGER,
  spell_id     INTEGER,
  PRIMARY KEY (character_id, spell_id),
  FOREIGN KEY (character_id) REFERENCES characters (character_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY (spell_id) REFERENCES spells (spell_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE
);


CREATE TABLE crafts (
  craft_id          SERIAL PRIMARY KEY,
  craft_name        TEXT UNIQUE NOT NULL,
  craft_description TEXT
);

CREATE TABLE craft_levels (
  craft_level   SERIAL PRIMARY KEY,
  cost          INT NOT NULL,
  discount_cost INT NOT NULL
);

CREATE TABLE craft_archetypes (
  craft_id     INT,
  archetype_id INT,
  PRIMARY KEY (craft_id, archetype_id),
  FOREIGN KEY (craft_id) REFERENCES crafts (craft_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY (archetype_id) REFERENCES archetypes (archetype_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE
);

CREATE TABLE crafts_sets (
  character_id INT,
  craft_id     INT,
  craft_level  INT,
  PRIMARY KEY (character_id, craft_id),
  FOREIGN KEY (character_id) REFERENCES characters (character_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY (craft_id) REFERENCES crafts (craft_id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT,
  FOREIGN KEY (craft_level) REFERENCES craft_levels (craft_level)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

CREATE TABLE skills (
  skill_id          SERIAL PRIMARY KEY,
  skill_name        TEXT NOT NULL,
  skill_description TEXT,
  cost              INT  NOT NULL,
  discount_cost     INT  NOT NULL
);

CREATE TABLE skill_archetypes (
  skill_id     INT,
  archetype_id INT,
  PRIMARY KEY (skill_id, archetype_id),
  FOREIGN KEY (skill_id) REFERENCES skills (skill_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY (archetype_id) REFERENCES archetypes (archetype_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE
);

CREATE TABLE skillsets (
  character_id INT,
  skill_id     INT,
  PRIMARY KEY (character_id, skill_id),
  FOREIGN KEY (character_id) REFERENCES characters (character_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY (skill_id) REFERENCES skills (skill_id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

CREATE TABLE skill_prerequisite_reference (
  child_id  INTEGER,
  parent_id INTEGER,
  PRIMARY KEY (child_id, parent_id),
  FOREIGN KEY (child_id) REFERENCES skills (skill_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY (parent_id) REFERENCES skills (skill_id)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO bastion;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO bastion;