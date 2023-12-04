package com.han.ds.lab1.controller;

import com.han.ds.lab1.payload.request.PersonRequest;
import com.han.ds.lab1.payload.response.PersonResponse;
import com.han.ds.lab1.payload.response.ValidationErrorResponse;
import com.han.ds.lab1.service.PersonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Person REST API operations")
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonServiceImpl personService;

    @Autowired
    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }


    @Operation(
            summary = "Get Person by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Person for ID", content = @Content(schema = @Schema(implementation = PersonResponse.class))),
                    @ApiResponse(
                            responseCode = "404", description = "Not found Person with ID", content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public PersonResponse getPerson(@PathVariable("id") Long id) {
        return personService.getPerson(id);
    }

    @Operation(
            summary = "Get all Persons",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All Persons",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonResponse.class))))
            }
    )
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<PersonResponse> getPersons() {
        return personService.getPersons();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPerson(@Valid @RequestBody PersonRequest request) {
        Long id = personService.createPerson(request);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(id)
                        .toUri()
        ).build();
    }


    @Operation(
            summary = "Update Person by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Person for ID was updated",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonResponse.class)))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found Person for ID",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public PersonResponse editPerson(@PathVariable("id") Long id, @RequestBody PersonRequest personRequest) {
        return personService.editPerson(id, personRequest);
    }

    @Operation(
            summary = "Remove Person by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Person for ID was removed"
                    )
            }
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }
}
