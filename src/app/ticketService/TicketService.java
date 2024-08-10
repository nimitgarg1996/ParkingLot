package app.ticketService;

import app.parkingService.ParkingService;
import app.parkingService.ParkingSpotEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class TicketService {
    private final ParkingService parkingService;
    private final HashMap<Long, TicketEntity> tickets;

    public TicketService(ParkingService parkingService) {
        this.parkingService = parkingService;
        this.tickets = new LinkedHashMap<>();
    }

    public Long generateTicket(int level, int row, int column) {
        ParkingSpotEntity parkingSpotEntity = parkingService.reserveParkingSpot(level, row, column);
        if(parkingSpotEntity == null) {
            return null;
        }
        //2 phase booking
        try {
            TicketEntity ticketEntity = new TicketEntity(parkingSpotEntity);
            parkingService.bookParkingSpot(parkingSpotEntity, ticketEntity);
            tickets.putIfAbsent(ticketEntity.getId(), ticketEntity);
            return ticketEntity.getId();
        } catch (Exception e) {
            parkingService.releaseParkingSpot(parkingSpotEntity);
        }
        return null;
    }

    public TicketEntity getTicket(long id) {
       return tickets.get(id);
    }
}
