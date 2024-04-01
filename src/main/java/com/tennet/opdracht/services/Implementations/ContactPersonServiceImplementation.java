package com.tennet.opdracht.services.Implementations;

import com.tennet.opdracht.entities.ContactPerson;
import com.tennet.opdracht.repositories.ContactPersonRepo;
import com.tennet.opdracht.services.ContactPersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ContactPersonServiceImplementation implements ContactPersonService {

    private final ContactPersonRepo contactPersonRepo;
    @Override
    public ContactPerson create(ContactPerson contactPerson) {
        log.info("Saving new contact person: {}", contactPerson.getName());
        return contactPersonRepo.save(contactPerson);
    }

    @Override
    public ContactPerson get(Long id) {
        log.info("Fetching Contact person by id: {}", id);
        return contactPersonRepo.findById(id).isPresent() ? contactPersonRepo.findById(id).get() : null;
    }

    @Override
    public List<ContactPerson> list(int limit) {
        log.info("Fetching all contact persons");
        return contactPersonRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting contact person by ID: {}", id);
        contactPersonRepo.deleteById(id);
        return TRUE;
    }
}
