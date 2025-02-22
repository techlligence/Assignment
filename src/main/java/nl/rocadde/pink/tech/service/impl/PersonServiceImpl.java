package nl.rocadde.pink.tech.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.rocadde.pink.tech.entity.Person;
import nl.rocadde.pink.tech.repository.PersonRepository;
import nl.rocadde.pink.tech.service.PersonService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;

    // Requirement 3
    @Override
    public List<Person> getPartnerWithThreeKidsAndOneChildBelow18() {
        List<Person> people = repository.findAll();
        return people.stream()
                .filter(person -> person.getPartner() != null)
                .filter(person -> person.getChildren().size() == 3)
                .filter(person -> {
                    long counterUnder18 = person.getChildren().stream()
                            .filter(child -> Period.between(child.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() < 18).count();
                    return counterUnder18 == 1;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String convertToBase64Csv(List<Person> people) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8)) {

            // CSV Header
            writer.println("ID,Name,BirthDate,PartnerID,ChildrenCount");

            // CSV Rows
            for (Person person : people) {
                writer.printf("%d,%s,%s,%d,%d%n",
                        person.getId(),
                        person.getName(),
                        person.getBirthdate(),
                        person.getPartner().getId(),
                        person.getChildren().size()
                );
            }

            writer.flush();
            byte[] csvBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(csvBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error generating CSV", e);
        }
    }

    // Requirement 4
    @Override
    public Person insertPerson(Person person) {
        return repository.save(person);
    }

    @Override
    @Transactional
    public Person updatePerson(Long id, Person updatedPerson) {
        return repository.findById(id).map(person -> {
            person.setName(updatedPerson.getName());
            person.setBirthdate(updatedPerson.getBirthdate());
            person.setPartner(updatedPerson.getPartner());
            person.setParent1(updatedPerson.getParent1());
            person.setParent2(updatedPerson.getParent2());
            return repository.save(person);
        }).orElseThrow(() -> new RuntimeException("Person with ID " + id + " not found"));
    }

    @SneakyThrows
    @Override
    public void deletePerson(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new Exception("Could not delete Person with ID: " + id);
        }
    }

    // Requirement 5
    @SneakyThrows
    public List<Person> getSortedPeople(String sortBy) {
        if (sortBy.equals("name")) {
            Sort sort = Sort.by(sortBy);
            return repository.findAll(sort);
        } else if (sortBy.equals("id")) {
            Sort sort = Sort.by(sortBy);
            return repository.findAll(sort);
        } else {
            throw new Exception("Can not sort by method given");
        }
    }
}