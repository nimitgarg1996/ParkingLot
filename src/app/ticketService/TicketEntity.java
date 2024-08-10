package app.ticketService;

import app.parkingService.ParkingSpotEntity;

public class TicketEntity {
    private final long id;
    private final ParkingSpotEntity parkingSpot;
    private final long checkInTime;
    private long checkOutTime;
    private double tariff;


    public TicketEntity(ParkingSpotEntity parkingSpot) {
        this.id = TicketIdGenerator.getInstance().getId();
        this.parkingSpot = parkingSpot;
        this.checkInTime = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public ParkingSpotEntity getParkingSpot() {
        return parkingSpot;
    }

    public long getCheckInTime() {
        return checkInTime;
    }

    public long getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(long checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public double getTariff() {
        return tariff;
    }

    public void setTariff(double tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", parkingSpot=" + parkingSpot +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", tarrif=" + tariff +
                '}';
    }
}
