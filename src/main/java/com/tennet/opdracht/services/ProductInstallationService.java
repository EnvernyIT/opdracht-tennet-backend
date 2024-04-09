package com.tennet.opdracht.services;

import com.tennet.opdracht.entities.ProductInstallation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ProductInstallationService {

    ProductInstallation create(ProductInstallation productInstallation);
    ProductInstallation get(Long id);
    List<ProductInstallation> getByName(String name);
    List<ProductInstallation> getByOutputPower(Double outputPower);
    List<ProductInstallation> list(int limit);
    Boolean delete(Long id);
    List<ProductInstallation> getByContactPersonId(Long id);

    List<ProductInstallation> getProductionInstallationsByNameAndOrOutputPower(String name, Double outputPower);

}
