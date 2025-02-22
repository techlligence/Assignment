INSERT INTO Person (id, name, birthDate) VALUES
                                             (1, 'Alice Johnson', '1980-05-15'),
                                             (2, 'Bob Johnson', '1978-08-20'),
                                             (3, 'Charlie Johnson', '2005-07-10'),
                                             (4, 'Diana Smith', '2010-09-25'),
                                             (5, 'Edward Brown', '1985-02-18'),
                                             (6, 'Fiona White', '1990-12-30'),

-- New couple
                                             (7, 'George Wilson', '1982-03-14'),
                                             (8, 'Hannah Wilson', '1984-07-22'),

-- Their three children
                                             (9, 'Isla Wilson', '2000-05-10'),
                                             (10, 'Jack Wilson', '2000-08-03'),
                                             (11, 'Olivia Wilson', '2018-11-19');

-- Update parent-child relationships
UPDATE Person SET parent1_id = 1, parent2_id = 2 WHERE id = 3; -- Charlie's parents: Alice & Bob
UPDATE Person SET parent1_id = 1, parent2_id = 2 WHERE id = 4; -- Diana's parents: Alice & Bob
UPDATE Person SET parent1_id = 7, parent2_id = 8 WHERE id = 9; -- Isla's parents: George & Hannah
UPDATE Person SET parent1_id = 7, parent2_id = 8 WHERE id = 10; -- Jack's parents: George & Hannah
UPDATE Person SET parent1_id = 7, parent2_id = 8 WHERE id = 11; -- Olivia's parents: George & Hannah

-- Insert data into the Person_Children table
INSERT INTO Person_Children (parent_id, child_id) VALUES
                                                      (1, 3), -- Alice -> Charlie
                                                      (2, 3), -- Bob -> Charlie
                                                      (1, 4), -- Alice -> Diana
                                                      (2, 4), -- Bob -> Diana

                                                      (7, 9), -- George -> Isla
                                                      (8, 9), -- Hannah -> Isla
                                                      (7, 10), -- George -> Jack
                                                      (8, 10), -- Hannah -> Jack
                                                      (7, 11), -- George -> Olivia
                                                      (8, 11); -- Hannah -> Olivia

-- Update partner relationships
UPDATE Person SET partner_id = 2 WHERE id = 1; -- Alice and Bob are partners
UPDATE Person SET partner_id = 1 WHERE id = 2; -- Bob and Alice are partners
UPDATE Person SET partner_id = 6 WHERE id = 5; -- Edward and Fiona are partners
UPDATE Person SET partner_id = 5 WHERE id = 6; -- Fiona and Edward are partners
UPDATE Person SET partner_id = 8 WHERE id = 7; -- George and Hannah are partners
UPDATE Person SET partner_id = 7 WHERE id = 8; -- Hannah and George are partners