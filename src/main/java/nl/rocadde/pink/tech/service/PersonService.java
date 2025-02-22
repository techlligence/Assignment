package nl.rocadde.pink.tech.service;

import nl.rocadde.pink.tech.entity.Person;
import java.util.List;

public interface PersonService {
    // Requirement 3
    List<Person> getPartnerWithThreeKidsAndOneChildBelow18();
    // Requirement 4
    Person insertPerson(Person person);
    Person updatePerson(Long id, Person person);
    void deletePerson(Long id);
    String convertToBase64Csv(List<Person> people);
    // Requirement 5
    List<Person> getSortedPeople(String sortBy);
}
