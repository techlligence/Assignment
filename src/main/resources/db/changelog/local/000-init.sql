CREATE TABLE Person (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        birthdate DATE NOT NULL,
                        parent1_id BIGINT,
                        parent2_id BIGINT,
                        partner_id BIGINT,
                        CONSTRAINT fk_parent1 FOREIGN KEY (parent1_id) REFERENCES Person(id),
                        CONSTRAINT fk_parent2 FOREIGN KEY (parent2_id) REFERENCES Person(id),
                        CONSTRAINT fk_partner FOREIGN KEY (partner_id) REFERENCES Person(id) ON DELETE SET NULL
);

CREATE TABLE Person_Children (
                                 parent_id BIGINT NOT NULL,
                                 child_id BIGINT NOT NULL,
                                 PRIMARY KEY (parent_id, child_id),
                                 CONSTRAINT fk_child FOREIGN KEY (child_id) REFERENCES Person(id) ON DELETE CASCADE,
                                 CONSTRAINT fk_parent FOREIGN KEY (parent_id) REFERENCES Person(id) ON DELETE CASCADE
);