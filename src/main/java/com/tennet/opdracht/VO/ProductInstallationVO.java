package com.tennet.opdracht.VO;

import lombok.Data;

@Data
public class ProductInstallationVO {
    private Long id;
    private String name;
    private Long contactPersonId;
    private Double outputPower;
}
