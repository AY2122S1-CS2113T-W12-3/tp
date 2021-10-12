package seedu.duke;

import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Ui {
    private static final String HELP_MESSAGE = "Here's some tips on how to use me!\n\n"
            + "help\n"
            + "\t - Show a summary of the commands and options that I can handle\n\n"
            + "list\n"
            + "\t - Lists all the events and tasks currently in the schedule\n"
            + "\t - An additional -e or -t flag can be appended to list either events or tasks only\n"
            + "\t - E.g. list -e OR list -t\n\n"
            + "add -e n/TITLE d/DATE v/VENUE b/BUDGET\n"
            + "\t - Add an event to the schedule\n\n"
            + "delete -e n/INDEX\n"
            + "\t - Deletes an event given its index in the overall schedule\n\n"
            + "select -e n/INDEX\n"
            + "\t - Selects an event given its index in the overall schedule and displays its details\n\n"
            + "add -t n/TITLE d/DATE\n"
            + "\t - Add a task to the schedule\n\n"
            + "delete -t n/INDEX\n"
            + "\t - Deletes a task given its index in the overall schedule\n\n"
            + "select -t n/INDEX\n"
            + "\t - Selects a task given its index in the overall schedule and displays its details\n\n"
            + "update -e OR -t\n"
            + "\t - Displays the respective list of events or tasks that you can choose and update";

    public static String readInput() {
        Scanner in = new Scanner(System.in);
        String userInput = "";
        if (in.hasNextLine()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    public static String getLineBreak() {
        return "______________________________________________________________________";
    }

    public static void printLineBreak() {
        System.out.println("______________________________________________________________________");
    }

    public static void promptForDescription() {
        System.out.println("Please add an optional description for your item and press enter.");
        printLineBreak();
    }

    public static String getTaskDeletionMessage(String taskTitle) {
        return String.format("This task has been removed: %s\n", taskTitle);
    }

    public static String getTaskAddedMessage(Task task) {
        return String.format("Task added: %s\n"
                        + "Total number of tasks = %s",
                task.getTitle(), Duke.taskList.size());
    }

    public static String getEventAddedMessage(Event event) {
        return String.format("Event added: %s\n"
                        + "Total number of events = %s",
                event.getTitle(), Duke.eventList.size());
    }

    public static void printGreetingMessage() {
        System.out.println("Greetings mortal. How may you be served today?\n"
                + "TIP: enter \"help\" if you are weak and clueless!\n"
                + getLineBreak());
    }

    public static String getByeMessage() {
        return "You will be missed!!";
    }

    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }

    public static String getSelectedTaskMessage(Task task) {
        return getLineBreak() + System.lineSeparator() + "Here are the details of the task:" + System.lineSeparator()
                + getTask(task) + System.lineSeparator() + getLineBreak();
    }

    public static String getSelectedEventMessage(Event event) {
        return getLineBreak() + System.lineSeparator() + "Here are the details of the event:" + System.lineSeparator()
                + getEvent(event) + System.lineSeparator() + getLineBreak();
    }

    public static void printList(ArrayList<Item> list) {
        AtomicInteger i = new AtomicInteger();
        list.forEach(item -> System.out.println(i.getAndIncrement() + 1 + ". " + item));
    }


    public static String getTask(Task task) {
        return task.getTitle() + System.lineSeparator()
                + Parser.convertDateTime(task.getDateTime()) + System.lineSeparator()
                + task.getDescription();
    }

    public static String getEvent(Event event) {
        return event.getTitle() + System.lineSeparator()
                + Parser.convertDateTime(event.getDateTime()) + System.lineSeparator();
    }

    public static void printTask(Task task) {
        System.out.println(task.getTitle() + System.lineSeparator()
                + Parser.convertDateTime(task.getDateTime()) + System.lineSeparator()
                + task.getDescription());
    }

    public static void printEvent(Event event) {
        System.out.println(event.getTitle() + System.lineSeparator()
                + Parser.convertDateTime(event.getDateTime()) + System.lineSeparator()
                + event.getDescription() + System.lineSeparator()
                + event.getVenue() + System.lineSeparator()
                + event.getBudget());
    }
}
