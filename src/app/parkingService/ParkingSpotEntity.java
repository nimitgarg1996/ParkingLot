package app.parkingService;

public class ParkingSpotEntity {
    private final int level;
    private final int row;
    private final int column;
    private ParkingSpotStatus status = ParkingSpotStatus.AVAILABLE;

    public ParkingSpotEntity(int level, int row, int column) {
        this.level = level;
        this.row = row;
        this.column = column;
    }

    public int getLevel() {
        return level;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ParkingSpotStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingSpotStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParkingSpotEntity{" +
                "level=" + level +
                ", row=" + row +
                ", column=" + column +
                ", status=" + status +
                '}';
    }
}
