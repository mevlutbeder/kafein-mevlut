package com.kafeinmevlut.garage.services.impl;

import com.kafeinmevlut.garage.dto.request.VehicleRequest;
import com.kafeinmevlut.garage.model.Slot;
import com.kafeinmevlut.garage.model.Queue;
import com.kafeinmevlut.garage.model.Vehicle;
import com.kafeinmevlut.garage.services.GarageService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author mevlutbeder
 * @created 25/12/2022 21:27
 */
@Slf4j
@Service
public class GarageServiceImpl implements GarageService {

    public static Integer queueNo = 0;
    public static List<Slot> slots = null;

    @PostConstruct
    public void init() {
        log.info("initialize creating slots list.");
        if (slots == null) {
            slots = new ArrayList<>();
            IntStream.range(1, 11).forEach(i -> slots.add(Slot.builder().slotNumber(i).queue(null).build()));
            log.info("Slots list created and size : {}", slots.size());
        }
    }

    @Override
    public String parkIt(VehicleRequest req) {

        List<Slot> emptySlots = slots.stream()
                .filter(slot -> slot.getQueue() == null)
                .collect(Collectors.toList());
        if (emptySlots.size() < req.getVehicleType().getValue()) {
            log.info("Garage is full.");
            return "Garage is full.";
        }
        if (emptySlots.size() == 10) {
            int slotCount = 0;
            Queue newQueue = Queue.builder()
                    .queueNumber(queueNo += 1)
                    .vehicle(
                            Vehicle.builder()
                                    .plaque(req.getPlaque())
                                    .color(req.getColor())
                                    .vehicleType(req.getVehicleType())
                                    .build())
                    .build();

            int i = 0;
            while (i < req.getVehicleType().getValue()) {
                slots.get(i).setQueue(newQueue);
                slotCount += 1;
                i++;
            }
            log.info("Allocated " + slotCount + (slotCount == 1 ? " slot." : " slots."));
            return "Allocated " + slotCount + (slotCount == 1 ? " slot." : " slots.");
        } else {
            List<Slot> slotAvailablelist = findAvailableSlot(req, emptySlots);
            if (slotAvailablelist.isEmpty()) {
                log.info("Garage is full.");
                return "Garage is full.";
            } else {
                return parkVehicleToGarage(req, slotAvailablelist);
            }
        }
    }

    private String parkVehicleToGarage(VehicleRequest req, List<Slot> availableSlotList) {
        Integer countSlotRequired = req.getVehicleType().getValue();
        Queue newQueue = Queue.builder()
                .queueNumber(queueNo += 1)
                .vehicle(
                        Vehicle.builder()
                                .plaque(req.getPlaque())
                                .color(req.getColor())
                                .vehicleType(req.getVehicleType())
                                .build())
                .build();
        int slotNo;
        if (availableSlotList.size() == 1) {
            slotNo = availableSlotList.get(0).getSlotNumber();
        } else {
            slotNo = findRequiredSlot(availableSlotList).getSlotNumber();
        }
        countSlotRequired -= 1;
        int allocatedSlotCount = 0;
        for (Slot slot : slots) {
            if (slot.getSlotNumber().equals(slotNo) && slot.getQueue() == null) {
                slot.setQueue(newQueue);
                allocatedSlotCount += 1;
                if (countSlotRequired != 0) {
                    slotNo += 1;
                    countSlotRequired -= 1;
                }
            }
        }
        log.info(new StringBuilder().append("Allocated ").append(allocatedSlotCount).append(allocatedSlotCount == 1 ? " slot." : " slots.").toString());
        return new StringBuilder().append("Allocated ").append(allocatedSlotCount).append(allocatedSlotCount == 1 ? " slot." : " slots.").toString();
    }

    private Slot findRequiredSlot(List<Slot> availableSlots) { // TODO : The process of determining the target slot according to the available slots
        for (Slot availableSlot : availableSlots) {
            List<Slot> slots = availableSlots.stream()
                    .filter(slot -> slot.getSlotNumber().equals(availableSlot.getSlotNumber() + 1))
                    .toList();
            if (slots.size() > 0) {
                return slots.get(0);
            }
        }
        return availableSlots.get(0);
    }

    private List<Slot> findAvailableSlot(VehicleRequest req, List<Slot> emptySlots) { //TODO :It gives the starting slots available according to the number of slots to be occupied.
        List<Slot> availableSlots = new ArrayList<>();
        List<Slot> newAvailableSlots = emptySlots;

        for (Slot emptySlot : emptySlots) {
            Integer slotNumber = emptySlot.getSlotNumber();
            Integer availableSlotCount = 0;

            for (Slot availableSlot : newAvailableSlots) {
                if (availableSlot.getSlotNumber().equals(slotNumber)) {
                    availableSlotCount += 1;
                    if (availableSlotCount.equals(req.getVehicleType().getValue())) {
                        Slot currentSlot = emptySlots.stream().filter(slot -> slot.getSlotNumber().equals(availableSlot.getSlotNumber() - (req.getVehicleType().getValue() - 1))).findFirst().get();
                        availableSlots.add(currentSlot);
                        availableSlotCount = 0;
                    }
                }
                slotNumber += 1;
            }
            newAvailableSlots = newAvailableSlots.stream().filter(
                            slot -> !slot.getSlotNumber()
                                    .equals(emptySlot.getSlotNumber()))
                    .distinct()
                    .collect(Collectors.toList());
        }

        availableSlots = availableSlots
                .stream().distinct().sorted(Comparator.comparingInt(Slot::getSlotNumber)).collect(Collectors.toList());
        return availableSlots;
    }

    @Override
    public List<String> getStatus() {
        List<Slot> parkedVehicles = slots.stream()
                .filter(slot -> slot.getQueue() != null)
                .toList();

        List<String> statusVehicles = new ArrayList<>();
        for (Slot parkedVehicle : parkedVehicles) {
            List<Slot> parkedVehicleSlots = parkedVehicles.stream()
                    .filter(slot -> slot.getQueue().getQueueNumber().equals(parkedVehicle.getQueue().getQueueNumber()))
                    .toList();

            StringBuilder statusSlots = new StringBuilder("[");

            for (Slot parkedVehicleSlot : parkedVehicleSlots) {
                statusSlots.append(parkedVehicleSlot.getSlotNumber().toString()).append(",");
            }
            statusSlots = new StringBuilder(statusSlots.substring(0, statusSlots.length() - 1) + "]");

            statusSlots = new StringBuilder(
                    new StringBuilder().append(parkedVehicle.getQueue().getVehicle().getPlaque())
                            .append(" ").append(parkedVehicle.getQueue().getVehicle().getColor())
                            .append(" ").append(statusSlots).toString()
            );

            if (!statusVehicles.contains(statusSlots.toString())) {
                statusVehicles.add(statusSlots.toString());
            }

        }
        if (statusVehicles.size() > 0) {
            return statusVehicles;
        }
        return null;
    }

    @Override
    public String leavePark(Integer queueNo) {
        String leaveParkStatus = "No one went out garage";
        for (Slot slot : slots) {
            if (slot.getQueue() != null && slot.getQueue().getQueueNumber().equals(queueNo)) {
                slot.setQueue(null);
                leaveParkStatus = "queue no : " + queueNo + " leave completed.";
            }
        }
        log.info(leaveParkStatus);
        return leaveParkStatus;
    }


}
