package app;

import app.parkingService.ParkingService;
import app.ticketService.TicketService;

public class App {
    private static final ParkingService parkingService = new ParkingService(2, 3,4);
    private static final TicketService ticketService = new TicketService(parkingService);

    public static void main(String[] args) {
        long id = ticketService.generateTicket(0, 0, 0);
        System.out.println(ticketService.getTicket(id));
        long id2 = ticketService.generateTicket(0, 2, 0);
        System.out.println(ticketService.getTicket(id2));
        id2 = ticketService.generateTicket(0, 2, 0);
        System.out.println(ticketService.getTicket(id2));
        id2 = ticketService.generateTicket(0, 2, 0);
        System.out.println(ticketService.getTicket(id2));
        id = ticketService.generateTicket(0, 0, 0);
        System.out.println(ticketService.getTicket(id));
        id = ticketService.generateTicket(0, 0, 0);
        System.out.println(ticketService.getTicket(id));
    }
}
