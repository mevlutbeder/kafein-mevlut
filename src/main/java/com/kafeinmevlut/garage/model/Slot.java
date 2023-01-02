package com.kafeinmevlut.garage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author mevlutbeder
 * @created 26/12/2022 00:20
 */
@Data
@Builder
@ApiModel(description = "All details about a parking bay. ")
public class Slot {

    @ApiModelProperty(name = "Slot Number")
    private Integer slotNumber;

    @ApiModelProperty(name = "Queue")
    private Queue queue;

}
