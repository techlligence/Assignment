package nl.rocadde.pink.tech.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

// Requirement 1 && 2
@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date birthdate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent1_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"children", "partner", "parents"})
    private Person parent1;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent2_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"children", "partner", "parents"})
    private Person parent2;

    @ManyToMany
    @JoinTable(name = "Person_Children", joinColumns = @JoinColumn(name = "parent_id"), inverseJoinColumns = @JoinColumn(name = "child_id"))
    @JsonIgnoreProperties({"children", "partner", "parents"})
    private List<Person> children;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_partner"))
    @JsonIgnoreProperties({"children", "partner", "parents"})
    private Person partner;
}