package com.kafeinmevlut.garage.dto.request;

import com.kafeinmevlut.garage.enums.VehicleType;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

/**
 * @author mevlutbeder
 * @created 01/01/2023 05:48
 */
@Data
@Builder
public class VehicleRequest {

    private String plaque;
    private String color;

    @Min(value = 1)
    private Integer queueNo;

    private VehicleType vehicleType;

}
