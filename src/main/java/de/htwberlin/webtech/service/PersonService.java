package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.PersonEntity;
import de.htwberlin.webtech.persistence.PersonRepository;
import de.htwberlin.webtech.web.api.Person;
import de.htwberlin.webtech.web.api.PersonCreateOrUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        List<PersonEntity> persons = personRepository.findAll();
        return persons.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Person findById(Long id) {
        var personEntity = personRepository.findById(id);
        return personEntity.map(this::transformEntity).orElse(null);
    }
    public Person create(PersonCreateOrUpdateRequest request) {
        var personEntity = new PersonEntity(request.getFirstname(), request.getLastname(), request.isVaccinated());
        personRepository.save(personEntity);
        return transformEntity(personEntity);
    }

    public Person update(Long id, PersonCreateOrUpdateRequest request) {
        var personEntityOptional = personRepository.findById(id);
        if (personEntityOptional.isEmpty()) {
            return null;
        }

        var personEntity = personEntityOptional.get();
        personEntity.setFirstName(request.getFirstname());
        personEntity.setLastName(request.getLastname());
        personEntity.setVaccinated(request.isVaccinated());
        personEntity = personRepository.save(personEntity);
        return transformEntity(personEntity);
    }

    public boolean deleteById(Long id) {
        if (!personRepository.existsById(id)) {
            return false;
        }

        personRepository.deleteById(id);
        return true;
    }
    private Person transformEntity(PersonEntity personEntity) {
        return new Person(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                personEntity.getVaccinated()
        );
    }
}
