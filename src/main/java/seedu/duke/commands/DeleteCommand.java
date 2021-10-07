package seedu.duke.commands;

import seedu.duke.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import static seedu.duke.Duke.eventList;
import static seedu.duke.Duke.taskList;

public class DeleteCommand extends Command {

    // input from user
    public String taskToDelete;
    public static ArrayList<Item> combinedItemList = new ArrayList<>();

    // v1.0: deleteCommand deletes purely based on index, i.e. delete [TASK_INDEX]
    // converted command array to string for future uses, where input may have extra spaces
    // such as 'delete foo bar'
    public DeleteCommand(String[] command) {
        taskToDelete = Arrays.toString(command);
    }

    public CommandResult execute() {

        fillCombinedItemList();

        String removedTaskTitle;
        removedTaskTitle = deleteTask(taskToDelete);
        return new CommandResult("This task has been removed: " + removedTaskTitle
            + System.lineSeparator());
    }

    public static void fillCombinedItemList() {
        combinedItemList.clear();
        combinedItemList.addAll(eventList);
        combinedItemList.addAll(taskList);
    }

    // did not throw a custom exception if taskIndex > itemList.size(), already caught by ArrayIndexOutOfBoundsException
    public static String deleteTask(String input) {
        try {
            int taskIndex = getTaskIndex(input);
            String taskTitle = combinedItemList.get(taskIndex).getTitle();
            combinedItemList.remove(taskIndex);
            return taskTitle;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "That task does not exist";
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return ("Please tell me which task number to delete");
        }
    }

    public static int getTaskIndex(String command) {
        int taskIndexPosition = command.trim().indexOf("- ") + 1;
        return Integer.parseInt(command.trim().substring(taskIndexPosition)) - 1;
    }
}
