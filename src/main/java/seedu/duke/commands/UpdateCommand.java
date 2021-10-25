package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Task;
import seedu.duke.parser.Parser;
import seedu.duke.Ui;
import seedu.duke.items.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class UpdateCommand extends Command {
    protected Integer eventNumber;
    protected Event eventToBeUpdated;
    protected boolean isError = false;
    protected static final String TITLE_FLAG = "title/";
    protected static final String DATE_FLAG = "date/";
    protected static final String DEADLINE_FLAG = "deadline/";
    protected static final String VENUE_FLAG = "venue/";
    protected static final String BUDGET_FLAG = "budget/";
    protected static final String DESCRIPTION_FLAG = "description/";
    protected static final String TASK_FLAG = "task/";
    protected static final String MEMBER_FLAG = "member/";

    public UpdateCommand(String[] command) {
        if (command.length < 2) {
            if (command[0].equalsIgnoreCase("update")) {
                System.out.println("please specify which event you would like to update in the format"
                        + System.lineSeparator() + "update [Event_num]");
            }
            Ui.printLineBreak();
            this.isError = true;
        } else {
            this.eventNumber = Integer.parseInt(command[1]) - 1;
            this.eventToBeUpdated = Duke.eventCatalog.get(eventNumber);
            System.out.println("Here are the details of the event:" + System.lineSeparator()
                    + "======================================");
            Ui.printEvent(Duke.eventCatalog.get(eventNumber));
            Ui.printLineBreak();
        }
    }

    public CommandResult execute() {
        String executeResult = "returning to main page...";
        if (!isError) {
            try {
                Ui.updateIntroMessage();
                String userInput = Ui.readInput();
                Ui.printLineBreak();
                if (userInput.equalsIgnoreCase("exit")) {
                    return new CommandResult(executeResult);
                }
                String[] userUpdates = userInput.trim().split(">");

                CommandResult result = implementUpdates(userUpdates);
                if (result != null) {
                    return result;
                }
                postUpdateMessage();
                Ui.printLineBreak();
            } catch (NullPointerException | NumberFormatException e) {
                executeResult = "please follow the format closely"
                        + System.lineSeparator() + "returning to main page...";
            } catch (DukeException e) {
                executeResult = e.getMessage();
            }
        }
        return new CommandResult(executeResult);
    }

    private CommandResult implementUpdates(String[] userUpdates) throws DukeException {
        for (String update : userUpdates) {
            String[] attribute = update.trim().split("/");
            if (update.contains(TITLE_FLAG)) {
                eventToBeUpdated.setTitle(attribute[1]);
            } else if (update.contains(DATE_FLAG)) {
                updateDate(attribute[1]);
            } else if (update.contains(VENUE_FLAG)) {
                eventToBeUpdated.setVenue(attribute[1]);
            } else if (update.contains(BUDGET_FLAG)) {
                updateBudget(attribute[1]);
            } else if (update.contains(DESCRIPTION_FLAG)) {
                eventToBeUpdated.setDescription(attribute[1]);
            } else if (update.contains(TASK_FLAG)) {
                updateTask(attribute[1]);
            } else {
                return new CommandResult("invalid Update, you have returned to the main page!");
            }
        }
        return null;
    }

    protected void updateTask(String index) throws DukeException {
        int taskNum = Integer.parseInt(index) - 1;
        Task taskToBeUpdated = eventToBeUpdated.getFromTaskList(taskNum);
        Ui.printTask(taskToBeUpdated);
        updateTaskIntroMessage();
        String userInput = Ui.readInput();
        String[] userUpdates = userInput.trim().split(">");
        for (String update : userUpdates) {
            String[] attribute = update.trim().split("/");
            if (update.contains(TITLE_FLAG)) {
                taskToBeUpdated.setTitle(attribute[1]);
            } else if (update.contains(DEADLINE_FLAG)) {
                updateDeadline(attribute[1], taskToBeUpdated);
            } else if (update.contains(DESCRIPTION_FLAG)) {
                taskToBeUpdated.setDescription(attribute[1]);
            } else if (update.contains(MEMBER_FLAG)) {
                updateMember(attribute[1], taskToBeUpdated);
            } else {
                System.out.println("invalid Command!");
            }
        }
        Ui.printLineBreak();
    }

    private void updateMember(String index, Task taskToBeUpdated) {
        System.out.println("Please key in the update for the Members Name");
        String userInput = Ui.readInput();
        taskToBeUpdated.getFromMemberList(Integer.parseInt(index) - 1).setName(userInput);
    }

    private void updateTaskIntroMessage() {
        Ui.printLineBreak();
        System.out.println("Please type the item for task you would like to update in the following manner "
                + System.lineSeparator() + "-----------------------------------------------------------------------"
                + System.lineSeparator() + "title/[NEW NAME]   "
                + System.lineSeparator() + "deadline/[NEW DATE[d/dd-MM-yyyy HHmm]]"
                + System.lineSeparator() + "description/[NEW DESCRIPTION]"
                + System.lineSeparator() + "member/[MEMBER INDEX]"
                + System.lineSeparator()
                + "You may type more then one update at a given time but separate them with a [>]"
                + System.lineSeparator() + Ui.getLineBreak());
    }

    protected void updateDeadline(String attribute, Task taskToBeUpdated) throws DukeException {
        try {
            LocalDateTime newDate = Parser.convertDateTime(attribute);
            if (newDate.isBefore(LocalDateTime.now())) {
                throw new DukeException("sorry a mortal cannot travel to the past");
            }
            taskToBeUpdated.setDateTime(Parser.convertDateTime(attribute));
        } catch (DateTimeParseException e) {
            System.out.println("incorrect format please ensure format for date is of [d/dd-MM-yyyy HHmm]");
        }
    }

    private void postUpdateMessage() {
        System.out.println("Here is the updated Event");
        Ui.printEvent(eventToBeUpdated);
    }


    protected void updateDate(String attribute) throws DukeException {
        try {
            LocalDateTime newDate = Parser.convertDateTime(attribute);
            if (newDate.isBefore(LocalDateTime.now())) {
                throw new DukeException("sorry a mortal cannot travel to the past");
            }
            eventToBeUpdated.setDateTime(Parser.convertDateTime(attribute));

        } catch (DateTimeParseException e) {
            System.out.println("incorrect format please ensure format for date is of [d/dd-MM-yyyy HHmm]");
        }

    }

    protected void updateBudget(String attribute) {
        try {
            double newBudget = Double.parseDouble(attribute);
            eventToBeUpdated.setBudget(newBudget);
        } catch (NullPointerException e) {
            System.out.println("please ensure budget is a double");
        }
    }
}
