package com.tennet.opdracht.repositories;

import com.tennet.opdracht.entities.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPersonRepo extends JpaRepository<ContactPerson, Long> {
}
