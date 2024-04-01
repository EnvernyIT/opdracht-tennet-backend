package com.tennet.opdracht.repositories;

import com.tennet.opdracht.entities.ProductInstallation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ProductInstallationRepo extends JpaRepository<ProductInstallation, Long> {

    @Query("select pi from ProductInstallation pi where pi.name = ?1")
    Collection<ProductInstallation> findByName(String name);

    @Query("select pi from ProductInstallation pi where pi.outputPower = ?1")
    List<ProductInstallation> findByOutputPower(Double outputPower);

    @Query("select pi from ProductInstallation pi where pi.contactPerson.id = ?1")
    List<ProductInstallation> findByContactPersonId(Long id);

    @Query("select pi from ProductInstallation pi where pi.name like %:name% AND pi.outputPower =:outputPower")
    List<ProductInstallation> findByNameAndOutputPower(@Param("name") String name, @Param("outputPower") Double outputPower);
}
