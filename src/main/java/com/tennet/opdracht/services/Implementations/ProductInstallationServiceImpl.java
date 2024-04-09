package com.tennet.opdracht.services.Implementations;

import com.tennet.opdracht.entities.ProductInstallation;
import com.tennet.opdracht.repositories.ProductInstallationRepo;
import com.tennet.opdracht.services.ProductInstallationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ProductInstallationServiceImpl implements ProductInstallationService {

    private final ProductInstallationRepo productInstallationRepo;
    @Override
    public ProductInstallation create(ProductInstallation productInstallation) {
        log.info("Saving new product installation: {}", productInstallation.getName());
        return productInstallationRepo.save(productInstallation);
    }

    @Override
    public ProductInstallation get(Long id) {
        log.info("Fetching product installation by id: {}", id);
        return productInstallationRepo.findById(id).isPresent() ? productInstallationRepo.findById(id).get() : null;
    }

    @Override
    public List<ProductInstallation> getByName(String name) {
        log.info("Fetching product installations by name: {}", name);
        return productInstallationRepo.getByName(name);
    }

    @Override
    public List<ProductInstallation> getByOutputPower(Double outputPower) {
        log.info("Fetching product installations by name: {}", outputPower);
        return productInstallationRepo.findByOutputPower(outputPower);
    }

    @Override
    public List<ProductInstallation> list(int limit) {
        log.info("Fetching all product installations");
        return productInstallationRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting product installation by id: {}", id);
        productInstallationRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public List<ProductInstallation> getByContactPersonId(Long id) {
        log.info("Fetching all product installations by Contact Person ID: {}", id);
        return productInstallationRepo.findByContactPersonId(id);
    }

    @Override
    public List<ProductInstallation> getProductionInstallationsByNameAndOrOutputPower(String name, Double outputPower) {
        log.info("Fetching product installations by name: {} & outputPower: {}", name, outputPower);
            return productInstallationRepo.findByNameAndOutputPower(name, outputPower);
        }
}
