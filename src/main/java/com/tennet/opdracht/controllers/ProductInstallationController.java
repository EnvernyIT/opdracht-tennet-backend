package com.tennet.opdracht.controllers;

import com.tennet.opdracht.VO.ProductInstallationVO;
import com.tennet.opdracht.entities.ContactPerson;
import com.tennet.opdracht.entities.ProductInstallation;
import com.tennet.opdracht.entities.Response;
import com.tennet.opdracht.services.ContactPersonService;
import com.tennet.opdracht.services.ProductInstallationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/productInstallations")
@RequiredArgsConstructor
public class ProductInstallationController {

    private final ProductInstallationService productInstallationService;
    private final ContactPersonService contactPersonService;

    @GetMapping("/list")
    public ResponseEntity<Response> getProductionInstallations() throws InterruptedException {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("productionInstallations", productInstallationService.list(30)))
                        .message("Production Installations retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveProductionInstallation(@RequestBody ProductInstallationVO vo) {
        if (vo.getOutputPower() >= 0.0001 && vo.getOutputPower().doubleValue() <= 999999) {
            ContactPerson contactPerson = contactPersonService.get(vo.getContactPersonId());
            if (contactPerson != null) {
                ProductInstallation productInstallation = new ProductInstallation(null, vo.getName(), contactPerson, vo.getOutputPower());
                return ResponseEntity.ok(
                        Response.builder()
                                .timeStamp(now())
                                .data(Map.of("productionInstallation", productInstallationService.create(productInstallation)))
                                .message("Production Installation: " + productInstallation.getName() + " created")
                                .status(CREATED)
                                .statusCode(CREATED.value())
                                .build()
                );
            } else {
                return ResponseEntity.ok(
                        Response.builder()
                                .timeStamp(now())
                                .data(null)
                                .message("Contact Person with ID: " + vo.getContactPersonId() + " doesn't exist. Cannot save entity!")
                                .status(OK)
                                .statusCode(OK.value())
                                .build()
                );
            }
        } else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(null)
                            .message(vo.getOutputPower() <= 0.0001 ? "Output power of: " + vo.getOutputPower() + " is too low. Cannot save entity!" : "Output power of: " + vo.getOutputPower() + " is too high. Cannot save entity!")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getProductionInstallation(@PathVariable("id") Long id) {
        ProductInstallation v = productInstallationService.get(id);
        if (v != null)
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("productionInstallation", productInstallationService.get(id)))
                            .message("Production Installation by ID: " + id.toString() + " retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(null)
                            .message("Production Installation by ID: " + id.toString() + " doesn't exist")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProductInstallation(@PathVariable("id") Long id) {
        ProductInstallation v = productInstallationService.get(id);
        if (v != null)
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("deleted", productInstallationService.delete(id)))
                            .message("Production Installation by ID: " + id.toString() + " deleted")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(null)
                            .message("Production Installation by ID: " + id.toString() + " does not exist")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<Response> getProductionInstallationByName(@PathVariable("name") String name) {
        List<ProductInstallation> v = productInstallationService.getByName(name);
        if (v != null)
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("productionInstallations", productInstallationService.getByName(name)))
                            .message("Production Installations by name: " + name + " retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(null)
                            .message("Production Installations by name: " + name + " do not exist")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
    }

    @GetMapping("/get/outputPower/{outputPower}")
    public ResponseEntity<Response> getProductionInstallationByOutputPower(@PathVariable("outputPower") BigDecimal outputPower) {
        List<ProductInstallation> v = productInstallationService.getByOutputPower(outputPower.doubleValue());
        if (v != null)
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("productionInstallations", productInstallationService.getByOutputPower(outputPower.doubleValue())))
                            .message("Production Installations with outputPower: " + outputPower.toString() + " retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(null)
                            .message("Production Installations by outputPower: " + outputPower.toString() + " do not exist")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
    }

    @GetMapping("/get/contactPerson/id/{id}")
    public ResponseEntity<Response> getProductionInstallationByContactPersonId(@PathVariable("id") Long id) {
        List<ProductInstallation> v = productInstallationService.getByContactPersonId(id);
        if (v != null)
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("productionInstallations", productInstallationService.getByContactPersonId(id)))
                            .message("Production Installations by outputPower: " + id.toString() + " retrieved")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );
        else
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(null)
                            .message("Production Installations by outputPower: " + id.toString() + " do not exist")
                            .status(OK)
                            .statusCode(OK.value())
                            .build()
            );

    }

    @GetMapping("filter/name/{name}/outputPower/{outputPower}")
    public ResponseEntity<Response> getProductionInstallationsByNameAndOrOutputPower(@PathVariable("name") String name, @PathVariable("outputPower") Double outputPower) throws InterruptedException {
        List<ProductInstallation> v = productInstallationService.getProductionInstallationsByNameAndOrOutputPower(name, outputPower);
        if (v != null) {
            if (!v.isEmpty()) {
                return ResponseEntity.ok(
                        Response.builder()
                                .timeStamp(now())
                                .data(Map.of("productionInstallations", productInstallationService.getProductionInstallationsByNameAndOrOutputPower(name, outputPower)))
                                .message("List of Production Installations by name: " + name + " & outputPower: " + outputPower.toString() + " retrieved")
                                .status(OK)
                                .statusCode(OK.value())
                                .build()
                );
            } else {
                return ResponseEntity.ok(
                        Response.builder()
                                .timeStamp(now())
                                .data(null)
                                .message("List of Production Installations by name: " + name + " & outputPower: " + outputPower.toString() + " is empty")
                                .status(OK)
                                .statusCode(OK.value())
                                .build()
                );
            }
        }
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(null)
                        .message("List of Production Installations by name: " + name + " & outputPower: " + outputPower.toString() + " is null")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

}
