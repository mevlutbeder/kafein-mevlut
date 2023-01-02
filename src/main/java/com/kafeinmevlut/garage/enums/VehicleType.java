package com.kafeinmevlut.garage.enums;


/**
 * @author mevlutbeder
 * @created 25/12/2022 20:19
 */
public enum VehicleType {
  Car("Car", 1),
  Jeep("Jeep", 2),
  Truck("Truck", 4);

  private final String name;
  private final Integer value;

  VehicleType(String name, Integer value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }
  public Integer getValue() {
    return value;
  }
}
