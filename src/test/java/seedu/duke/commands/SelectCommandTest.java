package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectCommandTest {

    @Test
    void selectItemFromList_listOfTwoItems_displayOneItemDetails() {
        // Setting up
        LocalDateTime event1DateTime = Parser.convertDateTime("19-02-2022 2000");
        Event event1 = new Event("Peppa Pig's Concert",
                "Asia world tour", event1DateTime,
                "Indoor Stadium", 1000.90);

        LocalDateTime task2DateTime = Parser.convertDateTime("20-02-2022 2359");
        Task task2 = new Task("Peppa Pig's Comeback", "For Charity", task2DateTime);

        ArrayList<Item> combinedItemList = new ArrayList<>();
        combinedItemList.add(event1);
        combinedItemList.add(task2);

        assertEquals(2, combinedItemList.size());

        String[] findCommandInput = new String[]{"find", "Peppa"};
        String[] selectCommandInput = new String[]{"select", "1"};
        String expectedOutputDetails = Ui.getEvent(event1);

        FindCommand.filteredItemList.clear();
        FindCommand command1 = new FindCommand(findCommandInput);
        assertEquals(2, FindCommand.filteredItemList.size());

        SelectCommand command = new SelectCommand(selectCommandInput);
        CommandResult selectResult = command.execute();
        assertEquals(expectedOutputDetails, selectResult.feedbackToUser);
    }
}