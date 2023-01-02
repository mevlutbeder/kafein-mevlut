package com.kafeinmevlut.garage.services;

import com.kafeinmevlut.garage.dto.request.VehicleRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author mevlutbeder
 * @created 25/12/2022 21:27
 */
public interface GarageService {

    String parkIt(@NotNull VehicleRequest req);
    List<String> getStatus();
    String leavePark(@NotNull Integer queueNo);

}
