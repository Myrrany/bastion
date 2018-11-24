--Insert statements
INSERT INTO archetypes (archetype_name, archetype_description) VALUES (?, ?);

INSERT INTO crafts (craft_name, craft_description) VALUES (?, ?);

INSERT INTO craft_levels (cost, discount_cost) VALUES (?, ?);

INSERT INTO craft_archetypes (craft_id, archetype_id) VALUES (?, ?);

INSERT INTO characters (character_name, xp, archetype_id) VALUES (?, ?, ?);

INSERT INTO crafts_sets (character_id, craft_id, craft_level) VALUES (?, ?, ?);

INSERT INTO skills (skill_name, skill_description, cost, discount_cost) VALUES (?, ?, ?, ?);

INSERT INTO skill_archetypes (skill_id, archetype_id) VALUES (?, ?);

INSERT INTO skill_prerequisite_reference (child_id, parent_id) VALUES (?, ?);

INSERT INTO skillsets (character_id, skill_id) VALUES (?, ?);

INSERT INTO elements (element_name, element_description, element_type) VALUES (?, ?, ?);

INSERT INTO secondary_reference (child_id, parent_id) VALUES (?, ?);

INSERT INTO spells (spell_name, spell_description, element_id, spell_level, range, self, touch) VALUES (?, ?, ?, ?, ?, ?, ?);

INSERT INTO spell_levels (spell_level, casting_time, cooldown, cost, discount_cost) VALUES (?, ?, ?, ?, ?);

