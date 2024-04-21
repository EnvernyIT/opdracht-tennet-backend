package com.tennet.opdracht.VO;

import lombok.Data;

@Data
public class ProductInstallationVO {
    private Long id;
    private String name;
    private Long contactPersonId;
    private Double outputPower;

    public ProductInstallationVO(Long id, String name, Long contactPersonId, Double outputPower) {
        this.id = id;
        this.name = name;
        this.contactPersonId = contactPersonId;
        this.outputPower = outputPower;
    }
}
