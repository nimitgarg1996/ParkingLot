package app;

import app.parkingService.ParkingService;
import app.ticketService.TicketEntity;

public class CheckoutService {

    private final ParkingService parkingService;

    private static final double TARIFF_PER_HOUR = 20.0;

    public CheckoutService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public TicketEntity initiateCheckout(TicketEntity ticket) {
        long currentTimestamp = System.currentTimeMillis();
        ticket.setCheckOutTime(currentTimestamp);
        long duration = (currentTimestamp - ticket.getCheckInTime()) / (1000 * 60 * 60);
        ticket.setTariff(getTariff(duration));
        parkingService.releaseParkingSpot(ticket.getParkingSpot());
        return ticket;
    }

    private double getTariff(long duration) {
        return TARIFF_PER_HOUR * duration;
    }
}
