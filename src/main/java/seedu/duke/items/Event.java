package seedu.duke.items;

import java.time.LocalDateTime;

public class Event extends Item {

    private LocalDateTime dateTime;
    private String venue;
    private double budget;

    public static final String EVENT_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    public Event(String title, String description, LocalDateTime dateTime, String venue, int budget) {
        super("event", title, description, dateTime);
        this.venue = venue;
        this.budget = budget;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getVenue() {
        return venue;
    }

    public double getBudget() {
        return budget;
    }
}
