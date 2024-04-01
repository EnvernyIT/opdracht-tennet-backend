package com.tennet.opdracht.controllers;

import com.tennet.opdracht.entities.ContactPerson;
import com.tennet.opdracht.entities.Response;
import com.tennet.opdracht.services.ContactPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/contactPersons")
@RequiredArgsConstructor
public class ContactPersonController {

    private final ContactPersonService contactPersonService;

    @GetMapping("/list")
    public ResponseEntity<Response> getContactPersons() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("contactPersons", contactPersonService.list(30)))
                            .message("Contact Persons retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getContactPerson(@PathVariable("id") Long id) {
        ContactPerson v = contactPersonService.get(id);
        if (v != null)
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("contactPerson", contactPersonService.get(id)))
                            .message("Contact Persons by ID: " + id.toString() + " retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        else
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(null)
                            .message("Contact Persons by ID: " + id.toString() +" does not exist")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveContactPerson(@RequestBody ContactPerson contactPerson) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("contactPerson", contactPersonService.create(contactPerson)))
                        .message("Contact Person: " + contactPerson.getName() + " created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteContactPerson(@PathVariable("id") Long id) {
        ContactPerson v = contactPersonService.get(id);
        if (v != null)
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("deleted", contactPersonService.delete(id)))
                            .message("Contact Person by ID: " + id.toString() + " deleted")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(null)
                            .message("Contact Person by ID: " + id.toString() + " does not exist")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
    }

}
