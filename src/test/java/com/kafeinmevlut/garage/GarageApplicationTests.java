package com.kafeinmevlut.garage;

import com.kafeinmevlut.garage.dto.request.VehicleRequest;
import com.kafeinmevlut.garage.enums.VehicleType;
import com.kafeinmevlut.garage.services.GarageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GarageApplicationTests {

    @Autowired
    GarageService garageService;

    @Test
    @Order(1)
    void parkTest() {
        String response = garageService.parkIt(
                VehicleRequest.builder()
                        .vehicleType(VehicleType.Car)
                        .color("Black")
                        .plaque("34-SO-1988").build()
        );
        Assertions.assertEquals("Allocated 1 slot.", response);
    }


    @Test
    @Order(2)
    void statusTest() {
        List<String> response = garageService.getStatus();
        List<String> data = new ArrayList<>();
        data.add("34-SO-1988 Black [1]");
        Assertions.assertArrayEquals(data.toArray(), response.toArray());
    }

    @Test
    @Order(3)
    void leaveTest() {
        String response = garageService.leavePark(1);
        Assertions.assertEquals("queue no : " + 1 + " leave completed.", response);
    }

}
