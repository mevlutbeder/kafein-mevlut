package com.kafeinmevlut.garage.model;

import com.kafeinmevlut.garage.enums.VehicleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author mevlutbeder
 * @created 25/12/2022 20:17
 */
@Data
@Builder
@ApiModel(description = "All details about the vehicle. ")
public class Vehicle {

    @ApiModelProperty(name = "Color", example = "Black")
    private String color;

    @ApiModelProperty(name = "Plaque", example = "34-SO-1988")
    private String plaque;

    @ApiModelProperty(name = "Vehicle Type", example = "Car")
    private VehicleType vehicleType;

}
