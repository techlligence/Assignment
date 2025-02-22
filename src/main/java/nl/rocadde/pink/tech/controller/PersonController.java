package nl.rocadde.pink.tech.controller;

import lombok.RequiredArgsConstructor;
import nl.rocadde.pink.tech.entity.Person;
import nl.rocadde.pink.tech.service.PersonService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("tech-rest")
@RequiredArgsConstructor
public class PersonController
{
    private final PersonService service;

    // Requirement 3
    // Requirement 3a
    @GetMapping("/3a")
    public List<Person> getPartnerWithThreeKidsAndOneChildBelow18() {
        return service.getPartnerWithThreeKidsAndOneChildBelow18();
    }

    // Requirement 3b
    @GetMapping("/3b")
    public String getCsv() {
        return service.convertToBase64Csv(service.getPartnerWithThreeKidsAndOneChildBelow18());
    }

    // Requirement 4
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return service.insertPerson(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return service.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        service.deletePerson(id);
    }

    // Requirement 5
    @GetMapping
    public List<Person> getPeopleSorted(@RequestParam(defaultValue = "id") String sortBy) {
        return service.getSortedPeople(sortBy);
    }
}