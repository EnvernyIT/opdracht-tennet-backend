package com.tennet.opdracht.services;

import com.tennet.opdracht.entities.ContactPerson;

import java.util.List;

public interface ContactPersonService {

    ContactPerson create(ContactPerson contactPerson);
    ContactPerson get(Long id);
    List<ContactPerson> list(int limit);
    Boolean delete(Long id);
}
