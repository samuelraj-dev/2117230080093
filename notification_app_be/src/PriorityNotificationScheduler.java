import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Notification {

    String id;
    String type;
    String message;
    LocalDateTime timestamp;

    public Notification(
            String id,
            String type,
            String message,
            LocalDateTime timestamp
    ) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getWeight() {

        switch (type) {
            case "Placement":
                return 3;

            case "Result":
                return 2;

            default:
                return 1;
        }
    }

    @Override
    public String toString() {
        return type + " - " + message;
    }
}

public class PriorityNotificationScheduler {

    public static void main(String[] args) {

        List<Notification> notifications = new ArrayList<>();

        notifications.add(
                new Notification(
                        "1",
                        "Placement",
                        "Google Hiring",
                        LocalDateTime.now().minusMinutes(10)
                )
        );

        notifications.add(
                new Notification(
                        "2",
                        "Event",
                        "Tech Fest",
                        LocalDateTime.now().minusHours(1)
                )
        );

        notifications.add(
                new Notification(
                        "3",
                        "Result",
                        "Mid Sem Result",
                        LocalDateTime.now().minusMinutes(30)
                )
        );

        notifications.sort((a, b) -> {

            if (a.getWeight() != b.getWeight()) {
                return b.getWeight() - a.getWeight();
            }

            return b.timestamp.compareTo(a.timestamp);
        });

        List<Notification> top10 =
                notifications.stream()
                        .limit(10)
                        .toList();

        System.out.println("Top Notifications:");

        for (Notification notification : top10) {
            System.out.println(notification);
        }
    }
}