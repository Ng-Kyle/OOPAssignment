package main;

public enum FilePath {
    MOVIES("storedData/movies.csv"),
    SHOWTIMES("storedData/showtimes.csv"),
    ORDERS("storedData/orders.csv"),
    CONCESSION_ITEMS("storedData/availableItems.csv"),
    STOCK("storedData/stockQuantity.csv"),
    SEAT_PRICES("storedData/seatPrices.csv"),
    COUPONS("storedData/coupons.csv");

    private final String path;

    FilePath(String path) {
        this.path = path;
    }

    public String toString() {
        return path;
    }
}
