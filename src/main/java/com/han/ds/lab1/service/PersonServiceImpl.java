package com.han.ds.lab1.service;

import com.han.ds.lab1.payload.request.PersonRequest;
import com.han.ds.lab1.payload.response.PersonResponse;
import com.han.ds.lab1.pojo.Person;
import com.han.ds.lab1.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponse getPerson(Long id) {
        return buildPersonResponse(getOne(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> getPersons() {
        return personRepository.findAll()
                .stream()
                .map(this::buildPersonResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long createPerson(PersonRequest request) {
        Person savedPerson = personRepository.save(buildPerson(request));
        return savedPerson.getId();
    }

    @Override
    @Transactional
    public PersonResponse editPerson(Long id, PersonRequest request) {
        Person person = getOne(id);
        person.setAge(request.getAge() != 0 ? request.getAge() : person.getAge());
        person.setName(request.getName());
        person.setWork(request.getWork() != null ? request.getWork() : person.getWork());
        person.setAddress(request.getAddress() != null ? request.getAddress() : person.getAddress());
        return buildPersonResponse(personRepository.save(person));
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    private PersonResponse buildPersonResponse(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .age(person.getAge())
                .address(person.getAddress())
                .work(person.getWork())
                .build();
    }

    private Person buildPerson(PersonRequest request) {
        Person person = new Person();
        person.setName(request.getName());
        person.setWork(request.getWork());
        person.setAddress(request.getAddress());
        person.setAge(request.getAge());
        return person;
    }

    private Person getOne(Long id) {
        return personRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Person with id " + id)
        );
    }
}
