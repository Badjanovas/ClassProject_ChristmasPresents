package SantasFactory.Gifts;

import java.util.Arrays;

public class GoodKidFactory {

    /*By using singleton creating extra goodKid. The Singleton pattern ensures that a class has only one instance and provides a global point to that instance.*/

    private static GoodKid benInstance;
    public static GoodKid createBen() {
        if (benInstance == null) {
            benInstance = new GoodKid("Ben", "Miller", 9876543210L, "789 Main St, Townsville", 12,
                    Arrays.asList(
                            new Present("potato", 10, "Fresh and organic potato", 0.5, "Brown"),
                            new Present("carrot", 17, "Farm-fresh carrot", 0.4, "Orange")
                    )
            );
            return benInstance;
        } else {
            throw new IllegalStateException("Ben already exists");
        }
    }

    private static GoodKid saraInstance;
    public static GoodKid createSara() {
        if (saraInstance == null) {
            saraInstance = new GoodKid("Sara", "Williams", 2345678901L, "123 Pine St, Villagetown", 18,
                    Arrays.asList(
                            new Present("book", 15, "Bestseller novel", 1.0, "Various"),
                            new Present("headphones", 30, "Noise-canceling headphones", 0.3, "Black")
                    )
            );
            return saraInstance;
        } else {
            throw new IllegalStateException("Sara already exists");
        }
    }

    private static GoodKid alexInstance;
    public static GoodKid createAlex() {
        if (alexInstance == null) {
            alexInstance = new GoodKid("Alex", "Davis", 3456789012L, "456 Maple St, Hamletville", 25,
                    Arrays.asList(
                            new Present("smartwatch", 100, "Fitness and smartwatch", 0.2, "Blue"),
                            new Present("laptop", 800, "High-performance laptop", 2.5, "Silver")
                    )
            );
            return alexInstance;
        } else {
            throw new IllegalStateException("Alex already exists");
        }
    }
}
