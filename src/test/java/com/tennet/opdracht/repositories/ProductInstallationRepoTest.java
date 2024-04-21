package com.tennet.opdracht.repositories;

import com.tennet.opdracht.entities.ContactPerson;
import com.tennet.opdracht.entities.ProductInstallation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductInstallationRepoTest {

    @Autowired
    private ProductInstallationRepo productInstallationRepo;

    @Autowired
    private ContactPersonRepo contactPersonRepo;

    ContactPerson contactPerson;

    @BeforeEach
    public void init() {
        ContactPerson saveContactPerson = ContactPerson.builder()
                .name("Ali Baba")
                .city("Almere")
                .zipCode("09876TT")
                .number("99E")
                .build();
        contactPersonRepo.save(saveContactPerson);

        contactPerson = contactPersonRepo.findById(saveContactPerson.getId()).get();
    }

    @Test
    public void ProductInstallationRepo_SaveAll_ReturnSavedProductInstallation() {

        //Arrange
        ProductInstallation productInstallation = ProductInstallation.builder()
                .name("Local Install")
                .contactPerson(contactPerson)
                .outputPower(299.99)
                .build();

        //Act
        ProductInstallation savedProductInstallation= productInstallationRepo.save(productInstallation);

        //Assert
        Assertions.assertThat(savedProductInstallation).isNotNull();
        Assertions.assertThat(savedProductInstallation.getId()).isGreaterThan(0);
    }

    @Test
    public void ProductInstallationRepo_GetAll_ReturnMoreThanOneProductInstallation() {
        ProductInstallation productInstallation = ProductInstallation.builder()
                .name("Local Install")
                .contactPerson(contactPerson)
                .outputPower(299.99)
                .build();
        ProductInstallation productInstallation1 = ProductInstallation.builder()
                .name("Global Install")
                .contactPerson(contactPerson)
                .outputPower(200.19)
                .build();

        productInstallationRepo.save(productInstallation);
        productInstallationRepo.save(productInstallation1);

        List<ProductInstallation> list = productInstallationRepo.findAll();

        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list.size()).isEqualTo(4);
    }

    @Test
    public void ProductInstallationRepo_FindById_ReturnSavedProductInstallation() {

        //Arrange
        ProductInstallation productInstallation = ProductInstallation.builder()
                .name("Local Install")
                .contactPerson(contactPerson)
                .outputPower(299.99)
                .build();

        //Act
        productInstallationRepo.save(productInstallation);

        ProductInstallation returnProductInstallation = productInstallationRepo.findById(productInstallation.getId()).get();

        //Assert
        Assertions.assertThat(returnProductInstallation).isNotNull();
    }

    @Test
    public void ProductInstallationRepo_UpdateProductInstallation_ReturnSavedProductInstallation() {

        //Arrange
        ProductInstallation productInstallation = ProductInstallation.builder()
                .name("Local Install")
                .contactPerson(contactPerson)
                .outputPower(299.99)
                .build();

        //Act
        productInstallationRepo.save(productInstallation);

        ProductInstallation returnProductInstallation = productInstallationRepo.findById(productInstallation.getId()).get();
        returnProductInstallation.setName("String Install");
        returnProductInstallation.setOutputPower(119.99);

        ProductInstallation updatedProductInstallation = productInstallationRepo.save(productInstallation);

        //Assert
        Assertions.assertThat(updatedProductInstallation.getName()).isNotNull();
        Assertions.assertThat(updatedProductInstallation.getName()).isEqualTo("String Install");
        Assertions.assertThat(updatedProductInstallation.getOutputPower()).isNotNull();
        Assertions.assertThat(updatedProductInstallation.getOutputPower()).isEqualTo(119.99);
    }

    @Test
    public void ProductInstallationRepo_DeleteProductInstallationById_ReturnProductInstallationIsNull() {
        //Arrange
        ProductInstallation productInstallation = ProductInstallation.builder()
                .name("Local Install")
                .contactPerson(contactPerson)
                .outputPower(299.99)
                .build();

        //Act
        productInstallationRepo.save(productInstallation);

        //Act
        productInstallationRepo.deleteById(productInstallation.getId());
        Optional<ProductInstallation> productInstallationReturn = productInstallationRepo.findById(productInstallation.getId());

        //Assert
        Assertions.assertThat(productInstallationReturn).isEmpty();
    }

}
