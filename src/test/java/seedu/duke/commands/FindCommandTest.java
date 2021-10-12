package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    @Test
    void findItemFromList_listOfTwoItems_expectOneItemFound() {
        // Setting up; this can be refactored into an utils class like in addressBook
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);

        LocalDateTime task2DateTime = Parser.convertDateTime("20-02-2022 2359");
        Task task2 = new Task("Funfair", "For Charity", task2DateTime);

        ArrayList<Item> testCombinedItemList = FindCommand.combinedItemList;
        testCombinedItemList.add(event1);
        testCombinedItemList.add(task2);

        assertEquals(2, testCombinedItemList.size());

        String[] testInput = new String[2];
        testInput[0] = "find";
        testInput[1] = "Peppa";
        String testInputFeedback = "1 items found.";

        FindCommand command = new FindCommand(testInput);
        CommandResult findResult = command.execute();
        System.out.println(FindCommand.filteredItemList.size());

        assertEquals(1, FindCommand.filteredItemList.size());
        assertEquals(testInputFeedback, findResult.feedbackToUser);
    }
}