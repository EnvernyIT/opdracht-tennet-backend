package com.tennet.opdracht.repositories;

import com.tennet.opdracht.entities.ContactPerson;
//import org.junit.Test;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContactPersonRepoTest {

    @Autowired
    private ContactPersonRepo contactPersonRepo;

    @Test
    public void ContactPersonRepo_SaveContactPerson_ReturnSavedContactPerson() {

        //Arrange
        ContactPerson contactPerson = ContactPerson.builder()
                .id(null)
                .name("Ali Ali")
                .city("Amersfoort")
                .number("15C")
                .zipCode("12345TT")
                .build();

        //Act
        ContactPerson savedContactPerson = contactPersonRepo.save(contactPerson);

        //Assert
        Assertions.assertThat(savedContactPerson).isNotNull();
        Assertions.assertThat(savedContactPerson.getId()).isGreaterThan(0);
    }

    @Test
    public void ContactPersonRepo_GetAllContactPersons_ReturnContactPersons() {
        //Arrange
        ContactPerson contactPerson = ContactPerson.builder()
                .id(null)
                .name("Ali Ali")
                .city("Amersfoort")
                .number("15C")
                .zipCode("12345TT")
                .build();
        ContactPerson contactPerson1 = ContactPerson.builder()
                .id(null)
                .name("Ette Ette")
                .city("Apeldoorn")
                .number("25D")
                .zipCode("432112QW")
                .build();

        // Act
         contactPersonRepo.save(contactPerson);
         contactPersonRepo.save(contactPerson1);

         List<ContactPerson> list = contactPersonRepo.findAll();

         //Assert
        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list.size()).isGreaterThanOrEqualTo(2);

    }

    @Test
    public void ContactPersonRepo_GetContactPersonsById_ReturnContactPerson() {
        //Arrange
        ContactPerson contactPerson = ContactPerson.builder()
                .id(null)
                .name("Ali Ali")
                .city("Amersfoort")
                .number("15C")
                .zipCode("12345TT")
                .build();

        contactPersonRepo.save(contactPerson);

        //Act
        ContactPerson savedContactPerson = contactPersonRepo.findById(contactPerson.getId()).get();

        //Assert
        Assertions.assertThat(savedContactPerson).isNotNull();
    }

    @Test
    public void ContactPersonRepo_GetContactPersonsByZipCode_ReturnContactPersonNotNull() {
        //Arrange
        ContactPerson contactPerson = ContactPerson.builder()
                .id(null)
                .name("Ali Ali")
                .city("Amersfoort")
                .number("15C")
                .zipCode("12345TT")
                .build();

        contactPersonRepo.save(contactPerson);

        //Act
        ContactPerson savedContactPerson = contactPersonRepo.findByZipCode(contactPerson.getZipCode()).get();

        //Assert
        Assertions.assertThat(savedContactPerson).isNotNull();
    }

    @Test
    public void ContactPersonRepo_UpdateContactPerson_ReturnContactPerson() {
        //Arrange
        ContactPerson contactPerson = ContactPerson.builder()
                .id(null)
                .name("Ali Ali")
                .city("Amersfoort")
                .number("15C")
                .zipCode("12345TT")
                .build();

        contactPersonRepo.save(contactPerson);

        //Act
        ContactPerson savedContactPerson = contactPersonRepo.findById(contactPerson.getId()).get();
        savedContactPerson.setZipCode("12345TR");
        savedContactPerson.setCity("Almelo");

        ContactPerson updatedContactPerson = contactPersonRepo.save(savedContactPerson);

        //Assert
        Assertions.assertThat(updatedContactPerson.getZipCode()).isNotNull();
        Assertions.assertThat(updatedContactPerson.getCity()).isNotNull();
    }

    @Test
    public void ContactPersonRepo_DeleteContactPersonById_ReturnContactPersonIsEmpty() {
        //Arrange
        ContactPerson contactPerson = ContactPerson.builder()
                .id(null)
                .name("Ali Ali")
                .city("Amersfoort")
                .number("15C")
                .zipCode("12345TT")
                .build();

        contactPersonRepo.save(contactPerson);

        //Act
        contactPersonRepo.deleteById(contactPerson.getId());
        Optional<ContactPerson> contactPersonReturn = contactPersonRepo.findById(contactPerson.getId());

        //Assert
        Assertions.assertThat(contactPersonReturn).isEmpty();
    }


}
