package StreamsApi.RealWorldScenarios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class RoomBooking {
    private String guestName;
    private String roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double ratePerNight;

    public RoomBooking(String guestName, String roomType, LocalDate checkIn, LocalDate checkOut, double ratePerNight) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ratePerNight = ratePerNight;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public double getTotalBookingCost() {
        return getNights() * ratePerNight;
    }
}

public class HotelBookingAnalysis {
    public static void main(String[] args) {
        List<RoomBooking> bookings = Arrays.asList(
            new RoomBooking("John Doe", "Deluxe Suite", LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 5), 200.0), // 4 nights = 800
            new RoomBooking("Jane Smith", "Standard Room", LocalDate.of(2025, 6, 2), LocalDate.of(2025, 6, 4), 100.0), // 2 nights = 200
            new RoomBooking("Alex Jones", "Deluxe Suite", LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 13), 200.0), // 3 nights = 600
            new RoomBooking("Emily Brown", "Presidential Suite", LocalDate.of(2025, 6, 15), LocalDate.of(2025, 6, 17), 500.0), // 2 nights = 1000
            new RoomBooking("Michael Scott", "Standard Room", LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 23), 100.0) // 3 nights = 300
        );

        // 1. Most popular room type (by total bookings count)
        Map.Entry<String, Long> mostPopularRoom = bookings.stream()
                .collect(Collectors.groupingBy(RoomBooking::getRoomType, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        // 2. Total revenue per room type
        Map<String, Double> revenuePerRoomType = bookings.stream()
                .collect(Collectors.groupingBy(
                        RoomBooking::getRoomType,
                        Collectors.summingDouble(RoomBooking::getTotalBookingCost)
                ));

        System.out.println("Most Popular Room Type: " + mostPopularRoom.getKey() + " (" + mostPopularRoom.getValue() + " bookings)");
        System.out.println("\nTotal Revenue per Room Type:");
        revenuePerRoomType.forEach((roomType, revenue) -> 
            System.out.printf(" - %-20s: $%.2f%n", roomType, revenue)
        );
    }
}
