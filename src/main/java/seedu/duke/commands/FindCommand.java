package seedu.duke.commands;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FindCommand extends Command {

    public String keyword;
    public static ArrayList<Item> combinedItemList = new ArrayList<>();

    // to pass into SelectCommand
    public static ArrayList<Item> filteredItemList = new ArrayList<>();

    public FindCommand(String[] command) {
        try {
            keyword = command[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a keyword!");
        }
    }

    public CommandResult execute() {
        combinedItemList = Parser.makeMainList();
        filteredItemList = filterItemsByString(keyword);

        // can replace this with exceptions
        if (filteredItemList.isEmpty()) {
            return new CommandResult("Failed: no matching items found");
        }
        Ui.printList(filteredItemList);
        return new CommandResult(filteredItemList.size() + " items found.");
    }

    /*
    private static String retrieveKeyword(String[] command) throws DukeException {
        String keyword = null;
        try {
            if (command.length == 1) {
                throw new DukeException("Please specify what you want to find.");
            }
            String commandAsString = convertToString(command);
            int startOfKeyword = commandAsString.trim().indexOf(" ") + 1;
            keyword = commandAsString.trim().substring(startOfKeyword);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please specify what you want to find.");
        }
        System.out.println(keyword);
        return keyword;
    }

    private static String convertToString(String[] command) {
        return Arrays.toString(command).trim();
    }
    */

    public static ArrayList<Item> filterItemsByString(String keyword) {
        filteredItemList = (ArrayList<Item>) combinedItemList.stream()
                .filter((item) -> item.getTitle().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
        return filteredItemList;
    }
}
