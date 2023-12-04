package com.han.ds.lab1.service;

import com.han.ds.lab1.payload.request.PersonRequest;
import com.han.ds.lab1.payload.response.PersonResponse;
import java.util.List;


public interface PersonService {
    PersonResponse getPerson(Long id);

    List<PersonResponse> getPersons();

    Long createPerson(PersonRequest request);

    PersonResponse editPerson(Long id, PersonRequest request);

    void deletePerson(Long id);
}
