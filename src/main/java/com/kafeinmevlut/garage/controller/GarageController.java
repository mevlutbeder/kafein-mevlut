package com.kafeinmevlut.garage.controller;

import com.kafeinmevlut.garage.dto.request.VehicleRequest;
import com.kafeinmevlut.garage.services.GarageService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mevlutbeder
 * @created 01/01/2023 04:55
 */
@Slf4j
@RestController
@Api(value = "All details about the Garage Controller.")
public class GarageController extends ApiGarageController {

    private final GarageService service;

    public GarageController(GarageService service) {
        this.service = service;
    }

    /**
     * @param req {"color": "Black","plate": "34-SO-1988", "vehicleType": "Truck" }
     * @return queue
     */
    @PostMapping("/park")
    public ResponseEntity<String> parkIt(@Valid @RequestBody VehicleRequest req) {
        log.info("Parking starting..");
        return ResponseEntity.ok().headers(new HttpHeaders()).body(service.parkIt(req));
    }

    /**
     * @return List<String> status
     */
    @GetMapping("/status")
    public ResponseEntity<List<String>> getStatus() {
        log.info("Get status starting..");
        return ResponseEntity.ok().headers(new HttpHeaders()).body(service.getStatus());
    }

    /**
     * @param req { "queueNo": 1}
     * @return String leave status
     */
    @PostMapping("/leave")
    public ResponseEntity<String> leavePark(@Valid @RequestBody VehicleRequest req) {
        log.info("Leave parking starting..");
        return ResponseEntity.ok().headers(new HttpHeaders()).body(service.leavePark(req.getQueueNo()));
    }


}
