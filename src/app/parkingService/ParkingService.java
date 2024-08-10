package app.parkingService;

import app.ticketService.TicketEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ParkingService {
    private final ParkingSpotEntity[][][] parkingSpotEntity;
//    private final HashMap<ParkingSpotEntity, TicketEntity> parkingSpotEntityMap = new HashMap<>();

    public ParkingService(int levels, int rows, int columns) {
        parkingSpotEntity = new ParkingSpotEntity[levels][rows][columns];
        for (int i = 0; i < levels; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < columns; k++) {
                    parkingSpotEntity[i][j][k] = new ParkingSpotEntity(i, j, k);
                }
            }
        }
    }

    public ParkingSpotEntity reserveParkingSpot(int level, int row, int column) {
        ParkingSpotEntity parkingSpotEntity = findNearestParkingSpot(level, row, column);
        if(parkingSpotEntity == null) {
            return null;
        }
        parkingSpotEntity.setStatus(ParkingSpotStatus.RESERVED);
        return parkingSpotEntity;
    }

    public void bookParkingSpot(ParkingSpotEntity parkingSpotEntity, TicketEntity ticket) {
        parkingSpotEntity.setStatus(ParkingSpotStatus.OCCUPIED);
        //parkingSpotEntityMap.put(parkingSpotEntity, ticket);
    }

    public void releaseParkingSpot(ParkingSpotEntity parkingSpotEntity) {
        parkingSpotEntity.setStatus(ParkingSpotStatus.AVAILABLE);
    }

    private ParkingSpotEntity findNearestParkingSpot(int level, int row, int column) {
        Queue<ParkingSpotEntity> queue = new LinkedList<>();
        queue.add(parkingSpotEntity[level][row][column]);
        int maxLevel = parkingSpotEntity.length;
        int maxRow = parkingSpotEntity[0].length;
        int maxColumn = parkingSpotEntity[0][0].length;

        boolean[][][] visited = new boolean[maxLevel][maxRow][maxColumn];
        visited[level][row][column] = true;

        while(!queue.isEmpty()) {
            ParkingSpotEntity parkingSpot = queue.remove();
            if(parkingSpot.getStatus() == ParkingSpotStatus.AVAILABLE) {
                return parkingSpot;
            }
            // Explore adjacent cells
            if(parkingSpot.getRow()+1 >=0 && parkingSpot.getRow()+1 < maxRow && !visited[parkingSpot.getLevel()][parkingSpot.getRow()+1][parkingSpot.getColumn()]) {
                queue.add(parkingSpotEntity[parkingSpot.getLevel()][parkingSpot.getRow()+1][parkingSpot.getColumn()]);
                visited[parkingSpot.getLevel()][parkingSpot.getRow()+1][parkingSpot.getColumn()] = true;
            }

            if(parkingSpot.getRow()-1 >=0 && parkingSpot.getRow()-1 < maxRow && !visited[parkingSpot.getLevel()][parkingSpot.getRow()-1][parkingSpot.getColumn()]) {
                queue.add(parkingSpotEntity[parkingSpot.getLevel()][parkingSpot.getRow()-1][parkingSpot.getColumn()]);
                visited[parkingSpot.getLevel()][parkingSpot.getRow()-1][parkingSpot.getColumn()] = true;
            }

            if(parkingSpot.getColumn()+1 >=0 && parkingSpot.getColumn()+1 < maxColumn && !visited[parkingSpot.getLevel()][parkingSpot.getRow()][parkingSpot.getColumn()+1]) {
                queue.add(parkingSpotEntity[parkingSpot.getLevel()][parkingSpot.getRow()][parkingSpot.getColumn()+1]);
                visited[parkingSpot.getLevel()][parkingSpot.getRow()][parkingSpot.getColumn()+1] = true;
            }

            if(parkingSpot.getColumn()-1 >=0 && parkingSpot.getColumn()-1 < maxColumn && !visited[parkingSpot.getLevel()][parkingSpot.getRow()][parkingSpot.getColumn()-1]) {
                queue.add(parkingSpotEntity[parkingSpot.getLevel()][parkingSpot.getRow()][parkingSpot.getColumn()-1]);
                visited[parkingSpot.getLevel()][parkingSpot.getRow()][parkingSpot.getColumn()-1] = true;
            }

            if(parkingSpot.getLevel()+1 >=0 && parkingSpot.getLevel()+1 < maxLevel && !visited[parkingSpot.getLevel()+1][parkingSpot.getRow()][parkingSpot.getColumn()]) {
                queue.add(parkingSpotEntity[parkingSpot.getLevel()+1][parkingSpot.getRow()][parkingSpot.getColumn()]);
                visited[parkingSpot.getLevel()+1][parkingSpot.getRow()][parkingSpot.getColumn()] = true;
            }

            if(parkingSpot.getLevel()-1 >=0 && parkingSpot.getLevel()-1 < maxLevel && !visited[parkingSpot.getLevel()-1][parkingSpot.getRow()][parkingSpot.getColumn()]) {
                queue.add(parkingSpotEntity[parkingSpot.getLevel()-1][parkingSpot.getRow()][parkingSpot.getColumn()]);
                visited[parkingSpot.getLevel()-1][parkingSpot.getRow()][parkingSpot.getColumn()] = true;
            }
        }
        return null;
    }

}
