package com.kafeinmevlut.garage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


/**
 * @author mevlutbeder
 * @created 26/12/2022 00:18
 */
@Data
@Builder
@ApiModel(description="All details about the queues. ")
public class Queue {

  @ApiModelProperty(name = "Queue Number")
  private Integer queueNumber;

  @ApiModelProperty(name = "Vehicle")
  private Vehicle vehicle;

}
